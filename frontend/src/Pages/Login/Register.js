import './Register.css';
import { useState } from "react";
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';

const pswrdStrengthLabels = ['weak', 'decent', 'strong'];

export function Register() {
    const [strength, setStrength] = useState("");
    const getStrength = (password) => {
        let strenghtIndicator = -1,
        upper = false,
        lower = false,
        numbers = false;

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

    const handleChange = (event) => getStrength(event.target.value);

    return (
        <article id="register-article">
            <div id="register-header-container">
                <h1 id="register-header">Registration</h1>
            </div>
            <hr/>
            <form className="login-form">
                <TextField
                        variant="filled"
                        label="Full Name"
                        className="register-credentials-input"
                        defaultValue=""
                    />
                <br/>
                <TextField
                    variant="filled"
                    label="Username"
                    className="register-credentials-input"
                    defaultValue=""
                />
                <br/>
                <TextField
                    variant="filled"
                    label="Password"
                    type="password"
                    className="register-credentials-input"
                    onChange={handleChange}
                    defaultValue=""
                />
                <br/>
                <div className={`strength-bar ${strength}`}>
                    <div></div>
                </div>
                <div id="strength-label">
                    {strength && <>{strength} password</>}
                </div>
                <Button id="sign-up-button" variant="contained">
                    Sign Up &nbsp; <FontAwesomeIcon icon={faUser} style={{color: "#ffffff",}} />
                </Button>
            </form>
        </article>
    )
    
}