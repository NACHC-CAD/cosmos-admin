package org.nachc.cosmos.web.util.query.org;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.OrgCodeDvo;
import org.yaorma.dao.Dao;

public class GetAllOrgs {

	public static List<OrgCodeDvo> exec(Connection conn) { 
		String sqlString = "select * from org_code order by code";
		List<OrgCodeDvo> rtn = Dao.findListBySql(new OrgCodeDvo(), sqlString, conn);
		return rtn;
	}
	
}
