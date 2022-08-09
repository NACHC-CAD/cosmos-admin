package org.nachc.cosmos.web.action.logon.queries.project;

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
import org.nachc.cad.cosmos.dvo.mysql.cosmos.RawTableFileDvo;
import org.nachc.cosmos.web.util.query.lot.GetLots;
import org.nachc.cosmos.web.util.query.project.GetAllProjects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetProjectListAction extends HttpServlet {

	@Resource(lookup = "java:/MySqlDS")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String forwardTo = req.getParameter("forwardTo");
			log.info("Getting projects...");
			log.info("Forward: " + forwardTo);
			List<ProjectDvo> projectList = GetAllProjects.exec(conn);
			req.setAttribute("projectList", projectList);
			// forward request
			RequestDispatcher disp = req.getRequestDispatcher(forwardTo);
			disp.forward(req, resp);
			log.info("Done with getDataLotsForOrgProject.");			
			
		} catch(Throwable thr) {
			throw new RuntimeException(thr);
		}
	}

}
