package org.nachc.cosmos.web.action.logon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjectDvo;
import org.nachc.cad.cosmos.dvo.mysql.cosmos.RawTableDetailDvo;
import org.nachc.cad.cosmos.dvo.mysql.cosmos.RawTableGroupDvo;
import org.nachc.cad.cosmos.util.connection.CosmosConnections;
import org.nachc.cosmos.web.model.project.details.Project;
import org.nachc.cosmos.web.model.project.details.ProjectOverview;
import org.nachc.cosmos.web.model.project.details.RawTableDetailList;
import org.nachc.cosmos.web.model.project.details.RawTableList;
import org.nachc.cosmos.web.proxy.ProjectOverviewProxy;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectOverviewAction extends HttpServlet {

	private static final String FORWARD = "/WEB-INF/jsp/pages/project/project.jsp";
	
	@Resource(lookup = "java:/MySqlDS")
	private DataSource mysqlDs;

	@Resource(lookup = "java:/DatabricksDS")
	private DataSource databricksDs;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CosmosConnections conns = null;
		try {
			log.info("ProjectOverviewAction...");
			String guid = req.getParameter("guid");
			log.info("guid: " + guid);
			// get the connection
			conns = CosmosConnections.open(mysqlDs, databricksDs);
			log.info("Got connection: " + conns);
			// get the project
			ProjectDvo projectDvo = Project.get(guid, conns.getMySqlConnection());
			req.setAttribute("projectDvo", projectDvo);
			// get the project overview
			ProjectOverviewProxy projectOverviewProxy = ProjectOverview.get(projectDvo.getCode(), conns.getMySqlConnection());
			req.setAttribute("projectOverviewProxy", projectOverviewProxy);
			// get the raw data tables list
			List<RawTableGroupDvo> rawTableGroupList = RawTableList.get(projectDvo.getCode(), conns.getMySqlConnection());
			req.setAttribute("rawTableGroupList", rawTableGroupList);
			// get the raw data file by org list
			List<RawTableDetailDvo> rawTableDetailListByOrg = RawTableDetailList.getByOrg(projectDvo.getCode(), conns.getMySqlConnection());
			req.setAttribute("rawTableDetailListByOrg", rawTableDetailListByOrg);
			// get the raw data file by table list
			List<RawTableDetailDvo> rawTableDetailListByTable = RawTableDetailList.getByTable(projectDvo.getCode(), conns.getMySqlConnection());
			req.setAttribute("rawTableDetailListByTable", rawTableDetailListByTable);
			// forward request
			RequestDispatcher disp = req.getRequestDispatcher(FORWARD);
			disp.forward(req, resp);
			log.info("Done with start.");
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			CosmosConnections.close(conns);
		}
	}

}
