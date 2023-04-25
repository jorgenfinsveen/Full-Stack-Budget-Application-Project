package no.idata1002.group19;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import no.idata1002.group19.domain.entity.Budget;


/**
 * JUnit test class which tests the different aspects of the creation
 * of a instance of the Budget database entity.
 */
@SpringBootTest
public class BudgetTest {

    @Test
    public void testConstructorValidInputs() {
        LocalDate startDate = LocalDate.of(2023, 4, 18);
        LocalDate endDate = LocalDate.of(2023, 6, 18);
        Budget budget = new Budget(startDate, endDate, 0);
        assertNotNull(budget);
        assertEquals("2023-04-18", budget.getStartDate().toString());
        assertEquals("2023-06-18", budget.getEndDate().toString());
        assertEquals(0, budget.getBoundary());
    }

    @Test
    void testIsValidPositive() {
        LocalDate startDate = LocalDate.of(2023, 4, 18);
        LocalDate endDate = LocalDate.of(2023, 4, 19);
        Budget budget = new Budget(startDate, endDate, 0);
        assertTrue(budget.isValid());
    }

    @Test
    void testIsValidNegative() {
        LocalDate startDate = LocalDate.of(2023, 4, 18);
        LocalDate endDate = null;
        Budget budget = new Budget(startDate, endDate, 0);
        assertFalse(budget.isValid());
    }

}
