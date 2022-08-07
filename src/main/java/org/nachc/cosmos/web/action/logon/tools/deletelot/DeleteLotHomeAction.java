package org.nachc.cosmos.web.action.logon.tools.deletelot;

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

import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjectDvo;
import org.nachc.cosmos.web.util.query.project.GetAllProjects;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteLotHomeAction extends HttpServlet {

	private static final String FORWARD = "/WEB-INF/jsp/pages/cosmosprojects/tools/delete-lot/DeleteLot.jsp";

	@Resource(lookup = "java:/MySqlDS")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			log.info("Loading data for delete lot tool...");
			conn = ds.getConnection();
			List<ProjectDvo> projectList = GetAllProjects.exec(conn);
			req.setAttribute("projectList", projectList);
			log.info("Got " + projectList.size() + " projects.");
			log.info("Forwarding to: " + FORWARD);
			RequestDispatcher disp = req.getRequestDispatcher(FORWARD);
			disp.forward(req, resp);
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
	}

}
