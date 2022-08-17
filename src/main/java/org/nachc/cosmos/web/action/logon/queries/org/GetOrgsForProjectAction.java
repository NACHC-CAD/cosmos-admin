package org.nachc.cosmos.web.action.logon.queries.org;

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

import org.nachc.cad.cosmos.dvo.mysql.cosmos.OrgCodeDvo;
import org.nachc.cosmos.web.util.query.org.GetAllOrgs;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetOrgsForProjectAction extends HttpServlet {

	@Resource(lookup = "java:/MySqlDS")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String forwardTo = req.getParameter("forwardTo");
			String projectCode = req.getParameter("project");
			log.info("Getting orgs...");
			log.info("Forward: " + forwardTo);
			// get the org list
			List<OrgCodeDvo> orgList = GetAllOrgs.forProject(conn, projectCode);
			req.setAttribute("orgList", orgList);
			RequestDispatcher disp = req.getRequestDispatcher(forwardTo);
			disp.forward(req, resp);
			log.info("Done.");
		} catch(Throwable thr) {
			throw new RuntimeException(thr);
		} finally {
			Database.close(conn);
		}
	}
	
}
