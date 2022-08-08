package org.nachc.cosmos.web.util.query.rawtablefile;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.RawTableFileDvo;
import org.yaorma.dao.Dao;

public class GetRawTableFileRecords {

	public static List<RawTableFileDvo> forOrgProjectLot(String org, String project, String lot, Connection conn) {
		String sqlString = "select * from raw_table_file where org_code = ? and project = ? and data_lot = ?";
		String[] params = {org, project, lot};
		List<RawTableFileDvo> rtn = Dao.findListBySql(new RawTableFileDvo(), sqlString, params, conn);
		return rtn;
	}
	
}
