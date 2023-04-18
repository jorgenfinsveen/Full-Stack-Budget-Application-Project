package no.idata1002.group19.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represent user entity.
 *
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false, updatable=false)
    private long uid;

    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @NotNull
    @Column(name = "pass", nullable = false)
    private String pass;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id", referencedColumnName = "bid")
    private Budget budget;

    private static final Logger LOGGER = LogManager.getLogger(User.class);
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    /**
     * Default contractor for user.
     *
     * @param userName the name of the user.
     * @param pass the password for the user.
     */
    public User(String userName, String pass, String role) {
        try {
            this.userName = stringChecker(userName, "userName");
            this.pass = stringChecker(pass, "pass");
            this.role = stringChecker(role, "role");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warn(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Checks if the string is valid
     *
     * @param string the string you want to check.
     * @param prefiks name of the string.
     * @return if the string is correct it returns the string
     */
    private String stringChecker(String string, String prefiks) {
        if(string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException("The string " + "'" + prefiks + "'" + " cant be empty or null");
        }
        return string;
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public User() {

    }

    /**
     * Returns the user id.
     * @return uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * Returns user name
     * @return userName
     */
    public String getUsername() {
        return userName;
    }

    /**
     * Returns password.
     * @return pass.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Setts the user id
     * @param uid the id of the user that you want.
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * Setts the username.
     * @param userName the name of the user that you want.
     */
    public void setUserName(String userName) {
        try {
            this.userName = stringChecker(userName, "userName");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warn(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Setts the password for the user.
     * @param pass the password of the user that you want.
     */
    public void setPass(String pass) {
        try {
            this.pass = stringChecker(pass, "pass");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warn(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Return role
     * @return role
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Setts the role of the user
     * @param role the role of the user.
     */
    public void setRole(String role) {
        try{
            this.role = stringChecker(role, "role");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warn(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Return budget
     * @return budget
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Setts budget
     * @param budget budget
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    /**
     * Checks of the user is valid.
     *
     * @return boolean statement. True if valid, false if not.
     */
    public boolean isValid() {
        return this.userName != null && !this.userName.trim().isEmpty() &&
                this.pass != null && !this.pass.trim().isEmpty() &&
                this.role != null && !this.role.trim().isEmpty();
    }
}
