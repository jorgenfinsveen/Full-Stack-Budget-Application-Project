import './Register.css';

export function Register() {
    return(
        <article>
            <section>
                <h1>Register</h1>
                <ul>
                    <li>Username: </li>
                    <li><input type="text" className="text-bar"></input></li>
                </ul>
                <ul>
                    <li>Password: </li>
                    <li><input type="text" className="text-bar"></input></li>
                </ul>
                <ul>
                    <li>Confirm Password: </li>
                    <li><input type="text" className="text-bar"></input></li>
                </ul>
                <button>Register</button>
            </section>
        </article>
    )
}