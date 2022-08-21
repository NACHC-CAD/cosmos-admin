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
import org.nachc.cad.cosmos.util.connection.CosmosConnections;
import org.nachc.cosmos.web.model.project.list.ProjectList;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartAction extends HttpServlet {

	private static final String FORWARD = "/WEB-INF/jsp/pages/cosmosprojects/cosmos-projects.jsp";
	
	@Resource(lookup="java:/MySqlDS")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CosmosConnections conns = null;
		try {
			log.info("Doing Start...");
			req.setAttribute("msg", "Logon Successful");
			// get the connection
			log.info("Datasource: " + ds);
			conns = CosmosConnections.open(ds, null);
			log.info("Got connection: " + conns.getMySqlConnection());
			// get the projects
			List<ProjectDvo> projectList = ProjectList.getProjects(conns.getMySqlConnection());
			req.setAttribute("projectList", projectList);
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
