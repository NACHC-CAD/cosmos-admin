package integration.cosmosadmin.curlew.env;

import org.junit.Test;
import org.nachc.cad.cosmos.action.create.protocol.raw.manual.tools.enviornment.SET_ENV_TO_DEV;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SetEnvToDevManualTest {

	@Test
	public void shouldSetEnvToDev() {
		log.info("Setting env to dev...");
		SET_ENV_TO_DEV.main(null);
		log.info("Done.");
	}
	
}
