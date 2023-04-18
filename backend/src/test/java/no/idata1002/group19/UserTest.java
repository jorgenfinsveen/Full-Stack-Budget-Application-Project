package no.idata1002.group19;

import nl.altindag.log.LogCaptor;
import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    Budget budget = new Budget(LocalDate.now(), LocalDate.now(), 10000);

    @Test
    void testConstructorValidInput() {
        String username = "user";
        String pass = "pass";
        String role = "user";
        User user = new User(username,pass,role, budget);
        assertEquals("user", user.getUsername());
        assertEquals("pass", user.getPassword());
        assertEquals("user", user.getRole());
    }

    @Test
    void testConstructorInvalidInputUsernameNull() {
        String expectedWarningMassageForUserName = "Caught Illegal Argument Exception: The string userName cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(User.class);
        User user = new User(null, "null", "null", budget);
        assertThat(logCaptor.getWarnLogs().contains(expectedWarningMassageForUserName));
    }

    @Test
    void testConstructorInvalidInputUsernameEmpty() {
        String expectedWarningMassageForUserName = "Caught Illegal Argument Exception: The string userName cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(User.class);
        User user = new User("", "null", "null", budget);
        assertThat(logCaptor.getWarnLogs().contains(expectedWarningMassageForUserName));
    }

    @Test
    void testConstructorInvalidInputPassNull() {
        String ectedWarningMassageForPass = "Caught Illegal Argument Exception: The string pass cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(User.class);
        User user = new User("null", null, "null", budget);
        assertThat(logCaptor.getWarnLogs().contains(ectedWarningMassageForPass));
    }

    @Test
    void testConstructorInvalidInputPassempty() {
        String ectedWarningMassageForPass = "Caught Illegal Argument Exception: The string pass cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(User.class);
        User user = new User("", null, "null", budget);
        assertThat(logCaptor.getWarnLogs().contains(ectedWarningMassageForPass));
    }

    @Test
    void testConstructorInvalidInputRoleNull() {
        String ectedWarningMassageForRole = "Caught Illegal Argument Exception: The string role cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(User.class);
        User user = new User("null", "null", null, budget);
        assertThat(logCaptor.getWarnLogs().contains(ectedWarningMassageForRole));
    }

    @Test
    void testConstructorInvalidInputRoleEmpty() {
        String ectedWarningMassageForRole = "Caught Illegal Argument Exception: The string role cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(User.class);
        User user = new User("null", "null", "", budget);
        assertThat(logCaptor.getWarnLogs().contains(ectedWarningMassageForRole));
    }

    @Test
    void testIsValidPositive() {
        User user = new User("user", "password", "admin", budget);
        assertTrue(user.isValid());
    }

    @Test
    void testIsValidNegative() {
        User user = new User("", "", "", budget);
        assertFalse(user.isValid());
    }


}
