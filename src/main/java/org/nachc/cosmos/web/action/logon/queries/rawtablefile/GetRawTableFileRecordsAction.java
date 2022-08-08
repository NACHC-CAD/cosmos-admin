package org.nachc.cosmos.web.action.logon.queries.rawtablefile;

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
import org.nachc.cosmos.web.util.query.rawtablefile.GetRawTableFileRecords;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetRawTableFileRecordsAction extends HttpServlet {

	@Resource(lookup = "java:/MySqlDS")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String project = req.getParameter("project");
			String orgCode = req.getParameter("orgCode");
			String dataLot = req.getParameter("dataLot");
			String forwardTo = req.getParameter("forwardTo");
			log.info("Getting data lots for: " + orgCode + " (" + project + ")");
			log.info("Forward: " + forwardTo);
			List<RawTableFileDvo> rawTableFileRecords = GetRawTableFileRecords.forOrgProjectLot(orgCode, project, dataLot, conn);
			log.info("Got " + rawTableFileRecords.size() + " records.");
			req.setAttribute("rawTableFileRecords", rawTableFileRecords);
			// forward request
			RequestDispatcher disp = req.getRequestDispatcher(forwardTo);
			disp.forward(req, resp);
			log.info("Done with getRawTableFileRecordsAction.");
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			Database.close(conn);
		}
	}

}
