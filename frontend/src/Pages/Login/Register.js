import './Register.css';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { useState, useEffect } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { ActiveLink } from "../../Components/Routing/ActiveLink";
import { SESSION } from '../../Session/Session';
import { HttpInterface } from '../../Session/HttpInterface';
import { Navigate } from "react-router-dom";



/**
 * Component which redirects to the Dashboard component.
 */
function DashboardRouting() {
    return (
        <Navigate to="/dashboard"/>
    );
};




/**
 * Sign-up form component which provides the input fields
 * for the user to write its preferred user credentials as
 * well as buttons for signing up and to return to the
 * login-page.
 * 
 * @param props.getStrength  The getStrength function which is used to determine password strength.
 * @param props.handleSignup The handleSignUp function which is used to send a sign-up request.
 */
function RegisterForm(props) {

    /** Username which are read from the username input field. */
    const [username, setUsername] = useState('');

    /** Password which are read from the password input field. */
    const [password, setPassword] = useState('');

    /** Password strength which indicates the strength of the given password. */
    const [strength, setStrength] = useState('');



    /**
     * Updates the username with the value read from the username input-field.
     * 
     * @param event The component which triggers the function.
     */
    const usernameChange = (event) => {
        setUsername(event.target.value);
    };
    


    /**
     * Updates the password with the value read from the password input-field 
     * and determines the strength of the new password.
     * 
     * @param event The component which triggers the function.
     */
    const passwordChange = (event) => {
        setPassword(event.target.value);
        setStrength(props.getStrength(password));
    };



    /**
     * Triggers the handleSignUp function provided through the
     * props parameter of the RegisterForm component and passes
     * the username and the password read from the input-fields
     * as arguments.
     */
    const signup = () => {
        props.handleSignup(username, password);
    };



    /**
     * UseEffect hook which calls on the getStrength function provided
     * by the props parameter of the RegisterForm component and updates
     * the strength variable upon changes of the password variable.
     */
    useEffect(() => {
        setStrength(props.getStrength(password));
    }, [password]);


    
    return (
        <article id="register-article">
            <div id="register-header-container">
                <h1 id="register-header">Registration</h1>
            </div>
            <hr/>
            <form className="login-form">
                <br/>
                <TextField
                    variant="filled"
                    label="Username"
                    className="register-credentials-input"
                    onChange={usernameChange}
                    defaultValue=""
                />
                <br/>
                <TextField
                    variant="filled"
                    label="Password"
                    type="password"
                    className="register-credentials-input"
                    onChange={passwordChange}
                    defaultValue=""
                />
                <br/>
                <div className={`strength-bar ${strength}`}>
                    <div></div>
                </div>
                <div id="strength-label">
                    {strength && <>{strength} password</>}
                </div>
                <div id="button-bar">
                    <div id="return-button-container">
                        <ActiveLink to="/login" id="register-link">
                            <Button id="goto-register-button" variant = "outlined"> 
                                Have an account?
                            </Button>
                        </ActiveLink>
                    </div>
                    <div id="sign-up-button-container">
                        <Button id="sign-up-button" variant="contained" onClick={signup}>
                            Sign Up &nbsp; <FontAwesomeIcon icon={faUser} style={{color: "#ffffff",}} />
                        </Button>
                    </div>
                </div>
            </form>
        </article>
    );
};




/**
 * Register-component which represents the sign-up-page to be displayed
 * on the /register path. It provides a form for the user to input its
 * preferred user credentials, and the functionality for forwarding the 
 * credentials to the server for registration and authentication.
 */
export function Register() {

    /** Labels indicating the strength of the password. */
    const pswrdStrengthLabels = ['weak', 'decent', 'strong'];
    

    /**
     * Determines the strength of the provided password based
     * on requirements related to the length of the password,
     * whether it contains numbers, and upper- and lowercase 
     * letters.
     * 
     * @param password {String} The password which are to be reviewed.
     * @return {String} The label corresponding to the strength of the password.
     */
    const getStrength = (password) => {
        let strenghtIndicator = -1;
        let upper = false;
        let lower = false;
        let numbers = false;

        /* Iterates through each character in the password. */
        for (let index = 0; index < password.length; index++) {

            /** The ACII-code of the gicen character. */
            const char = password.charCodeAt(index);

            /* Determines whether the character is an uppercase letter. */
            if (!upper && char >= 65 && char <= 90) {
                upper = true;
                strenghtIndicator++;
            }

            /* Determines whether the character is a number. */
            if (!numbers && char >= 48 && char <= 57) {
                numbers = true;
                strenghtIndicator++;
            }

            /* Determines whether the character is an lowercase letter. */
            if (!lower && char >= 97 && char <= 122) {
                lower = true;
                strenghtIndicator++;
            }

            /* Determines whether the password is shorter than 10 symbols. */
            if (password.length < 10) {
                strenghtIndicator = 1;
            }

             /* Determines whether the password is shorter than 5 symbols. */
            if (password.length < 5) {
                strenghtIndicator = 0;
            }
        }
        return pswrdStrengthLabels[strenghtIndicator];
    };



    /**
     * Creates an object with the username and password along with
     * required parameters which are not taken into consideration by
     * the server. The object is provided to the HTTP Interface to
     * parse into a JSON object and forward to the server in the
     * request body.
     * 
     * Upon a succesful signup, the HTTP Interface will automatically
     * send a login request in order to receive a JSON Web Token and the
     * ID of the budget which has been assigned to the new user.
     * 
     * If the signup resulted in a succesful authentication after login,
     * the Register-component will return a redirection to the dashboard-
     * page.
     * 
     * @param usr   {String} Username to sign up with.
     * @param pswrd {String} Password to sign up with.
     */
    const handleSignup = async (usr, pswrd) => {

        /** Sign-up credentials which are to be forwarded to the server. */
        const user = {
            username: usr,
            password: pswrd,
            role: "",
            bid: 0
        };

        /** Response received from the server after sign-up request. */
        const result = await HttpInterface.signUp(user);

        /* Updates the content which are to be returned by the component upon authentication. */
        if (SESSION.getAuth()) setContent(<DashboardRouting/>);
    };
    


    /**
     * Content to be returned by the Register-component. It is 
     * supervised by a useState hook, and has a default value
     * which is the RegisterForm component.
     */
    const [content,  setContent]  = useState(
        <RegisterForm
            getStrength={getStrength}
            handleSignup={handleSignup}
        />
    );



    return content;
}