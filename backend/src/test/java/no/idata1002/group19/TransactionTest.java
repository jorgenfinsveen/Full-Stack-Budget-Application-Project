package no.idata1002.group19;

import nl.altindag.log.LogCaptor;
import no.idata1002.group19.domain.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    @Test
    void testConstructorWhitValidInputs() {
        String tname = "test";
        int value = 10;
        String description = "test test";
        LocalDate localDate = LocalDate.of(2023,04,18);

        Transaction transaction = new Transaction(tname, value, description, localDate);

        assertEquals("test", transaction.getTname());
        assertEquals(10, transaction.getValue());
        assertEquals("test test", transaction.getDescription());
        assertEquals("2023-04-18", transaction.getDate().toString());
    }

    @Test
    void testConstructorInvalidInputTnameNull() {
        String expectedWarningMassageForTname = "Caught Illegal Argument Exception: The string tname cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(Transaction.class);
        LocalDate localDate = LocalDate.of(2023,4,18);
        Transaction transaction = new Transaction(null, 10, "null", localDate);
        assertThat(logCaptor.getWarnLogs().contains(expectedWarningMassageForTname));
    }

    @Test
    void testConstructorInvalidInputTnameEmpty() {
        String expectedWarningMassageForTname = "Caught Illegal Argument Exception: The string tname cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(Transaction.class);
        LocalDate localDate = LocalDate.of(2023,4,18);
        Transaction transaction = new Transaction("", 10, "null", localDate);
        assertThat(logCaptor.getWarnLogs().contains(expectedWarningMassageForTname));
    }

    @Test
    void testConstructorInvalidInputValueNull() {
        String expectedWarningMassageForValue = "Caught Illegal Argument Exception: The integer value cant be below 0";
        LogCaptor logCaptor = LogCaptor.forClass(Transaction.class);
        LocalDate localDate = LocalDate.of(2023,4,18);
        Transaction transaction = new Transaction("null", -1, "null", localDate);
        assertThat(logCaptor.getWarnLogs().contains(expectedWarningMassageForValue));
    }

    @Test
    void testConstructorInvalidInputDescriptionNull() {
        String expectedWarningMassageForDescription = "Caught Illegal Argument Exception: The string description cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(Transaction.class);
        LocalDate localDate = LocalDate.of(2023,4,18);
        Transaction transaction = new Transaction("null", 10, null, localDate);
        assertThat(logCaptor.getWarnLogs().contains(expectedWarningMassageForDescription));
    }

    @Test
    void testConstructorInvalidInputDescriptionEmpty() {
        String expectedWarningMassageForDescription = "Caught Illegal Argument Exception: The string description cant be empty or null";
        LogCaptor logCaptor = LogCaptor.forClass(Transaction.class);
        LocalDate localDate = LocalDate.of(2023,4,18);
        Transaction transaction = new Transaction("null", 10, "", localDate);
        assertThat(logCaptor.getWarnLogs().contains(expectedWarningMassageForDescription));
    }

    @Test
    void testIsValidPositive() {
        Transaction transaction = new Transaction("Groceries", 50, "Bought groceries for the week", LocalDate.now());
        assertTrue(transaction.isValid());
    }

    @Test
    void testIsValidNegative() {
        Transaction transaction = new Transaction(null, -10, "Invalid transaction", null);
        assertFalse(transaction.isValid());
    }

}