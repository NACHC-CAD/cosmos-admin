package org.nachc.cosmos.web.action.logon;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MultipartConfig
public class UploadAction extends HttpServlet {

	@Resource(lookup = "java:/MySqlDS")
	private DataSource ds;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Doing post");
		writeZipFileToDisc(req, resp);
		log.info("Done.");
	}

	private void writeZipFileToDisc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Getting file");
		Part filePart = req.getPart("file");
		InputStream in = filePart.getInputStream();
		String fileName = filePart.getSubmittedFileName();
		log.info("Got file: " + fileName);
		File file = new File("C:\\_WORKSPACES\\_COSMOS_SERVER\\_UPLOAD\\" + fileName);
		log.info("Wrting file");
		FileUtil.write(in, file);
		log.info("Done writing file");
	}
	
	
}
