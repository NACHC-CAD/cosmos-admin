package org.nachc.cosmos.web.model.project.details;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.RawTableDetailDvo;
import org.yaorma.dao.Dao;

public class RawTableDetailList {

	public static List<RawTableDetailDvo> getByOrg(String projectCode, Connection conn) {
		return Dao.findListBySql(new RawTableDetailDvo(), "select * from raw_table_detail where project = ? order by org_code, group_table_name", projectCode, conn);
	}
	
	public static List<RawTableDetailDvo> getByTable(String projectCode, Connection conn) {
		return Dao.findListBySql(new RawTableDetailDvo(), "select * from raw_table_detail where project = ? order by group_table_name, org_code", projectCode, conn);
	}
	
}
