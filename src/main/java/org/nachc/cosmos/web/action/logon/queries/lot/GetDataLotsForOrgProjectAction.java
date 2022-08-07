package org.nachc.cosmos.web.action.logon.queries.lot;

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

import org.nachc.cad.cosmos.dvo.mysql.cosmos.RawTableFileDvo;
import org.nachc.cosmos.web.util.query.lot.GetLots;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetDataLotsForOrgProjectAction extends HttpServlet{

	@Resource(lookup = "java:/MySqlDS")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String projectGuid = req.getParameter("projectGuid");
			String orgCode = req.getParameter("orgCode");
			String forwardTo = req.getParameter("forwardTo");
			log.info("Getting data lots for: " + orgCode + " (" + projectGuid + ")");
			log.info("Forward: " + forwardTo);
			List<RawTableFileDvo> dataLots = GetLots.forOrgProject(orgCode, projectGuid, conn);
			req.setAttribute("dataLots", dataLots);
			// forward request
			RequestDispatcher disp = req.getRequestDispatcher(forwardTo);
			disp.forward(req, resp);
			log.info("Done with getDataLotsForOrgProject.");			
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
	}

}
