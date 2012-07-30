/**
 * 
 */
package org.openforis.collect.manager;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.openforis.collect.model.CollectRecord;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.collect.model.Configuration;
import org.openforis.idm.metamodel.AttributeDefinition;
import org.openforis.idm.metamodel.EntityDefinition;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.metamodel.Schema;
import org.openforis.idm.metamodel.Survey;
import org.openforis.idm.model.Attribute;
import org.openforis.idm.model.Entity;
import org.openforis.idm.model.Node;
import org.openforis.idm.model.NodeVisitor;
import org.openforis.idm.model.Record;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author S. Ricci
 *
 */
public class RecordIndexManager {
	
	private static final Version LUCENE_VERSION = Version.LUCENE_36;

	public enum SearchType {
		EQUAL, STARTS_WITH, CONTAINS;
	}

	protected static Log LOG = LogFactory.getLog(RecordIndexManager.class);

	protected static final String INDEX_PATH_CONFIGURATION_KEY = "index_path";
	
	protected static final QName INDEX_NAME_ANNOTATION = new QName("http://www.openforis.org/collect/3.0/collect", "index");

	protected static final String RECORD_ID_FIELD = "_record_id";
	
	protected RAMDirectory ramDirectory;
	
	@Autowired
	private ConfigurationManager configurationManager;

	@Autowired
	private RecordManager recordManager;
	
	protected static String indexRootPath;
	
	protected static boolean inited = false;
	
	private boolean cancelled;

	public RecordIndexManager() {
	}
	
	protected void init() throws RecordIndexException {
		if ( ! inited ) {
			initStatics();
		}
		initTemporaryIndex();
		cancelled = false;
	}

	protected void initStatics() throws RecordIndexException {
		Configuration configuration = configurationManager.getConfiguration();
		indexRootPath = configuration.get(INDEX_PATH_CONFIGURATION_KEY);
		unlock();
		IndexWriter indexWriter = createIndexWriter();
		closeIndexHandler(indexWriter);
		inited = true;
	}
	
	public static void unlock() throws RecordIndexException {
		try {
			File indexRootDir = new File(indexRootPath);
			Directory directory = new SimpleFSDirectory(indexRootDir);
			if ( IndexWriter.isLocked(directory) ) {
				IndexWriter.unlock(directory);
			}
		} catch(Exception e) {
			destroyIndex();
		}
	}
	
	public static void destroyIndex() {
		File indexDir = new File(indexRootPath);
		indexDir.delete();
	}
	
	public void initTemporaryIndex() throws RecordIndexException {
		ramDirectory = new RAMDirectory();
		IndexWriter indexWriter = null;
		try {
			indexWriter = createTemporaryIndexWriter();
		} catch (Exception e) {
			throw new RecordIndexException(e);
		} finally {
			closeIndexHandler(indexWriter);
		}
	}

	public void cleanIndex() throws RecordIndexException {
		IndexWriter indexWriter = createIndexWriter();
		destroyIndex(indexWriter);
	}
	
	public void destroyTemporaryIndex() throws RecordIndexException {
		IndexWriter indexWriter = createTemporaryIndexWriter();
		destroyIndex(indexWriter);
	}
	
	protected void destroyIndex(IndexWriter indexWriter) throws RecordIndexException {
		try {
			indexWriter.deleteAll();
		} catch (Exception e) {
			throw new RecordIndexException(e);
		} finally {
			closeIndexHandler(indexWriter);
		}
	}

	public void temporaryIndex(CollectRecord record) throws RecordIndexException {
		Entity rootEntity = record.getRootEntity();
		EntityDefinition rootEntityDefn = rootEntity.getDefinition();
		if ( hasIndexableNodes(rootEntityDefn) ) {
			IndexWriter indexWriter = null;
			try {
				indexWriter = createTemporaryIndexWriter();
				indexWriter.deleteAll(); //temporary index is relative only to one record
				index(indexWriter, record);
			} catch (Exception e) {
				throw new RecordIndexException(e);
			} finally {
				closeIndexHandler(indexWriter);
			}
		}
	}
	
	public void index(CollectRecord record) throws RecordIndexException {
		cancelled = false;
		IndexWriter indexWriter = null;
		try {
			indexWriter = createIndexWriter();
			Integer recordId = record.getId();
			deleteDocuments(indexWriter, recordId);
			index(indexWriter, record);
			//TODO cancel indexing if "cancelled" becomes "true"
		} catch (Exception e) {
			throw new RecordIndexException(e);
		} finally {
			closeIndexHandler(indexWriter);
		}
	}
	
	public void indexAllRecords(CollectSurvey survey, String rootEntity) throws RecordIndexException {
		cancelled = false;
		List<CollectRecord> summaries = recordManager.loadSummaries(survey, rootEntity);
		IndexWriter indexWriter = null;
		try {
			indexWriter = createIndexWriter();
			for (CollectRecord record : summaries) {
				if ( ! cancelled ) {
					Integer recordId = record.getId();
					deleteDocuments(indexWriter, recordId);
					index(indexWriter, record);
				} else {
					break;
				}
			}
		} catch (Exception e) {
			throw new RecordIndexException(e);
		} finally {
			closeIndexHandler(indexWriter);
		}
	}

	public void cancelIndexing() {
		cancelled = true;
	}

	public boolean hasIndexableNodes(Survey survey) {
		Schema schema = survey.getSchema();
		List<EntityDefinition> rootEntityDefinitions = schema.getRootEntityDefinitions();
		for (EntityDefinition entityDefn: rootEntityDefinitions) {
			boolean hasIndexableNodes = hasIndexableNodes(entityDefn);
			if ( hasIndexableNodes ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasIndexableNodes(EntityDefinition entityDefn) {
		Stack<NodeDefinition> stack = new Stack<NodeDefinition>();
		stack.push(entityDefn);
		while ( ! stack.isEmpty() ) {
			NodeDefinition defn = stack.pop();
			String indexName = defn.getAnnotation(INDEX_NAME_ANNOTATION);
			if ( StringUtils.isNotBlank(indexName) ) {
				return true;
			}
			if ( defn instanceof EntityDefinition ) {
				List<NodeDefinition> childDefns = ((EntityDefinition) defn).getChildDefinitions();
				stack.addAll(childDefns);
			}
		}
		return false;
	}
	
	private void index(final IndexWriter indexWriter, CollectRecord record) throws RecordIndexException {
		try {
			Entity rootEntity = record.getRootEntity();
			rootEntity.traverse(new NodeVisitor() {
				@Override
				public void visit(Node<? extends NodeDefinition> node, int idx) {
					NodeDefinition defn = node.getDefinition();
					if (defn instanceof AttributeDefinition ) {
						index(indexWriter, (Attribute<?, ?>) node);
					}
				}
			});
		} catch (Exception e) {
			throw new RecordIndexException(e);
		}
	}

	public void index(List<CollectRecord> records) throws Exception {
		for (CollectRecord record : records) {
			index(record);
		}
	}
	
    public List<String> search(SearchType searchType, Survey survey, int attributeDefnId, int fieldIndex, String queryText, int maxResults)  throws RecordIndexException {
    	Schema schema = survey.getSchema();
    	AttributeDefinition defn = (AttributeDefinition) schema.getById(attributeDefnId);
    	String indexName = defn.getAnnotation(INDEX_NAME_ANNOTATION);
		if ( StringUtils.isNotBlank(indexName) ) {
			IndexSearcher indexSearcher = null;
			try {
				//search in ram directory
				Set<String> tempResult = searchInTemporaryDirectory(searchType, indexName,
						fieldIndex, queryText, maxResults);
				//search in file system index
		        indexSearcher = createIndexSearcher();
		        Set<String> committedResult = search(indexName, indexSearcher, searchType, queryText, fieldIndex, maxResults);
		        List<String> result = mergeSearchResults(maxResults, tempResult, committedResult);
		        return result;
	        } catch(Exception e) {
	        	throw new RecordIndexException(e);
	        } finally {
	        	closeIndexHandler(indexSearcher);
	        }
		} else {
			throw new RecordIndexException("");
		}
    }

	protected Set<String> searchInTemporaryDirectory(SearchType searchType, String indexName,
			int fieldIndex, String queryText, int maxResults)
			throws RecordIndexException, Exception {
		if ( ramDirectory != null ) {
			IndexSearcher indexSearcher = createTemporaryIndexSearcher();
			Set<String> tempResult = search(indexName, indexSearcher, searchType, queryText, fieldIndex, maxResults);
			return tempResult;
		} else {
			return Collections.emptySet();
		}
	}

	protected void deleteDocuments(IndexWriter indexWriter, int recordId)
			throws RecordIndexException {
		try {
			Term term = new Term(RECORD_ID_FIELD, Integer.toString(recordId));
			indexWriter.deleteDocuments(term);
		} catch (Exception e) {
			throw new RecordIndexException(e);
		}
	}

	protected List<String> mergeSearchResults(int maxResults, Set<String> tempResult, Set<String> committedResult) {
		Set<String> result = new HashSet<String>();
		result.addAll(tempResult);
		result.addAll(committedResult);
		List<String> sortedList = new ArrayList<String>(result);
		Collections.sort(sortedList);
		if ( sortedList.size() > maxResults ) {
			sortedList = sortedList.subList(0, maxResults - 1);
		}
		return sortedList;
	}

	protected Set<String> search(String indexName, IndexSearcher indexSearcher, SearchType searchType, String queryText, int fieldIndex, int maxResults)
			throws Exception {
		Set<String> result = new HashSet<String>();
        if ( indexSearcher != null ) {
			String indexFieldKey = indexName + "_" +Integer.toString(fieldIndex);
			Query query = createQuery(searchType, indexFieldKey, queryText);
			TopDocs hits = indexSearcher.search(query, maxResults);
			ScoreDoc[] scoreDocs = hits.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
			    Document doc = indexSearcher.doc(scoreDoc.doc);
			    String value = doc.get(indexFieldKey);
				result.add(value);
			}
        }
		return result;
	}
    
    protected IndexWriter createTemporaryIndexWriter() throws RecordIndexException {
    	try {
    		SimpleAnalyzer analyzer = new SimpleAnalyzer(LUCENE_VERSION);
			IndexWriterConfig conf = new IndexWriterConfig(LUCENE_VERSION, analyzer);
			conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
			IndexWriter indexWriter = new IndexWriter(ramDirectory, conf);
	    	return indexWriter;
    	} catch (IOException e) {
			throw new RecordIndexException(e.getMessage(), e);
		}
    }

	protected static IndexWriter createIndexWriter() throws RecordIndexException {
		try {
			File indexDir = new File(indexRootPath);
			if ( indexDir.exists() || indexDir.mkdirs() ) {
				SimpleAnalyzer analyzer = new SimpleAnalyzer(LUCENE_VERSION);
				IndexWriterConfig conf = new IndexWriterConfig(LUCENE_VERSION, analyzer);
				conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
				Directory directory = new SimpleFSDirectory(indexDir);
				IndexWriter indexWriter = new IndexWriter(directory, conf);
				return indexWriter;
			} else {
				throw new RecordIndexException("Cannot access index directory: " + indexRootPath);
			}
		} catch (IOException e) {
			throw new RecordIndexException(e.getMessage(), e);
		}
	}

	protected IndexSearcher createIndexSearcher() throws RecordIndexException {
		try {
			File indexDir = new File(indexRootPath);
	        if ( indexDir.exists() || indexDir.mkdirs() ) {
				Directory directory = new SimpleFSDirectory(indexDir);
		        IndexReader indexReader = IndexReader.open(directory);
				int numDocs = indexReader.numDocs();
				if ( numDocs > 0 ) {
			        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			        return indexSearcher;
				} else {
					return null;
				}
	        } else {
	        	throw new RecordIndexException("Cannot access index directory: " + indexRootPath);
	        }
		} catch (IOException e) {
			throw new RecordIndexException(e.getMessage(), e);
		}
    }
	
	protected IndexSearcher createTemporaryIndexSearcher() throws RecordIndexException {
		try {
			IndexReader indexReader = IndexReader.open(ramDirectory);
	        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
	        return indexSearcher;
		} catch (IOException e) {
			throw new RecordIndexException(e.getMessage(), e);
		}
	}

	protected void index(IndexWriter indexWriter, Attribute<?, ?> attr) {
		AttributeDefinition defn = attr.getDefinition();
		String indexName = defn.getAnnotation(INDEX_NAME_ANNOTATION);
		if ( StringUtils.isNotBlank(indexName) ) {
			try {
				Object value = attr.getValue();
				if ( value != null ) {
					Document doc = new Document();
					Record record = attr.getRecord();
					Integer recordId = record.getId();
					if ( recordId  != null ) {
						Field recordKeyField = createRecordIdField(recordId);
						doc.add(recordKeyField);
					}
					int fieldCount = attr.getFieldCount();
					for (int fieldIndex = 0; fieldIndex < fieldCount; fieldIndex++ ) {
						org.openforis.idm.model.Field<?> field = attr.getField(fieldIndex);
						index(doc, indexName, field);
					}
					indexWriter.addDocument(doc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void index(Document doc, String indexName, org.openforis.idm.model.Field<?> field) {
		int fieldIndex = field.getIndex();
		Object fieldValue = field.getValue();
		if ( fieldValue != null ) {
			String fieldValueStr = fieldValue.toString();
			if ( StringUtils.isNotBlank(fieldValueStr)) {
				String fieldKey = indexName + "_" + Integer.toString(fieldIndex);
				Field docField = new Field(fieldKey, fieldValueStr, Field.Store.YES, Field.Index.ANALYZED);
				doc.add(docField);
			}
		}
	}
	
	protected Field createRecordIdField(int recordId) {
		Field recordKeyField = new Field(RECORD_ID_FIELD, Integer.toString(recordId), Field.Store.YES, Field.Index.NOT_ANALYZED);
		return recordKeyField;
	}

	protected Query createQuery(SearchType searchType, String indexFieldKey, String searchText) throws ParseException {
		String escapedSearchText = QueryParser.escape(searchText.trim().toLowerCase());
		String queryText = escapedSearchText;
		
		if ( StringUtils.isNotBlank(queryText) ) {
			switch ( searchType ) {
			case STARTS_WITH:
				queryText = escapedSearchText + "*";
				break;
			case CONTAINS:
				//queryText = "*" + escapedSearchText + "*"; TODO support CONTAINS query
				queryText = escapedSearchText + "*";
				break;
			default:
				queryText = escapedSearchText;
			}
		}
		SimpleAnalyzer analyzer = new SimpleAnalyzer(LUCENE_VERSION);
		QueryParser queryParser = new QueryParser(LUCENE_VERSION, indexFieldKey, analyzer);
		queryParser.setDefaultOperator(Operator.AND);
		Query query = queryParser.parse(queryText);
        return query;
    }
	
	private void closeIndexHandler(Closeable indexHandler) {
		if ( indexHandler != null ) {
			try {
				indexHandler.close();
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

}
