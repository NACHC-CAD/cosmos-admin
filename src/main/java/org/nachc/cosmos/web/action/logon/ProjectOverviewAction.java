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
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			log.info("ProjectOverviewAction...");
			String guid = req.getParameter("guid");
			log.info("guid: " + guid);
			// get the connection
			log.info("Datasource: " + ds);
			conn = ds.getConnection();
			log.info("Got connection: " + conn);
			// get the project
			ProjectDvo projectDvo = Project.get(guid, conn);
			req.setAttribute("projectDvo", projectDvo);
			// get the project overview
			ProjectOverviewProxy projectOverviewProxy = ProjectOverview.get(projectDvo.getCode(), conn);
			req.setAttribute("projectOverviewProxy", projectOverviewProxy);
			// get the raw data tables list
			List<RawTableGroupDvo> rawTableGroupList = RawTableList.get(projectDvo.getCode(), conn);
			req.setAttribute("rawTableGroupList", rawTableGroupList);
			// get the raw data file by org list
			List<RawTableDetailDvo> rawTableDetailListByOrg = RawTableDetailList.getByOrg(projectDvo.getCode(), conn);
			req.setAttribute("rawTableDetailListByOrg", rawTableDetailListByOrg);
			// get the raw data file by table list
			List<RawTableDetailDvo> rawTableDetailListByTable = RawTableDetailList.getByTable(projectDvo.getCode(), conn);
			req.setAttribute("rawTableDetailListByTable", rawTableDetailListByTable);
			// forward request
			RequestDispatcher disp = req.getRequestDispatcher(FORWARD);
			disp.forward(req, resp);
			log.info("Done with start.");
		} catch (SQLException exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
	}

}
