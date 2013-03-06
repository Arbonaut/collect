/**
 * 
 */
package org.openforis.collect.manager.speciesImport;

import static org.openforis.idm.model.species.Taxon.TaxonRank.FAMILY;
import static org.openforis.idm.model.species.Taxon.TaxonRank.GENUS;
import static org.openforis.idm.model.species.Taxon.TaxonRank.SPECIES;
import static org.openforis.idm.model.species.Taxon.TaxonRank.SUBSPECIES;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.gbif.ecat.model.ParsedName;
import org.gbif.ecat.parser.NameParser;
import org.gbif.ecat.parser.UnparsableException;
import org.gbif.ecat.voc.Rank;
import org.openforis.collect.manager.referenceDataImport.CSVDataImportReader;
import org.openforis.collect.manager.referenceDataImport.CSVLineParser;
import org.openforis.collect.manager.referenceDataImport.ParsingError;
import org.openforis.collect.manager.referenceDataImport.ParsingError.ErrorType;
import org.openforis.collect.manager.referenceDataImport.ParsingException;
import org.openforis.commons.io.csv.CsvLine;
import org.openforis.idm.metamodel.Languages;
import org.openforis.idm.model.species.Taxon.TaxonRank;

/**
 * @author S. Ricci
 *
 */
public class SpeciesCSVReader extends CSVDataImportReader<SpeciesLine> {

	public SpeciesCSVReader(String filename) throws IOException, ParsingException {
		super(filename);
	}

	public SpeciesCSVReader(Reader reader) throws IOException, ParsingException {
		super(reader);
	}
	
	@Override
	protected SpeciesCSVLineParser createLineParserInstance() {
		SpeciesCSVLineParser lineParser = SpeciesCSVLineParser.createInstance(this, currentCSVLine);
		return lineParser;
	}
	

	@Override
	public boolean validateAllFile() throws ParsingException {
		Validator validator = new Validator();
		validator.validate();
		return true;
	}
	
	public List<String> getLanguageColumnNames() {
		List<String> columnNames = getColumnNames();
		int fixedColumnsLength = SpeciesFileColumn.values().length;
		if ( columnNames.size() > fixedColumnsLength ) {
			return columnNames.subList(fixedColumnsLength, columnNames.size());
		} else {
			return Collections.emptyList();
		}
	}
	
	public static class SpeciesCSVLineParser extends CSVLineParser<SpeciesLine> {
		
		private static final String VERNACULAR_NAME_TRIM_EXPRESSION = "^\\s+|\\s+$|;+$|\\.+$";
		private static final String SYNONYM_COL_NAME = "";
		private static final Pattern SYNONYM_PATTERN = Pattern.compile("^syn\\.?\\s+", Pattern.CASE_INSENSITIVE);
		
		private static final String DEFAULT_VERNACULAR_NAMES_SEPARATOR = ",";
		private static final String OTHER_VERNACULAR_NAMES_SEPARATOR_EXPRESSION = "/";

		private ParsedName<Object> parsedScientificName;
		private String rawScientificName;
		
		SpeciesCSVLineParser(SpeciesCSVReader reader, CsvLine line) {
			super(reader, line);
		}
		
		public static SpeciesCSVLineParser createInstance(SpeciesCSVReader reader, CsvLine line) {
			return new SpeciesCSVLineParser(reader, line);
		}
	
		public SpeciesLine parse() throws ParsingException {
			SpeciesLine line = super.parse();
			this.rawScientificName = extractRawScientificName();
			this.parsedScientificName = parseRawScienfificName();
			line.setTaxonId(parseTaxonId(false));
			line.setCode(extractCode());
			line.setFamilyName(extractFamilyName());
			line.setRawScientificName(this.rawScientificName);
			line.setGenus(extractGenus());
			line.setSpeciesName(extractSpeciesName());
			line.setCanonicalScientificName(extractCanonicalScientificName());
			line.setRank(extractRank());
			line.setLanguageToVernacularNames(extractVernacularNames());
			return line;
		}

		protected Integer parseTaxonId(boolean required) throws ParsingException {
			return getColumnValue(SpeciesFileColumn.NO, required, Integer.class);
		}

		protected String extractCode() throws ParsingException {
			return getColumnValue(SpeciesFileColumn.CODE, true, String.class);
		}
		
		protected String extractFamilyName() throws ParsingException {
			return getColumnValue(SpeciesFileColumn.FAMILY, true, String.class);
		}

		protected String extractRawScientificName() throws ParsingException {
			return getColumnValue(SpeciesFileColumn.SCIENTIFIC_NAME, true, String.class);
		}
		
		protected ParsedName<Object> parseRawScienfificName() throws ParsingException {
			try {
				NameParser nameParser = new NameParser();
				ParsedName<Object> parsedName = nameParser.parse(rawScientificName);
				return parsedName;
			} catch (UnparsableException e) {
				ParsingError error = createFieldParsingError(
						SpeciesFileColumn.SCIENTIFIC_NAME, "scientific name", rawScientificName);
				throw new ParsingException(error);
			}
		}
		
		protected TaxonRank extractRank() throws ParsingException {
			Rank rank = parsedScientificName.getRank();
			TaxonRank taxonRank;
			if ( rank == null ) {
				taxonRank = GENUS;
			} else {
				switch ( rank ) {
				case FAMILY:
					taxonRank = FAMILY;
					break;
				case GENUS:
					taxonRank = GENUS;
					break;
				case SPECIES:
					taxonRank = SPECIES;
					break;
				case VARIETY:
					taxonRank = SUBSPECIES;
					break;
				default:
					taxonRank = SPECIES;
				}
			}
			return taxonRank;
		}

		protected String extractCanonicalScientificName() throws ParsingException {
			Rank rank = parsedScientificName.getRank();
			boolean showRankMarker = rank == Rank.GENUS || rank == Rank.VARIETY;
			String result = parsedScientificName.buildName(false, showRankMarker, false, false, false, true, true, false, false, false, false);
			return result;
		}
		
		protected String extractGenus() throws ParsingException {
			String genus = parsedScientificName.getGenusOrAbove();
			return genus;
		}
		
		protected String extractSpeciesName() throws ParsingException {
			String speciesName = parsedScientificName.canonicalSpeciesName();
			return speciesName;
		}
		
		protected Map<String, List<String>> extractVernacularNames() throws ParsingException {
			Map<String, List<String>> result = new HashMap<String, List<String>>();
			List<String> languageColumnNames = ((SpeciesCSVReader) getReader()).getLanguageColumnNames();
			for (String langCode : languageColumnNames) {
				List<String> vernacularNames = extractVernacularNames(langCode);
				result.put(langCode, vernacularNames);
			}
			return result;
		}

		protected List<String> extractVernacularNames(String colName) throws ParsingException {
			String colValue = StringUtils.normalizeSpace(getColumnValue(colName, false, String.class));
			if ( StringUtils.isBlank(colValue) ) {
				return Collections.emptyList();
			} else {
				List<String> result = new ArrayList<String>();
				String normalized = colValue.replaceAll(OTHER_VERNACULAR_NAMES_SEPARATOR_EXPRESSION, DEFAULT_VERNACULAR_NAMES_SEPARATOR);
				String[] split = StringUtils.split(normalized, DEFAULT_VERNACULAR_NAMES_SEPARATOR);
				for (String splitPart : split) {
					String trimmedPart = extractVernacularName(colName, splitPart);
					if ( trimmedPart != null ) {
						result.add(trimmedPart);
					}
				}
				return result;
			}
		}

		private String extractVernacularName(String colName, String splitPart) throws ParsingException {
			String trimmed = splitPart.replaceAll(VERNACULAR_NAME_TRIM_EXPRESSION, "");
			if ( trimmed.length() > 0 ) {
				Matcher matcher = SYNONYM_PATTERN.matcher(trimmed);
				if ( matcher.find() ) {
					if ( SYNONYM_COL_NAME.equals(colName) ) {
						matcher.replaceAll("");
					} else {
						ParsingError error = new ParsingError(ErrorType.UNEXPECTED_SYNONYM, lineNumber, colName);
						throw new ParsingException(error);
					}
				}
				return trimmed;
			} else {
				return null;
			}
		}

		@SuppressWarnings("unchecked")
		protected <T> T getColumnValue(SpeciesFileColumn column, boolean required, Class<T> type) throws ParsingException {
			T value = csvLine.getValue(column.getName(), type);
			if ( required && ( value == null || value instanceof String && StringUtils.isBlank((String) value) )) {
				throwEmptyColumnParsingException(column);
			}
			if ( value instanceof String ) {
				value = (T) value.toString().trim();
			}
			return value;
		}
		
		protected ParsingError createFieldParsingError(SpeciesFileColumn column, String fieldName, String value) {
			ParsingError error = new ParsingError(ErrorType.INVALID_VALUE, lineNumber, 
					column.getName(), "Error parsing " + fieldName +" from " + value);
			return error;
		}

		protected void throwEmptyColumnParsingException(SpeciesFileColumn column)
				throws ParsingException {
			ParsingError error = new ParsingError(ErrorType.EMPTY, lineNumber, column.getName());
			throw new ParsingException(error);
		}

	}
	
	class Validator {
		
		public void validate() throws ParsingException {
			validateHeaders();
		}

		protected void validateHeaders() throws ParsingException {
			List<String> colNames = getColumnNames();
			SpeciesFileColumn[] expectedColumns = SpeciesFileColumn.values();
			int fixedColsSize = expectedColumns.length;
			if ( colNames == null || colNames.size() < fixedColsSize ) {
				ParsingError error = new ParsingError(ErrorType.UNEXPECTED_COLUMNS);
				throw new ParsingException(error);
			}
			for (int i = 0; i < fixedColsSize; i++) {
				String colName = StringUtils.trimToEmpty(colNames.get(i));
				String expectedColName = expectedColumns[i].getName();
				if ( ! expectedColName.equals(colName) ) {
					ParsingError error = new ParsingError(ErrorType.WRONG_COLUMN_NAME, 1, colName, expectedColName);
					throw new ParsingException(error);
				}
			}
			validateLanguageHeaders(colNames);
		}

		protected void validateLanguageHeaders(List<String> colNames) {
			List<String> languageColumnNames = getLanguageColumnNames();
			for (String colName : languageColumnNames) {
				if ( ! Languages.exists(Languages.Standard.ISO_639_3, colName) ) {
					throw new RuntimeException("Invalid column name: " + colName + " - valid lanugage code (ISO-639-3) expected");
				}
			}
		}
		
	}

}
