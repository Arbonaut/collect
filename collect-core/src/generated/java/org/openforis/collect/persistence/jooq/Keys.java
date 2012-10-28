/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq;

/**
 * This class is generated by jOOQ.
 *
 * A class modelling foreign key relationships between tables of the collect schema
 */
@SuppressWarnings({"unchecked"})
public class Keys extends org.jooq.impl.AbstractKeys {

	// IDENTITY definitions

	// UNIQUE and PRIMARY KEY definitions
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcConfigRecord> ofc_config_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcConfig.OFC_CONFIG, org.openforis.collect.persistence.jooq.tables.OfcConfig.OFC_CONFIG.NAME);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcLogoRecord> ofc_logo_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcLogo.OFC_LOGO, org.openforis.collect.persistence.jooq.tables.OfcLogo.OFC_LOGO.POS);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcRecordRecord> ofc_record_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD, org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD.ID);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcSurveyRecord> ofc_survey_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcSurvey.OFC_SURVEY, org.openforis.collect.persistence.jooq.tables.OfcSurvey.OFC_SURVEY.ID);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcSurveyRecord> ofc_survey_name_key = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcSurvey.OFC_SURVEY, org.openforis.collect.persistence.jooq.tables.OfcSurvey.OFC_SURVEY.NAME);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcSurveyRecord> ofc_survey_uri_key = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcSurvey.OFC_SURVEY, org.openforis.collect.persistence.jooq.tables.OfcSurvey.OFC_SURVEY.URI);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcSurveyWorkRecord> ofc_survey_work_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK, org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.ID);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcSurveyWorkRecord> ofc_survey_work_name_key = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK, org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.NAME);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcSurveyWorkRecord> ofc_survey_work_uri_key = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK, org.openforis.collect.persistence.jooq.tables.OfcSurveyWork.OFC_SURVEY_WORK.URI);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonRecord> ofc_taxon_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON, org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.ID);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonRecord> ofc_taxon_id_key = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON, org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.TAXON_ID, org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.TAXONOMY_ID);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonVernacularNameRecord> ofc_taxon_vernacular_name_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcTaxonVernacularName.OFC_TAXON_VERNACULAR_NAME, org.openforis.collect.persistence.jooq.tables.OfcTaxonVernacularName.OFC_TAXON_VERNACULAR_NAME.ID);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonomyRecord> ofc_taxonomy_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY, org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.ID);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonomyRecord> ofc_taxonomy_name_key = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY, org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.NAME);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcUserRecord> ofc_user_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcUser.OFC_USER, org.openforis.collect.persistence.jooq.tables.OfcUser.OFC_USER.ID);
	public static final org.jooq.UniqueKey<org.openforis.collect.persistence.jooq.tables.records.OfcUserRoleRecord> ofc_user_role_pkey = createUniqueKey(org.openforis.collect.persistence.jooq.tables.OfcUserRole.OFC_USER_ROLE, org.openforis.collect.persistence.jooq.tables.OfcUserRole.OFC_USER_ROLE.ID);

	// FOREIGN KEY definitions
	public static final org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcRecordRecord, org.openforis.collect.persistence.jooq.tables.records.OfcSurveyRecord> ofc_record__ofc_record_survey_fkey = createForeignKey(ofc_survey_pkey, org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD, org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD.SURVEY_ID);
	public static final org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcRecordRecord, org.openforis.collect.persistence.jooq.tables.records.OfcUserRecord> ofc_record__ofc_record_created_by_user_fkey = createForeignKey(ofc_user_pkey, org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD, org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD.CREATED_BY_ID);
	public static final org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcRecordRecord, org.openforis.collect.persistence.jooq.tables.records.OfcUserRecord> ofc_record__ofc_record_modified_by_user_fkey = createForeignKey(ofc_user_pkey, org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD, org.openforis.collect.persistence.jooq.tables.OfcRecord.OFC_RECORD.MODIFIED_BY_ID);
	public static final org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonRecord, org.openforis.collect.persistence.jooq.tables.records.OfcTaxonomyRecord> ofc_taxon__ofc_taxon_taxonomy_fkey = createForeignKey(ofc_taxonomy_pkey, org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON, org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.TAXONOMY_ID);
	public static final org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonRecord, org.openforis.collect.persistence.jooq.tables.records.OfcTaxonRecord> ofc_taxon__ofc_taxon_parent_fkey = createForeignKey(ofc_taxon_pkey, org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON, org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.PARENT_ID);
	public static final org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonVernacularNameRecord, org.openforis.collect.persistence.jooq.tables.records.OfcTaxonRecord> ofc_taxon_vernacular_name__ofc_taxon_vernacular_name_taxon_fkey = createForeignKey(ofc_taxon_pkey, org.openforis.collect.persistence.jooq.tables.OfcTaxonVernacularName.OFC_TAXON_VERNACULAR_NAME, org.openforis.collect.persistence.jooq.tables.OfcTaxonVernacularName.OFC_TAXON_VERNACULAR_NAME.TAXON_ID);
	public static final org.jooq.ForeignKey<org.openforis.collect.persistence.jooq.tables.records.OfcUserRoleRecord, org.openforis.collect.persistence.jooq.tables.records.OfcUserRecord> ofc_user_role__ofc_user_user_role_fkey = createForeignKey(ofc_user_pkey, org.openforis.collect.persistence.jooq.tables.OfcUserRole.OFC_USER_ROLE, org.openforis.collect.persistence.jooq.tables.OfcUserRole.OFC_USER_ROLE.USER_ID);

	/**
	 * No instances
	 */
	private Keys() {}
}
