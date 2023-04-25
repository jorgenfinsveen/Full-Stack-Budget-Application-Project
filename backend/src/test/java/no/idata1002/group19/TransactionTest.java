package no.idata1002.group19;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import no.idata1002.group19.domain.entity.Budget;
import no.idata1002.group19.domain.entity.Transaction;


/**
 * JUnit test class which tests the different aspects of the creation
 * of a instance of the Transaction database entity.
 */
@SpringBootTest
public class TransactionTest {

    private Budget budget = new Budget();

    @Test
    void testConstructorWhitValidInputs() {
        String tname = "test";
        int value = 10;
        String description = "test test";
        LocalDate localDate = LocalDate.of(2023,04,18);

        Transaction transaction = new Transaction(tname, value, description, localDate, budget);

        assertEquals("test", transaction.getTname());
        assertEquals(10, transaction.getValue());
        assertEquals("test test", transaction.getDescription());
        assertEquals("2023-04-18", transaction.getDate().toString());
    }

    @Test
    void testConstructorInvalidInputTnameNull() {
        LocalDate localDate = LocalDate.of(2023,4,18);
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(null, 10, "null", localDate, budget);
        });
    }

    @Test
    void testConstructorInvalidInputTnameEmpty() {
        LocalDate localDate = LocalDate.of(2023,4,18);
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("", 10, "null", localDate, budget);
        });
    }

    @Test
    void testConstructorInvalidInputDescriptionNull() {
        LocalDate localDate = LocalDate.of(2023,4,18);
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("null", 10, null, localDate, budget);
        });
    }

    @Test
    void testConstructorInvalidInputDescriptionEmpty() {
        LocalDate localDate = LocalDate.of(2023,4,18);
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("null", 10, "", localDate, budget);
        });
    }

    @Test
    void testIsValidPositive() {
        Transaction transaction = new Transaction("Groceries", 50, "Bought groceries for the week", LocalDate.now(), budget);
        assertTrue(transaction.isValid());
    }
}