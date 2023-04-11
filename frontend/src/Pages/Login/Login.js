import './Login.css';
import { Link } from 'react-router-dom';


export function Login() {
    return(
        <article>
            <section>
                <h1>Login</h1>
                <ul>
                    <li>Username: </li>
                    <li><input type="text" className="text-bar"></input></li>
                </ul>
                <ul>
                    <li>Password: </li>
                    <li><input type="text" className="text-bar"></input></li>
                </ul>
                <button>Login</button>
                <p>No Account Register Here</p>
                <Link to="/register"><button>Register</button></Link>
            </section>
        </article>
    )
}