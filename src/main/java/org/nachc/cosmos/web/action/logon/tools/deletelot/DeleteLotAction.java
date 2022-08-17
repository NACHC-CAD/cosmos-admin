package org.nachc.cosmos.web.action.logon.tools.deletelot;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.nachc.cad.cosmos.util.connection.CosmosConnections;
import org.nachc.cosmos.web.util.listener.OutputStreamListener;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.web.listener.Listener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteLotAction extends HttpServlet {

	private static final int NUMBER_OF_TRIES = 5;

	@Resource(lookup = "java:/MySqlDS")
	private DataSource mysqlDs;

	@Resource(lookup = "java:/DatabricksDS")
	private DataSource databricksDs;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("----------");
		log.debug("Processing request...");
		int tryNumber = 0;
		processRequest(req, resp, tryNumber);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp, int tryNumber) throws ServletException, IOException {
		// set up listener to write progress back to client
		resp.setContentType("application/text");
		OutputStream out = resp.getOutputStream();
		Listener lis = new OutputStreamListener(out);
		log(lis, "Deleting data lot from COSMOS...");
		log(lis, "Try number: " + tryNumber);
		out.flush();
		CosmosConnections conns = null;
		try {
			// get the database connection
			log(lis, "Getting database connection...");
			conns = new CosmosConnections(mysqlDs, databricksDs);
			log(lis, "Got database connection.");
			// do the delete
			log(lis, "Doing delete...");
			// get the parameters
			String project = req.getParameter("project");
			String orgCode = req.getParameter("org");
			String dataLot = req.getParameter("dataLot");
			String createGroupTables = req.getParameter("createGroupTablesDeleteFormInput");
			// echo parameters
			log(lis, "Deleting the following:");
			log(lis, "\tProject:\t" + project);
			log(lis, "\tOrg:\t\t" + orgCode);
			log(lis, "\tLot:\t\t" + dataLot);
			log(lis, "Group Tables: " + createGroupTables);
			// do the delete
			log(lis, "Doing the delete...");
			if("true".equalsIgnoreCase(createGroupTables)) {
				org.nachc.cad.cosmos.action.delete.DeleteLotAction.deleteLot(project, orgCode, dataLot, conns, lis);
			} else {
				org.nachc.cad.cosmos.action.delete.DeleteLotAction.deleteLotFiles(project, orgCode, dataLot, conns);
			}
			log(lis, "Done with delete.");
			// done
			log(lis, "");
			log(lis, "Done.");
		} catch (Throwable thr) {
			tryNumber++;
			if (tryNumber <= NUMBER_OF_TRIES) {
				log(lis, "Hmm, couldn't quite connect.  This happens sometimes. Trying again...");
				TimeUtil.sleep(3);
				processRequest(req, resp, tryNumber);
			} else {
				log(lis, "An exception occured...");
				PrintStream ps = new PrintStream(out);
				thr.printStackTrace(ps);
				thr.printStackTrace();
				log(lis, "\n\n! ! ! An Error occured processing this request (stactrace is above for reference) ! ! !");
				log(lis, "ERROR MESSAGE: ");
				log(lis, thr.getMessage());
				throw new RuntimeException(thr);
			}
		} finally {
			if(conns != null) {
				conns.close();
			}
		}
	}

	private void log(Listener lis, String str) {
		log.info(str);
		if (lis != null) {
			lis.notify(str);
		}
	}

}
