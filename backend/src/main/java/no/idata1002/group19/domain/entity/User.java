package no.idata1002.group19.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

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
    @GeneratedValue
    @Column(name = "uid")
    private long uid;

    @NotNull
    @Column(name = "username", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "pass", nullable = false)
    private String pass;

    @OneToOne(mappedBy = "user")
    private Budget budget;

    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    /**
     * Default contractor for user.
     *
     * @param userName the name of the user.
     * @param pass the password for the user.
     */
    public User(String userName, String pass) {
        try {
            this.userName = stringChecker(userName, "userName");
            this.pass = stringChecker(pass, "pass");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
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
        if(string.isEmpty() || string == null) {
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
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
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
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Checks of the user is valid.
     *
     * @return boolean statement. True if valid, false if not.
     */
    public boolean isValid() {
        return !"".equals(this.userName) && !"".equals(this.pass);
    }
}
