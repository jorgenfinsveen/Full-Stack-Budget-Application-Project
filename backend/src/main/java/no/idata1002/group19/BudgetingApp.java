package no.idata1002.group19;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import no.idata1002.group19.domain.entity.User;
import no.idata1002.group19.domain.repository.UserRepository;

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

	@Autowired
	private UserRepository userRepository;

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