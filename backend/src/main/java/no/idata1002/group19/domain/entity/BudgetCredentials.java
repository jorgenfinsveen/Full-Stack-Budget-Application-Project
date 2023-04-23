package no.idata1002.group19.domain.entity;

/**
 * 
 * Represents budget credentials, including start and end dates and a boundary value.
 * 
 * @author Group19
 * @since 16.04.2023
 * @version 23.04.2023
 */
public class BudgetCredentials {

    private String startDate;
    private String endDate;
    private int boundary;

    /**
     * Gets the start date of the budget period.
     *
     * @return The start date of the budget period.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the budget period.
     *
     * @param startDate The start date of the budget period to set.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the budget period.
     *
     * @return The end date of the budget period.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the budget period.
     *
     * @param endDate The end date of the budget period to set.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the boundary value for the budget.
     *
     * @return The boundary value for the budget.
     */
    public int getBoundary() {
        return boundary;
    }

    /**
     * Sets the boundary value for the budget.
     *
     * @param boundary The boundary value for the budget to set.
     */
    public void setBoundary(int boundary) {
        this.boundary = boundary;
    }
}