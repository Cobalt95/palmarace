import { BaseSyntheticEvent, useState } from "react";
import "./LoginForm.scss";
import { request, setAuthToken } from "../../../helper/axios-helper";
import { Link, useNavigate } from "react-router-dom";

const LoginForm = () => {

    // state
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    
    const navigate = useNavigate();
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
        }).then(() => {
            navigate("/");
        })
    }
    // render
    return (
        <div className="login-form-container">
            <div className="login-form-sub-container">
                <form onSubmit={handleSubmit} className="login-form">
                    <div>
                        <h1>Log in</h1>
                        <div className="input-container">
                            <label htmlFor="loginFormEmail">Email</label><input id="loginFormEmail" type="email" value={email} onChange={handleEmailUpdate}/>
                            <label htmlFor="loginFormPassword">Password</label><input id="loginFormPassword" type="password" value={password} onChange={handlePasswordUpdate}/>
                    </div>
                    </div>
                    <div>
                        <button>Log In</button>
                        <Link to="/register">Create a new account</Link>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default LoginForm;