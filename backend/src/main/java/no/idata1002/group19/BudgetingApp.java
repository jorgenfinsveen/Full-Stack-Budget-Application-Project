package no.idata1002.group19;

import java.net.InetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * <h1>Budgeting App</h1>
 * 
 * <p>This class is the main class of the application. It is
 * responsible for launching the Spring Boot application and
 * initialize the processes necessary for service provisions
 * and database administration.</p>
 * 
 * @since 	23.03.2023
 * @version 23.03.2023
 * @author 	IDATA1002_2023_19
 * @see     <a href="https://spring.io">Spring Boot</a>
 */
@SpringBootApplication
public class BudgetingApp implements CommandLineRunner {

	/* Represents the environment in which the application is running. */
	@Autowired
	private Environment environment;

	/** Logger element for logging application events to STDOUT. */
	private static final Logger LOG = LoggerFactory.getLogger(BudgetingApp.class);

	/**
	 * Main method which is called upon application startup.
	 * 
	 * <p>Responsible for launching the Spring application.</p>
	 * 
	 * @param args sequence of arguments provided upon launch execution.
	 */
	public static void main(String[] args) {

		/* Start the Spring application. */
		SpringApplication.run(BudgetingApp.class, args);
	}

	/**
	 * Initializing method inherited from the CommandLineRunner interface.
	 * 
	 * <p>Logs initial status of the application instance.</p>
	 * 
	 * @param args sequence of arguments provided upon launch execution.
	 * @throws Exception upon error during execution.
	 */
	@Override
	public void run(String... args) throws Exception {

		/* Inject IP address and assigned port of active server instance. */
		String ip   = InetAddress.getLocalHost().getHostAddress();
		String port = environment.getProperty("local.server.port");

		/* Log initial status to STDOUT. */
		LOG.info("Server instance initialized: " + ip + ":" + port);

		/* Logs whether the application is running in debug mode or not. */
		String debug = (environment.getProperty("debugging_mode"));
		if (debug != null) {
			debug = debug.trim();
			boolean b = "DEBUG".equalsIgnoreCase(debug);

			LOG.info("Application debug-mode: " + b);
		}
	}
}