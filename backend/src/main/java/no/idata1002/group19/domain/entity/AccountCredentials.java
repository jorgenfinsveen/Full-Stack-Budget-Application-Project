
package no.idata1002.group19.domain.entity;

/**
 * 
 * Represents the account credentials entity that contains
 * the user's login information such as username and password.
 * 
 * @author Group19
 * @since 16.04.2023
 * @version 23.04.2023
 */
public class AccountCredentials {

    private String username;
    private String password;

    /**
     * Gets the username of the user.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
