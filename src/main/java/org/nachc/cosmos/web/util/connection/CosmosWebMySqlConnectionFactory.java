package org.nachc.cosmos.web.util.connection;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CosmosWebMySqlConnectionFactory {

	@Resource(lookup="java:/MySqlDS")
	private DataSource ds;

	private Connection doGetConnection() {
		try {
			log.info("Getting MySql connection");
			log.info("Data source: " + ds);
			Connection conn = ds.getConnection();
			log.info("Got MySql connection");
			return conn;
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
	public static Connection getConnection() {
		return new CosmosWebMySqlConnectionFactory().doGetConnection();
	}
	
}
