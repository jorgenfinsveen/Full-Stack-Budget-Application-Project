package no.idata1002.group19.domain.entity;

/**
 * 
 * Represents user credentials, including the username, password, role, and budget ID.
 * 
 * @author Group19
 * @since 16.04.2023
 * @version 23.04.2023
 */
public class UserCredentials {

    private String username;
    private String password;
    private String role;
    private long bid;

    /**
     * Returns the username of this UserCredentials object.
     * 
     * @return the username of this UserCredentials object.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this UserCredentials object to the specified value.
     * 
     * @param username the new username to be set for this UserCredentials object.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of this UserCredentials object.
     * 
     * @return the password of this UserCredentials object.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this UserCredentials object to the specified value.
     * 
     * @param password the new password to be set for this UserCredentials object.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the role of this UserCredentials object.
     * 
     * @return the role of this UserCredentials object.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of this UserCredentials object to the specified value.
     * 
     * @param role the new role to be set for this UserCredentials object.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns the budget ID of this UserCredentials object.
     * 
     * @return the budget ID of this UserCredentials object.
     */
    public long getBid() {
        return bid;
    }

    /**
     * Sets the budget ID of this UserCredentials object to the specified value.
     * 
     * @param bid the new budget ID to be set for this UserCredentials object.
     */
    public void setBid(long bid) {
        this.bid = bid;
    }
}
