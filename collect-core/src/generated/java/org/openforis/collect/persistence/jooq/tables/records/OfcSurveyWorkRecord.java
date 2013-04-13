/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.1"},
                            comments = "This class is generated by jOOQ")
public class OfcSurveyWorkRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.collect.persistence.jooq.tables.records.OfcSurveyWorkRecord> {

	private static final long serialVersionUID = -1853982996;

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.ID, value);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.lang.Integer getId() {
		return getValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.ID);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.util.List<org.openforis.collect.persistence.jooq.tables.records.OfcSamplingDesignRecord> fetchOfcSamplingDesignList() {
		return create()
			.selectFrom(org.openforis.collect.persistence.jooq.tables.OfcSamplingDesign.OFC_SAMPLING_DESIGN)
			.where(org.openforis.collect.persistence.jooq.tables.OfcSamplingDesign.OFC_SAMPLING_DESIGN.SURVEY_WORK_ID.equal(getValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.ID)))
			.fetch();
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.util.List<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonomyRecord> fetchOfcTaxonomyList() {
		return create()
			.selectFrom(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY)
			.where(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.SURVEY_WORK_ID.equal(getValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.ID)))
			.fetch();
	}

	/**
	 * An uncommented item
	 */
	public void setName(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.NAME, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getName() {
		return getValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.NAME);
	}

	/**
	 * An uncommented item
	 */
	public void setUri(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.URI, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getUri() {
		return getValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.URI);
	}

	/**
	 * An uncommented item
	 */
	public void setIdml(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.IDML, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getIdml() {
		return getValue(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.IDML);
	}

	/**
	 * Create a detached OfcSurveyWorkRecord
	 */
	public OfcSurveyWorkRecord() {
		super(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK);
	}
}
