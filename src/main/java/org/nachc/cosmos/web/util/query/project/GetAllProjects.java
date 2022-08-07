package org.nachc.cosmos.web.util.query.project;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjectDvo;
import org.yaorma.dao.Dao;

public class GetAllProjects {

	public static List<ProjectDvo> exec(Connection conn) {
		String sqlString = "select * from project order by code";
		List<ProjectDvo> rtn = Dao.findListBySql(new ProjectDvo(), sqlString, conn);
		return rtn;
	}

}
