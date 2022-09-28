package org.nachc.cosmos.web.util.version;

import org.nachc.cad.cosmos.action.confirm.ConfigurationSummary;
import org.nachc.cad.cosmos.action.confirm.ConfirmConfiguration;
import org.nachc.cad.cosmos.util.connection.CosmosConnections;

public class ApplicationResourcesVersionInfoFactory {

	private static ConfigurationSummary VERSIONS = null;
	
	public static ConfigurationSummary getConfigurationSummary(CosmosConnections conns) {
		if(VERSIONS == null) {
			VERSIONS = ConfirmConfiguration.exec(conns);
		}
		return VERSIONS;
	}

}
