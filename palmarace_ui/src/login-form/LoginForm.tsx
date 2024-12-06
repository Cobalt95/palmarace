import { BaseSyntheticEvent, SyntheticEvent, useState } from "react";
import "./LoginForm.scss"
import axios from 'axios';

const LoginForm = () => {

    // state
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    // handlers
    const handleEmailUpdate = (event:BaseSyntheticEvent) => {
        setEmail(event.currentTarget.value);
    }
    const handlePasswordUpdate = (event:BaseSyntheticEvent) => {
        setPassword(event.currentTarget.value);
    }
    const handleSubmit = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        console.log("Called");
        axios.post("http://localhost:8080/login", {
            email,
            password
        },
        {
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => console.log(response.data))
    }
    // render
    return (
        <div className="login-form-container">
            <form onSubmit={handleSubmit} className="login-form">
                <h1>Log in</h1>
                <div className="input-text">
                    <label htmlFor="loginFormEmail">Email</label><input id="loginFormEmail" type="email" value={email} onChange={handleEmailUpdate}/>
                    <label htmlFor="loginFormPassword">Password</label><input id="loginFormPassword" type="password" value={password} onChange={handlePasswordUpdate}/>
                </div>
                <button>Log In</button>
                <a>Create a new account</a>
            </form>
        </div>
    );
}

export default LoginForm;