package org.nachc.cosmos.web.model.project.details;

import java.sql.Connection;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjectDvo;
import org.yaorma.dao.Dao;

public class Project {

	public static ProjectDvo get(String guid, Connection conn) {
		return Dao.find(new ProjectDvo(), "guid", guid, conn);
	}
	
}
