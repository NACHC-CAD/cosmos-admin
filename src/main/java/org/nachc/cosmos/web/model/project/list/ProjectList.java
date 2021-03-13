package org.nachc.cosmos.web.model.project.list;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjectDvo;
import org.yaorma.dao.Dao;
import org.yaorma.database.Data;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectList {

	public static List<ProjectDvo> getProjects(Connection conn) {
		log.info("Getting projects");
		List<ProjectDvo> rtn = Dao.findListBySql(new ProjectDvo(), "select * from project order by name", conn);
		log.info("Got " + rtn.size() + " projects");
		return rtn;
	}

}
