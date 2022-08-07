package org.nachc.cosmos.web.action.logon.tools.deletelot;

import java.io.IOException;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteLotHomeAction extends HttpServlet {

	private static final String FORWARD = "/WEB-INF/jsp/pages/cosmosprojects/tools/delete-lot/DeleteLot.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Loading data for delete lot tool...");
		log.info("Forwarding to: " + FORWARD);
		RequestDispatcher disp = req.getRequestDispatcher(FORWARD);
		disp.forward(req, resp);
	}

}
