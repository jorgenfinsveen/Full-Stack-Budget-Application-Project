import './Register.css';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { ActiveLink } from "../../Components/Routing/ActiveLink";
import { SESSION } from '../../Session/Session';
import { HttpInterface } from '../../Session/HttpInterface';
import { Navigate } from "react-router-dom";

const pswrdStrengthLabels = ['weak', 'decent', 'strong'];

let strength;

export const setStrength = (val) => {strength = val};
export const getStrength2 = () => {return strength};

export function Register() {
    
    let username;
    let password;

    const getStrength = (password) => {
        let strenghtIndicator = -1,
        upper = false,
        lower = false,
        numbers = false;

        if (password === undefined) return;

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
        setStrength(pswrdStrengthLabels[strenghtIndicator]);
    };


    const handleUsernameChange = (event) => {
        username = event.target.value;
    }

    const handlePasswordChange = (event) => {
        password = event.target.value;
        getStrength(password);
    }

    const handleSignup = async () => {
        const user = {
            username: username,
            password: password,
            role: "",
            bid: 0
        };
        const result = await HttpInterface.signUp(user);

        if (SESSION.getAuth() == true) setContent(dashboardPage);
    };

    const dashboardPage = (
        <Navigate to="/dashboard"/>
    );

    const registerPage = (
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
                    onChange={handleUsernameChange}
                    defaultValue=""
                />
                <br/>
                <TextField
                    variant="filled"
                    label="Password"
                    type="password"
                    className="register-credentials-input"
                    onChange={handlePasswordChange}
                    defaultValue=""
                />
                <br/>
                <div className={`strength-bar ${getStrength2()}`}>
                    <div></div>
                </div>
                <div id="strength-label">
                    {getStrength2() && <>{getStrength2()} password</>}
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
                        <Button id="sign-up-button" variant="contained" onClick={handleSignup}>
                            Sign Up &nbsp; <FontAwesomeIcon icon={faUser} style={{color: "#ffffff",}} />
                        </Button>
                    </div>
                </div>

            </form>
        </article>
    );
    const [content,  setContent]  = useState(registerPage);

    return content;
}