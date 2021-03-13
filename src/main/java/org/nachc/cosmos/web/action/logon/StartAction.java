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
		Connection conn = null;
		try {
			log.info("Doing Start...");
			req.setAttribute("msg", "Logon Successful");
			// get the connection
			log.info("Datasource: " + ds);
			conn = ds.getConnection();
			log.info("Got connection: " + conn);
			// get the projects
			List<ProjectDvo> projectList = ProjectList.getProjects(conn);
			req.setAttribute("projectList", projectList);
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
