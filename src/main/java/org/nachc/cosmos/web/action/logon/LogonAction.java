package org.nachc.cosmos.web.action.logon;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.PersonDvo;
import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjectDvo;
import org.nachc.cad.cosmos.util.connection.CosmosConnections;
import org.nachc.cosmos.web.model.project.list.ProjectList;
import org.nachc.cosmos.web.util.params.MySqlParams;
import org.yaorma.dao.Dao;
import org.yaorma.database.Database;

import com.nach.core.util.web.listener.Listener;
import com.nach.core.util.web.security.PasswordUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogonAction extends HttpServlet {

	@Resource(lookup = "java:/MySqlDS")
	private DataSource mysqlDs;

	@Resource(lookup = "java:/DatabricksDS")
	private DataSource databricksDs;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("--------------------");
		log.info("Getting connection");
		CosmosConnections conns = null;
		try {
			log(null, "Getting database connections...");
			conns = CosmosConnections.open(mysqlDs, databricksDs);
			log(null, "Got database connections.");
			// get the projects
			List<ProjectDvo> projectList = ProjectList.getProjects(conns.getMySqlConnection());
			req.setAttribute("projectList", projectList);
			// forward the request
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/pages/home/Home.jsp");
			disp.forward(req, resp);
		} finally {
			log(null, "Open connections: " + CosmosConnections.getOpenCount());
			log(null, "Closing connections...");
			CosmosConnections.close(conns);
			log(null, "Done closing connections.");
			log(null, "Open connections: " + CosmosConnections.getOpenCount());
		}

	}

	private void log(Listener lis, String str) {
		log.info(str);
		if (lis != null) {
			lis.notify(str);
		}
	}

}
