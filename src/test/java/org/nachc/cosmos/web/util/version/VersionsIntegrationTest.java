package org.nachc.cosmos.web.util.version;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nachc.cad.cosmos.action.confirm.ConfigurationSummary;
import org.nachc.cad.cosmos.action.create.protocol.raw.manual.tools.enviornment.SET_ENV_TO_DEV;
import org.nachc.cad.cosmos.util.connection.CosmosConnections;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VersionsIntegrationTest {

	@Test
	public void shouldGetVersionInformation() {
		log.info("Setting environment to dev...");
		SET_ENV_TO_DEV.exec();
		log.info("Getting connections...");
		CosmosConnections conns = new CosmosConnections();
		try {
			log.info("Checking config...");
			ConfigurationSummary config = ApplicationResourcesVersionInfoFactory.getConfigurationSummary(conns);
			assertTrue(config.getJavaVersion().startsWith("11"));
			assertTrue("this_is_dev".equals(config.getDatabricksDbInstance()));
			assertTrue("this_is_dev".equals(config.getDatabricksFileStoreInstance()));
			assertTrue("this_is_dev".equals(config.getMySqlDbInstance()));
		} finally {
			log.info("Closing connections...");
			CosmosConnections.close(conns);
		}
		log.info("Done.");
	}
	
}
