package nz.co.solnet.taskmanage;

import jakarta.annotation.PreDestroy;
import nz.co.solnet.taskmanage.helper.DatabaseHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BootstrapConfig  {

	private static final Logger logger = LogManager.getLogger(BootstrapConfig.class);
	
	/**
	 * This method gets invoked when the servlet context is initialised.
	 */
	@Bean
	CommandLineRunner contextInitialized() {

		logger.info("Visit http://server:port/context-path/swagger-ui/index.html to see documentation");
		logger.info("(Default) http://localhost:8080/swagger-ui/index.html ");
		return args -> {
			DatabaseHelper.initialiseDB();
			logger.info("DB initialised successfully");
		};
	}
	
	/**
	 * This method gets invoked when the servlet context is destroyed.
	 */
	@PreDestroy
	public void contextDestroyed() {

		DatabaseHelper.cleanupDB();
		logger.info("DB shutdown successfully");
	}
}
