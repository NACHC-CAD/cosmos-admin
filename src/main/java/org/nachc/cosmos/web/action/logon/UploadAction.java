package org.nachc.cosmos.web.action.logon;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.nachc.cad.cosmos.util.connection.CosmosConnections;
import org.nachc.cad.cosmos.util.mysql.params.MySqlParams;
import org.nachc.cad.cosmos.util.project.UploadDir;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MultipartConfig
public class UploadAction extends HttpServlet {

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
		log.info("----------");
		log.info("Doing post");
		resp.setContentType("application/text");
		OutputStream out = resp.getOutputStream();
		out.write("Writing file to COSMOS!".getBytes());
		out.flush();
		writeZipFileToDisc(req, resp);
		out.write("\n\nDone.\n".getBytes());
		out.flush();
		log.info("Done.");
	}

	private void writeZipFileToDisc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Getting connection");
		CosmosConnections conns = new CosmosConnections(mysqlDs, databricksDs);
		try {
			log.info("Getting file");
			Part filePart = req.getPart("file");
			InputStream in = filePart.getInputStream();
			String fileName = filePart.getSubmittedFileName();
			log.info("Got file: " + fileName);
			File uploadDir = getUploadFileDir(fileName);
			File file = new File(uploadDir, fileName);
			/*
			log.info("Wrting file");
			FileUtil.write(in, file);
			log.info("Done writing file");
			log.info("Zipfile size on disc: " + file.length());
			log.info("Unzipping");
			File srcDir = ZipUtil.unzip(file, file.getParentFile());
			log.info("Done unzipping");
			log.info("Source Dir: " + FileUtil.getCanonicalPath(srcDir));
			UploadDir.uploadDir(srcDir, "greshje", conns);
			conns.commit();
			*/
		} finally {
			conns.close();
		}
	}
	
	private File getUploadFileDir(String fileName) {
		String serverFilesRoot = MySqlParams.getServerFileRoot();
		File rtn = null;
		int cnt = -1;
		boolean fileExists = true;
		while(fileExists == true) {
			cnt++;
			String cntString = StringUtils.leftPad(cnt + "", 3, "0");
			String dirName = "";
			dirName += serverFilesRoot + "\\";
			dirName += FileUtil.getPrefix(fileName);
			dirName += TimeUtil.getDateAsYyyyMmDd(TimeUtil.getNow(), '-');
			dirName = dirName + "_" + cntString;
			rtn = new File(dirName);
			fileExists = rtn.exists();
		}
		boolean success = rtn.mkdirs();
		log.info("Upload dir: " + FileUtil.getCanonicalPath(rtn));
		log.info("Dir created: " + success);
		return rtn;
	}
	
}
