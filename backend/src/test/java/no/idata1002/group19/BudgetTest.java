package no.idata1002.group19;

import nl.altindag.log.LogCaptor;
import no.idata1002.group19.domain.entity.Budget;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetTest {

    @Test
    void testConstructorValidInputs() {
        LocalDate startDate = LocalDate.of(2023, 4, 18);
        LocalDate endDate = LocalDate.of(2023, 6, 18);
        Budget budget = new Budget(startDate, endDate, 0);
        assertEquals(budget.getStartDate().toString(), "2023-04-18");
    }

    @Test
    void testConstructorInvalidInputs() {
        String expectedWarningMasage = "Invalid input: start date or end date is null, or end date is before start date";
        LogCaptor logCaptor = LogCaptor.forClass(Budget.class);
        LocalDate localDate1 = LocalDate.of(2023,4,18);
        LocalDate localDate2 = LocalDate.of(2023,4,16);
        Budget budget = new Budget(localDate1, localDate2, 0);
        assertThat(logCaptor.getWarnLogs().contains(expectedWarningMasage));
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
