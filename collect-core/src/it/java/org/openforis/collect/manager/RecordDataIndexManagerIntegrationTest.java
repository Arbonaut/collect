package org.openforis.collect.manager;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openforis.collect.manager.RecordDataIndexManager.SearchType;
import org.openforis.collect.model.CollectRecord;
import org.openforis.collect.model.CollectRecord.Step;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.collect.model.CollectSurveyContext;
import org.openforis.collect.persistence.SurveyImportException;
import org.openforis.collect.persistence.xml.CollectIdmlBindingContext;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.metamodel.xml.InvalidIdmlException;
import org.openforis.idm.metamodel.xml.SurveyUnmarshaller;
import org.openforis.idm.model.Code;
import org.openforis.idm.model.Entity;
import org.openforis.idm.model.expression.ExpressionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author S. Ricci
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = {"classpath:test-context.xml"} )
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class RecordDataIndexManagerIntegrationTest {
	//private final Log log = LogFactory.getLog(ConfigurationDaoIntegrationTest.class);
	
	@Autowired
	protected RecordDataIndexManager indexManager;
	
	@Before
	public void before() throws Exception {
		indexManager.destroyIndex();
	}
	
	@Test
	public void roundTripTest() throws Exception  {
		CollectSurvey survey = loadSurvey();
		String[] gpsModels = new String[] {"GPS MAP 62 S", "GPS MAP 60CSX", "SXBLUEII-L", "GPS MAP 62S"};
		createIndex(survey, gpsModels);
		NodeDefinition autoCompleteNodeDefn = survey.getSchema().getByPath("/cluster/gps_model");
		
		//testSingleResultMatching(survey, autoCompleteNodeDefn);
		
		testMultipleResultsFound(survey, autoCompleteNodeDefn);
		
		testLimitedMultipleResultsFound(survey, autoCompleteNodeDefn);
		
		testNoResultsFound(survey, autoCompleteNodeDefn);
	}
	
	@After
	public void after() throws Exception {
		indexManager.destroyIndex();
	}

	private void testSingleResultMatching(CollectSurvey survey, NodeDefinition autoCompleteNodeDefn) throws Exception {
		List<String> result = indexManager.search(SearchType.EQUAL, survey, autoCompleteNodeDefn.getId(), 0, "SXBLUEII-L", 10);
		assertNotNull(result);
		assertEquals(1, result.size());
		String value = result.iterator().next();
		assertEquals("SXBLUEII-L", value);
	}

	private void testMultipleResultsFound(CollectSurvey survey, NodeDefinition autoCompleteNodeDefn) throws Exception {
		List<String> result;
		result = indexManager.search(SearchType.STARTS_WITH, survey, autoCompleteNodeDefn.getId(), 0, "GPS", 10);
		assertNotNull(result);
		assertEquals(3, result.size());
		assertArrayEquals(new String[] {"GPS MAP 60CSX", "GPS MAP 62 S", "GPS MAP 62S"}, result.toArray());
	}

	private void testNoResultsFound(CollectSurvey survey, NodeDefinition autoCompleteNodeDefn) throws Exception {
		List<String> result;
		result = indexManager.search(SearchType.STARTS_WITH, survey, autoCompleteNodeDefn.getId(), 0, "GPS NOT LISTED", 10);
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	private void testLimitedMultipleResultsFound(CollectSurvey survey, NodeDefinition autoCompleteNodeDefn) throws Exception {
		List<String> result;
		result = indexManager.search(SearchType.STARTS_WITH, survey, autoCompleteNodeDefn.getId(), 0, "GPS", 2);
		assertNotNull(result);
		assertEquals(2, result.size());
		assertArrayEquals(new String[] {"GPS MAP 60CSX", "GPS MAP 62 S"}, result.toArray());
	}

	private void createIndex(CollectSurvey survey, String[] gpsModels) throws Exception {
		int count = 1;
		for (String gpsModel : gpsModels) {
			CollectRecord record = createTestRecord(survey, Integer.toString(count++), gpsModel);
			indexManager.index(record);
		}
	}
	
	private CollectSurvey loadSurvey() throws IOException, SurveyImportException, InvalidIdmlException {
		URL idm = ClassLoader.getSystemResource("test.idm.xml");
		InputStream is = idm.openStream();
		CollectSurveyContext context = new CollectSurveyContext(new ExpressionFactory(), null, null);
		CollectIdmlBindingContext idmlBindingContext = new CollectIdmlBindingContext(context);
		SurveyUnmarshaller surveyUnmarshaller = idmlBindingContext.createSurveyUnmarshaller();
		CollectSurvey survey = (CollectSurvey) surveyUnmarshaller.unmarshal(is);
		survey.setName("archenland1");
		return survey;
	}

	private CollectRecord createTestRecord(CollectSurvey survey, String id, String gpsModel) {
		CollectRecord record = new CollectRecord(survey, "2.0");
		Entity cluster = record.createRootEntity("cluster");
		record.setCreationDate(new GregorianCalendar(2011, 12, 31, 23, 59).getTime());
		record.setStep(Step.ENTRY);
		cluster.addValue("id", new Code(id));
		cluster.addValue("gps_model", gpsModel);
		return record;
	}

}
