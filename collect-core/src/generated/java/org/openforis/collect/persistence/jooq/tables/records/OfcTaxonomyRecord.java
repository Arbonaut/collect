/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.1"},
                            comments = "This class is generated by jOOQ")
public class OfcTaxonomyRecord extends org.jooq.impl.UpdatableRecordImpl<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonomyRecord> {

	private static final long serialVersionUID = -1573896626;

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.ID, value);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.lang.Integer getId() {
		return getValue(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.ID);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.util.List<org.openforis.collect.persistence.jooq.tables.records.OfcTaxonRecord> fetchOfcTaxonList() {
		return create()
			.selectFrom(org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON)
			.where(org.openforis.collect.persistence.jooq.tables.OfcTaxon.OFC_TAXON.TAXONOMY_ID.equal(getValue(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.ID)))
			.fetch();
	}

	/**
	 * An uncommented item
	 */
	public void setName(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.NAME, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getName() {
		return getValue(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.NAME);
	}

	/**
	 * An uncommented item
	 */
	public void setMetadata(java.lang.String value) {
		setValue(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.METADATA, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getMetadata() {
		return getValue(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY.METADATA);
	}

	/**
	 * Create a detached OfcTaxonomyRecord
	 */
	public OfcTaxonomyRecord() {
		super(org.openforis.collect.persistence.jooq.tables.OfcTaxonomy.OFC_TAXONOMY);
	}
}
