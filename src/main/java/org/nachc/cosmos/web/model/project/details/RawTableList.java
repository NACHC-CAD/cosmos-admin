package org.nachc.cosmos.web.model.project.details;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.RawTableGroupDvo;
import org.yaorma.dao.Dao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RawTableList {

	public static List<RawTableGroupDvo> get(String projectCode, Connection conn) {
		return Dao.findListBySql(new RawTableGroupDvo(), "select * from raw_table_group where project = ? order by group_table_name", projectCode, conn);
	}
	
}
