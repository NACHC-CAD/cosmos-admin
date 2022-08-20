package org.nachc.cosmos.web.action.logon;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.nachc.cad.cosmos.action.create.protocol.raw.params.RawDataFileUploadParams;
import org.nachc.cad.cosmos.util.connection.CosmosConnections;
import org.nachc.cad.cosmos.util.project.UploadDir;
import org.nachc.cosmos.web.util.listener.OutputStreamListener;
import org.nachc.cosmos.web.util.params.MySqlParams;
import org.nachc.cosmos.web.util.validation.ValidationException;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.file.ZipUtil;
import com.nach.core.util.props.PropertiesUtil;
import com.nach.core.util.string.StringUtil;
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
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OutputStream out = resp.getOutputStream();
		Listener lis = new OutputStreamListener(out);
		CosmosConnections conns = null;
		try {
			conns = CosmosConnections.open(mysqlDs, databricksDs);
			processRequest(req, resp, conns, lis);
		} finally {
			log(lis, "Closing connections...");
			if (conns != null) {
				CosmosConnections.close(conns);
			}
			log(lis, "Done closing connections.");
		}

	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp, CosmosConnections conns, Listener lis) throws ServletException, IOException {
		log.debug("----------");
		log.debug("Doing post");
		resp.setContentType("application/text");
		log(lis, "Writing file to COSMOS...");
		log(lis, "Doing validation of request...");
		boolean isValid = validateRequest(req, resp, lis);
		if (isValid) {
			try {
				log(lis, "Writing zip files to disc...");
				writeZipFileToDisc(req, resp, conns, lis);
				log(lis, "Done writing zip files to disc.");
				log(lis, "\n\nDone.");
			} catch (ValidationException exp) {
				log(lis, "\n! ! ! An Error occured processing this file (stactrace is above for reference) ! ! !");
				log(lis, "ERROR MESSAGE: ");
				log(lis, exp.getMessage());
			} catch (Throwable thr) {
				PrintStream ps = new PrintStream(lis.getOut());
				thr.printStackTrace(ps);
				thr.printStackTrace();
				log(lis, "\n\n! ! ! An Error occured processing this file (stactrace is above for reference) ! ! !");
				log(lis, "ERROR MESSAGE: ");
				log(lis, thr.getMessage());
			}
		} else {
			log(lis, "\n--- VALIDATION FAILED ---");
			log(lis, "The selected file could not be processed.");
		}
	}

	private boolean validateRequest(HttpServletRequest req, HttpServletResponse resp, Listener lis) {
		log(lis, "Validating request...");
		try {
			Part filePart = req.getPart("file");
			InputStream in = filePart.getInputStream();
			String fileName = filePart.getSubmittedFileName();
			if (in == null || StringUtil.isEmpty(fileName)) {
				log(lis, "Could not read input file (file was null)");
				log(lis, "Validation failed");
				return false;
			} else {
				log(lis, "Finishing validation");
				in.close();
			}
			log(lis, "Validation complete.");
			return true;
		} catch (Exception exp) {
			log(lis, "Could not read input file.");
			return false;
		}
	}

	private void writeZipFileToDisc(HttpServletRequest req, HttpServletResponse resp, CosmosConnections conns, Listener lis) throws ServletException, IOException {
		// get the connections
		log(lis, "------");
		log(lis, "Getting connections");
		log(lis, "Got connections");
		log(lis, "------");
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
		ZipUtil.unzip(file, file.getParentFile());
		File srcDir = getUnzippedRootFile(file);
		log(lis, "Done unzipping");
		log(lis, "Source Dir: " + FileUtil.getCanonicalPath(srcDir));
		log(lis, "Done writing file to server");
		log(lis, "------");
		String createGroupTables = req.getParameter("createGroupTables");
		log(lis, "Uploading data to Databricks");
		RawDataFileUploadParams rtn = null;
		if ("true".equalsIgnoreCase(createGroupTables)) {
			// TODO: FIX UID HERE (should not be "greshje")
			rtn = UploadDir.uploadDir(srcDir, "greshje", conns, lis, true);
		} else {
			log(lis, "SKIPPING CREATE GROUP TABLES...");
			log(lis, "You will need to create the group tables for this project to propegate the data.");
			rtn = UploadDir.uploadDirFilesOnly(srcDir, "greshje", conns, lis);
		}
		if (rtn.isValidationSuccess() == false) {
			log(lis, "");
			log(lis, "-----------------------");
			log(lis, "UPLOAD FAILED:");
			log(lis, rtn.getValidationMessage());
			log(lis, "-----------------------");
			log(lis, "");
			throw new ValidationException("Validation failed, see specific message above.");
		}
		conns.commit();
	}

	private File getUnzippedRootFile(File srcFile) {
		File dir = srcFile.getParentFile();
		List<File> files = FileUtil.list(dir);
		for (File file : files) {
			if (file.isDirectory()) {
				return file;
			}
		}
		return null;
	}

	private File getUploadFileDir(String fileName, Listener lis) {
		String serverFilesRoot = MySqlParams.getServerFileRoot();
		File rtn = null;
		int cnt = -1;
		boolean fileExists = true;
		while (fileExists == true) {
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
		if (lis != null) {
			lis.notify(str);
		}
	}

}
