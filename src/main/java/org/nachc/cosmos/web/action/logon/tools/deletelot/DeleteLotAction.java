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
		resp.setContentType("application/text");
		OutputStream out = resp.getOutputStream();
		Listener lis = new OutputStreamListener(out);
		log(lis, "Deleting data lot from COSMOS...");
		log(lis, "Try number: " + tryNumber);
		out.flush();
		log(lis, "Getting database connection...");
		CosmosConnections conns = new CosmosConnections(mysqlDs, databricksDs);
		log(lis, "Got database connection.");
		try {
			log(lis, "Doing delete...");
			String project = req.getParameter("project");
			String org = req.getParameter("org");
			String dataLot = req.getParameter("dataLot");
			log(lis, "Deleting the following:");
			log(lis, "\tProject: " + project);
			log(lis, "\tOrg:     " + org);
			log(lis, "\tLot:     " + dataLot);
			log(lis, "Done.");
		} catch (Throwable thr) {
			tryNumber++;
			if(tryNumber <= NUMBER_OF_TRIES) {
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
			conns.close();
		}
	}

	
	private void log(Listener lis, String str) {
		log.info(str);
		if (lis != null) {
			lis.notify(str);
		}
	}

}
