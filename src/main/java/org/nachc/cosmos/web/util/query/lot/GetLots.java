package org.nachc.cosmos.web.util.query.lot;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.RawTableFileDvo;
import org.yaorma.dao.Dao;

public class GetLots {
	
	public static List<RawTableFileDvo> forOrgProject(String orgCode, String projectGuid, Connection conn) {
		String sqlString = "select distinct data_lot, provided_by, created_date from raw_table_file where org_code = ? and project = ? order by data_lot";
		String[] params = {orgCode, projectGuid};
		List<RawTableFileDvo> rtn = Dao.findListBySql(new RawTableFileDvo(), sqlString, params, conn);
		return rtn;
	}

}
