import './Login.css';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { useState } from 'react';
import { ActiveLink } from "../../Components/Routing/ActiveLink";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faRightToBracket, faUser } from '@fortawesome/free-solid-svg-icons';
import { HttpInterface } from '../../Session/HttpInterface';
import { Navigate } from "react-router-dom";
import { SESSION } from '../../Session/Session';


/**
 * Login-component which represents the login-page to be displayed
 * on the /login path. It provides a form for the user to input its
 * user credentials, and the functionality for forwarding the 
 * credentials to the server for authentication.
 */
export function Login() {

    /** Username which are read from the username input field. */
    let username;

    /** Password which are read from the password input field. */
    let password;



    /**
     * Packages the login-credentials into an object which can be
     * parsed to JSON and passed in the body of the login request
     * performed by the HTTP Interface.
     * 
     * If the user is authenticated, the content which the Login-
     * component returns is replaced with a redirect to the
     * dashboard page.
     */
    const handleLogin = async () => {

        /** 
         * User credentials object which are to be sent to the server.
         */
        const credentials = {
            username: username,
            password: password
        };

        /** Awaits for the HTTP Interface to finish the authentication request. */
        const result = await HttpInterface.authenticateLogin(credentials);

        /* Redorects to the dashboard-page upon authentication. */
        if (SESSION.getAuth()) setContent(dashboardPage);
    };



    /** Updates the username upon change of value in the username input field. */
    const handleUsername = (event) => {
        username = event.target.value;
    };


    /** Updates the password upon change of value in the password input field. */
    const handlePassword = (event) => {
        password = event.target.value;
    };


    /** Redirection component to the dashboard page. */
    const dashboardPage = (
        <Navigate to="/dashboard"/>
    );


    /**
     * Login-page which contains the input fields for username and password as well
     * as buttons for logging in and redirection to the sign-up page.
     */
    const loginPage = (
        <article id="login-article">
            <header id="login-header-container">
                <h1 id="login-header">Login</h1>
            </header>
            <hr/>
            <section id="login-content-section">
                    <ul className="login-ul">
                        <li className="login-list-element">
                            <TextField
                                variant="filled"
                                label="Username"
                                defaultValue=""
                                onChange={handleUsername}
                            />
                        </li>
                    </ul>
                    <ul className="login-ul">
                        <li className="login-list-element">
                            <TextField
                                variant="filled"
                                label="Password"
                                type="password"
                                defaultValue=""
                                onChange={handlePassword}
                            />
                        </li>
                    </ul>
                    <Button id="login-button" variant = "contained" onClick={handleLogin}>
                        Login  &nbsp; <FontAwesomeIcon icon={faRightToBracket} style={{color: "#ffffff",}} />
                    </Button>
                </section>
                <section id="goto-register-section">
                    <h2 id="register-paragraph">Don't have an account?</h2>
                    <ActiveLink to="/register" id="register-link">
                        <Button id="goto-register-button" variant = "contained"> 
                            Create Account &nbsp; <FontAwesomeIcon icon={faUser} style={{color: "#ffffff",}} />
                        </Button>
                    </ActiveLink>
                </section>
            </article>
    );


    /**
     * Content to be returned by the Login-component. It is supervised
     * by the useState-hook.
     */
    const [content,  setContent]  = useState(loginPage);


    return content;
}