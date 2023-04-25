package no.idata1002.group19;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.User;


/**
 * JUnit test class which tests the different aspects of the creation
 * of a instance of the User database entity.
 */
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
        assertThrows(IllegalArgumentException.class, () -> {
            new User(null, "null", "null", budget);
        });
    }

    @Test
    void testConstructorInvalidInputUsernameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("", "null", "null", budget);
        });
    }

    @Test
    void testConstructorInvalidInputPassNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("null", null, "null", budget);
        });
    }

    @Test
    void testConstructorInvalidInputPassempty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("", null, "null", budget);
        });
    }

    @Test
    void testConstructorInvalidInputRoleNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("null", "null", null, budget);
        });
    }

    @Test
    void testConstructorInvalidInputRoleEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("null", "null", "", budget);
        });
    }

    @Test
    void testIsValidPositive() {
        User user = new User("user", "password", "admin", budget);
        assertTrue(user.isValid());
    }
}
