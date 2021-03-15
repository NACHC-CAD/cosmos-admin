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
import org.nachc.cosmos.web.util.listener.OutputStreamListener;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.web.listener.Listener;

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
		log.debug("----------");
		log.debug("Doing post");
		resp.setContentType("application/text");
		OutputStream out = resp.getOutputStream();
		Listener lis = new OutputStreamListener(out);
		log(lis, "Writing file to COSMOS...");
		out.flush();
		writeZipFileToDisc(req, resp, lis);
		log(lis, "\n\nDone.\n");
		out.flush();
	}

	private void writeZipFileToDisc(HttpServletRequest req, HttpServletResponse resp, Listener lis) throws ServletException, IOException {
		log(lis, "------");
		log(lis, "Getting connections");
		CosmosConnections conns = new CosmosConnections(mysqlDs, databricksDs);
		log(lis, "Got connections");
		log(lis, "------");
		try {
			log(lis, "Getting file");
			Part filePart = req.getPart("file");
			InputStream in = filePart.getInputStream();
			String fileName = filePart.getSubmittedFileName();
			log(lis, "Got file: " + fileName);
			File uploadDir = getUploadFileDir(fileName, lis);
			File file = new File(uploadDir, fileName);
			log(lis, "Wrting file");
			FileUtil.write(in, file);
			log(lis, "Done writing file");
			log(lis, "Zipfile size on disc: " + file.length());
			log(lis, "Unzipping");
			File srcDir = ZipUtil.unzip(file, file.getParentFile());
			log(lis, "Done unzipping");
			log(lis, "Source Dir: " + FileUtil.getCanonicalPath(srcDir));
			log(lis, "Done writing file to server");
			log(lis, "------");
			log(lis, "Uploading data to Databricks");
			// TODO: FIX UID HERE
			UploadDir.uploadDir(srcDir, "greshje", conns, lis);
			conns.commit();
		} finally {
			conns.close();
		}
	}
	
	private File getUploadFileDir(String fileName, Listener lis) {
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
		log(lis, "Upload dir: " + FileUtil.getCanonicalPath(rtn));
		log(lis, "Dir created: " + success);
		return rtn;
	}
	
	private void log(Listener lis, String str) {
		log.info(str);
		lis.notify(str);
	}
	
}
