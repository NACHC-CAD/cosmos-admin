package org.nachc.cosmos.web.action.logon.tools.projectgrouptable;

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
import org.nachc.cad.cosmos.util.project.UploadDir;
import org.nachc.cosmos.web.util.listener.OutputStreamListener;

import com.nach.core.util.web.listener.Listener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateProjectGroupTablesAction extends HttpServlet {

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
		log.debug("Doing post");
		String project = req.getParameter("projectPickList");
		resp.setContentType("application/text");
		OutputStream out = resp.getOutputStream();
		Listener lis = new OutputStreamListener(out);
		log(lis, "Creating group tables for project...");
		log(lis, "Project: " + project);
		out.flush();
		CosmosConnections conns = null;
		try {
			log(lis, "------");
			log(lis, "Getting connections");
			conns = CosmosConnections.open(mysqlDs, databricksDs);
			log(lis, "Got connections");
			log(lis, "------");
			log(lis, "Creating group tables for project: " + project);
			UploadDir.uploadCreateGroupTablesOnly(project, conns, lis);
			log(lis, "Done creating Databricks tables.");
		} catch (Throwable thr) {
			PrintStream ps = new PrintStream(out);
			thr.printStackTrace(ps);
			thr.printStackTrace();
			log(lis, "\n\n! ! ! An Error occured processing this request (stactrace is above for reference) ! ! !");
			log(lis, "ERROR MESSAGE: ");
			log(lis, thr.getMessage());
		} finally {
			if (conns != null) {
				conns.close();
			}
		}
		log(lis, "");
		log(lis, "Done.");
	}

	private void log(Listener lis, String str) {
		log.info(str);
		if (lis != null) {
			lis.notify(str);
		}
	}

}
