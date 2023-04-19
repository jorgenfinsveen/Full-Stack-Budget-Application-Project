import { useState } from 'react';
import { ActiveLink } from "../../Components/Routing/ActiveLink";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faRightToBracket, faUser } from '@fortawesome/free-solid-svg-icons';
import { HttpInterface } from '../../Session/HttpInterface';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import './Login.css';



export function Login() {

    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    
    const credentials = {
        username: username,
        password: password
    };

    const handleLogin = () => {
        HttpInterface.authenticateLogin(credentials);
    };

    const handleUsername = (event) => {
        setUsername(event.target.value);
    };

    const handlePassword = (event) => {
        setPassword(event.target.value);
    }

    return (
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
}