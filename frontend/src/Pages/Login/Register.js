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



function DashboardRouting() {
    return (
        <Navigate to="/dashboard"/>
    );
};

function RegisterForm(props) {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [strength, setStrength] = useState('');

    const usernameChange = (event) => {
        setUsername(event.target.value);
    };
    
    const passwordChange = (event) => {
        setPassword(event.target.value);
        setStrength(props.getStrength(password));
    };

    const signup = () => {
        props.handleSignup(username, password);
    };


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

export function Register() {

    const pswrdStrengthLabels = ['weak', 'decent', 'strong'];
    

    const getStrength = (password) => {
        let strenghtIndicator = -1;
        let upper = false;
        let lower = false;
        let numbers = false;

        for (let index = 0; index < password.length; index++) {
            const char = password.charCodeAt(index);
            if (!upper && char >= 65 && char <= 90) {
                upper = true;
                strenghtIndicator++;
            }
            if (!numbers && char >= 48 && char <= 57) {
                numbers = true;
                strenghtIndicator++;
            }
            if (!lower && char >= 97 && char <= 122) {
                lower = true;
                strenghtIndicator++;
            }
            if (password.length < 10) {
                strenghtIndicator = 1;
            }
            if (password.length < 5) {
                strenghtIndicator = 0;
            }
        }
        return pswrdStrengthLabels[strenghtIndicator];
    };

    const handleSignup = async (usr, pswrd) => {
        const user = {
            username: usr,
            password: pswrd,
            role: "",
            bid: 0
        };
        const result = await HttpInterface.signUp(user);

        if (SESSION.getAuth()) setContent(<DashboardRouting/>);
    };
    
    const [content,  setContent]  = useState(
        <RegisterForm
            getStrength={getStrength}
            handleSignup={handleSignup}
        />
    );

    return content;
}