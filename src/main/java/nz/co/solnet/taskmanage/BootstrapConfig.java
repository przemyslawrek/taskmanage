package solnet;

import nz.co.solnet.helper.DatabaseHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BootstrapConfig implements ServletContextListener{

	private static final Logger logger = LogManager.getLogger(BootstrapConfig.class);
	
	/**
	 * This method gets invoked when the servlet context is initialised.
	 */
	public void contextInitialized(ServletContextEvent sce) {

		DatabaseHelper.initialiseDB();
		logger.info("DB initialised successfully");
	}
	
	/**
	 * This method gets invoked when the servlet context is destroyed.
	 */
	public void contextDestroyed(ServletContextEvent sce) {

		DatabaseHelper.cleanupDB();
		logger.info("DB shutdown successfully");
	}
}
