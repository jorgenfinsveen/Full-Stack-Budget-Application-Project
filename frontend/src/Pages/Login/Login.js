import './Login.css';
import { ActiveLink } from "../../Components/Routing/ActiveLink";
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faRightToBracket, faUser } from '@fortawesome/free-solid-svg-icons';



export function Login() {
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
                            />
                        </li>
                    </ul>
                    <Button id="login-button" variant = "contained">
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