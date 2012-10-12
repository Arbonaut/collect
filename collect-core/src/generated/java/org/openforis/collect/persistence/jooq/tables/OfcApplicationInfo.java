/**
 * This class is generated by jOOQ
 */
package org.openforis.collect.persistence.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.1"},
                            comments = "This class is generated by jOOQ")
public class OfcApplicationInfo extends org.jooq.impl.TableImpl<org.openforis.collect.persistence.jooq.tables.records.OfcApplicationInfoRecord> {

	private static final long serialVersionUID = 171432188;

	/**
	 * The singleton instance of ofc_application_info
	 */
	public static final org.openforis.collect.persistence.jooq.tables.OfcApplicationInfo OFC_APPLICATION_INFO = new org.openforis.collect.persistence.jooq.tables.OfcApplicationInfo();

	/**
	 * The class holding records for this type
	 */
	private static final java.lang.Class<org.openforis.collect.persistence.jooq.tables.records.OfcApplicationInfoRecord> __RECORD_TYPE = org.openforis.collect.persistence.jooq.tables.records.OfcApplicationInfoRecord.class;

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.openforis.collect.persistence.jooq.tables.records.OfcApplicationInfoRecord> getRecordType() {
		return __RECORD_TYPE;
	}

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<org.openforis.collect.persistence.jooq.tables.records.OfcApplicationInfoRecord, java.lang.String> VERSION = createField("version", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * No further instances allowed
	 */
	private OfcApplicationInfo() {
		super("ofc_application_info", org.openforis.collect.persistence.jooq.Collect.COLLECT);
	}

	/**
	 * No further instances allowed
	 */
	private OfcApplicationInfo(java.lang.String alias) {
		super(alias, org.openforis.collect.persistence.jooq.Collect.COLLECT, org.openforis.collect.persistence.jooq.tables.OfcApplicationInfo.OFC_APPLICATION_INFO);
	}

	@Override
	public org.openforis.collect.persistence.jooq.tables.OfcApplicationInfo as(java.lang.String alias) {
		return new org.openforis.collect.persistence.jooq.tables.OfcApplicationInfo(alias);
	}
}
