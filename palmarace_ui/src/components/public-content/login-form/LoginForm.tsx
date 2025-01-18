import { BaseSyntheticEvent, useState } from "react";
import "./LoginForm.scss";
import { request, setAuthToken } from "../../../helper/axios-helper";

type LoginFormProps = {
    title: string;
    toggleRegister: () => void;
    displayPrivateContent: () => void;
}

const LoginForm = ({title, toggleRegister, displayPrivateContent} : LoginFormProps) => {

    // state
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    // handlers
    const handleEmailUpdate = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        setEmail(event.currentTarget.value);
    }
    const handlePasswordUpdate = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        setPassword(event.currentTarget.value);
    }
    const handleSubmit = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        request("/login", "POST", {
            email,
            password
        }).then((response) => {
            setAuthToken(response.headers["authorization"]);
            displayPrivateContent();
        })
    }
    // render
    return (
        <div className="login-form-container">
            <form onSubmit={handleSubmit} className="login-form">
                <h1>{title}</h1>
                <div className="input-container">
                    <label htmlFor="loginFormEmail">Email</label><input id="loginFormEmail" type="email" value={email} onChange={handleEmailUpdate}/>
                    <label htmlFor="loginFormPassword">Password</label><input id="loginFormPassword" type="password" value={password} onChange={handlePasswordUpdate}/>
                </div>
                <button>Log In</button>
                <a onClick={toggleRegister}>Create a new account</a>
            </form>
        </div>
    );
}

export default LoginForm;