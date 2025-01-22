import { BaseSyntheticEvent, useState } from "react";
import "./LoginForm.scss";
import { request, setAuthToken } from "../../../helper/axios-helper";
import { Link, useNavigate } from "react-router-dom";

type LoginFormFields = {
    email: string,
    password: string
}

const LoginForm = () => {

    // state & data
    const [loginFormFields, setLoginFormFields] = useState<LoginFormFields>({
        email: "",
        password: ""
    });

    const {email, password} = loginFormFields;
    const navigate = useNavigate();
    // handlers
    const handleFieldUpdate = (e:BaseSyntheticEvent) => {
        e.preventDefault();
        setLoginFormFields({
            ...loginFormFields,
            [e.target.name]: e.target.value
        });
    }
    const handleSubmit = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        request("/login", "POST", loginFormFields)
        .then((response) => {
            setAuthToken(response.headers["authorization"]);
        })
        .then(() => {
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
                            <label htmlFor="loginFormEmail">Email</label><input name="email" id="loginFormEmail" type="email" value={email} onChange={handleFieldUpdate}/>
                            <label htmlFor="loginFormPassword">Password</label><input name="password" id="loginFormPassword" type="password" value={password} onChange={handleFieldUpdate}/>
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