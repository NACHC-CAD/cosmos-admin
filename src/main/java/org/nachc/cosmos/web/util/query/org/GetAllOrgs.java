package org.nachc.cosmos.web.util.query.org;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.OrgCodeDvo;
import org.yaorma.dao.Dao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllOrgs {

	public static List<OrgCodeDvo> exec(Connection conn) { 
		String sqlString = "select * from org_code order by code";
		List<OrgCodeDvo> rtn = Dao.findListBySql(new OrgCodeDvo(), sqlString, conn);
		return rtn;
	}
	
	public static List<OrgCodeDvo> forProject(Connection conn, String projectCode) {
		log.info("Getting orgs for: " + projectCode);
		String sqlString = "select * from org_code where code in (select distinct org_code from raw_table_file where project = ?)";
		List<OrgCodeDvo> rtn = Dao.findListBySql(new OrgCodeDvo(), sqlString, projectCode, conn);
		log.info("Got " + rtn.size() + " orgs.");
		return rtn;
	}
	
}
