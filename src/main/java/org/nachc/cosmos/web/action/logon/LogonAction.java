package org.nachc.cosmos.web.action.logon;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogonAction extends HttpServlet {

	@Resource(lookup = "java:/MySqlDS")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Doing logon...");
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		String attempt = req.getParameter("attempt");
		log.info(uid + " (attempt " + attempt + ")");
		if (attempt == null) {
			log.info("FORWARDING TO LOGON PAGE");
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/logon/LogOn.jsp");
			disp.forward(req, resp);
		} else {
			doLogOn(req, resp);
		}
		log.info("Done with logon");
	}

	private void doLogOn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DOING LOG ON");
		boolean isValidLogOn = false;
		if(isValidLogOn) {
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/pages/home/Home.jsp");
			disp.forward(req, resp);
		} else {
			req.setAttribute("msg", "Invalid Logon, Please try again.");
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/jsp/logon/LogOn.jsp");
			disp.forward(req, resp);
		}
	}

}
