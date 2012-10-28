package org.openforis.collect.persistence.jooq;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.SelectLimitStep;
import org.jooq.conf.Settings;
import org.jooq.impl.Factory;

/**
 * @author G. Miceli
 */
public class DialectAwareJooqFactory extends Factory {
	
	private static final long serialVersionUID = 1L;
	private static final String POSTGRESQL_DBNAME = "PostgreSQL";
	private static final String APACHE_DERBY_DBNAME = "Apache Derby";
	private static final String SQLITE_DBNAME = "SQLite for Android";
	
	public DialectAwareJooqFactory(Connection connection) {
		super(connection, getDialect(connection));
	}
	
	public Settings getJooqSettings(){
		return getSettings();
	}

	private static SQLDialect getDialect(Connection conn) {
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			String dbName = metaData.getDatabaseProductName();
			if ( dbName.equals(APACHE_DERBY_DBNAME) ) {
				return SQLDialect.DERBY;
			} else if ( dbName.equals(POSTGRESQL_DBNAME) ) {
				return SQLDialect.POSTGRES;
			} else if ( dbName.equals(SQLITE_DBNAME) ) {
				return SQLDialect.SQLITE;
			} else {
				throw new IllegalArgumentException("Unknown database "+dbName);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error getting database name", e);
		}
	}

	public int getNextIdValue(String tableName){
		//System.out.println("NO SEQUENCES!!!!!!!!!!!!!!!!!!!!!!"+tableName);
		SelectLimitStep q = this.select()
				.from(tableName)
				.orderBy(Factory.literal("id").desc());
		Result<Record> result = q.fetch();
//		System.out.println("QUERY:"+q.toString());
//		System.out.println("ILOSCwierszy"+result.size());
//		System.out.println(result.formatCSV());
//		System.out.println("NAJWYZSZE ID"+result.get(0).getValueAsString(0));
//		System.out.println("NAJWYZSZE ID"+result.get(0).getValueAsString(1));
//		System.out.println("NAJWYZSZE ID"+result.get(0).getValueAsString(2));
//		if (result.size()==0){
//			this.execute("insert into ofc_user_role_id_seq values (1);");	
//		}
//		else {
//			this.execute("UPDATE ofc_user_role_id_seq SET nextval = 2;");
//		}
		if (!result.isEmpty())
			return result.get(0).getValueAsInteger(0)+1;
		else 
			return 1;
    }

}
