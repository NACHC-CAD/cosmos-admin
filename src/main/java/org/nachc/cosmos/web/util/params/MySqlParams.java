package org.nachc.cosmos.web.util.params;

import java.util.Properties;

import com.nach.core.util.props.PropertiesUtil;

public class MySqlParams {

	private static final Properties PROPS = PropertiesUtil.getAsProperties("/auth/_web-app-mysql-auth.properties");

	public static String getKey() {
		return PROPS.getProperty("key");
	}
	
	public static String getServerFileRoot() {
		return PROPS.getProperty("sever-file-root");
	}
	
}
