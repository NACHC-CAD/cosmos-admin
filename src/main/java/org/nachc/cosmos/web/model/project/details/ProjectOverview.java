package org.nachc.cosmos.web.model.project.details;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjUrlDvo;
import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjectDvo;
import org.nachc.cosmos.web.proxy.ProjectOverviewProxy;
import org.yaorma.dao.Dao;

public class ProjectOverview {

	public static ProjectOverviewProxy get(String projectCode, Connection conn) {
		ProjectOverviewProxy rtn = new ProjectOverviewProxy();
		ProjectDvo projectDvo = Dao.find(new ProjectDvo(), "code", projectCode, conn);
		rtn.setProjectDvo(projectDvo);
		List<ProjUrlDvo> projUrlDvoList = Dao.findListBySql(new ProjUrlDvo(), "select * from proj_url where project = ?", projectCode, conn);
		rtn.setProjUrlDvoList(projUrlDvoList);
		return rtn;
	}
	
}
