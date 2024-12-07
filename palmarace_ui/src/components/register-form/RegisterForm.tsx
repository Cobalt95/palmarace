import { BaseSyntheticEvent, useState } from "react";
import { request, setAuthToken } from "../../helper/axios-helper";

type RegisterFormProps = {
    title: string;
    toggleLogin: () => void;
    displayPrivateContent: () => void;
}

const RegisterForm = ( {title, toggleLogin, displayPrivateContent} : RegisterFormProps) => {
    // state
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [dateBirth, setDateBirth] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    // handlers
    const handlefirstNameUpdate = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        setFirstName(event.target.value);
    }
    const handleLastNameUpdate = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        setLastName(event.target.value);
    }
    const handleDateBirthUpdate = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        setDateBirth(event.target.value);
    }
    const handleEmailUpdate = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        setEmail(event.target.value);
    }
    const handlePasswordUpdate = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        setPassword(event.target.value);
    }
    const handleSubmit = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        request("/register", "POST", {
            firstName,
            lastName,
            dateBirth,
            email,
            password,
            countryCode: "FR"
        }).then((response) => {
            setAuthToken(response.headers["authorization"]);
            displayPrivateContent();
        })
    }
    // render
    return (
        <div className="register-form-container">
            <form onSubmit={handleSubmit}>
                <h1>{title}</h1>
                <div className="input-container">
                    <label htmlFor="registerFormFirstName">First name</label><input id="registerFormFirstname" type="text" value={firstName} onChange={handlefirstNameUpdate}/>
                    <label htmlFor="registerFormLastName">Last name</label><input id="registerFormLastName" type="text" value={lastName} onChange={handleLastNameUpdate}/>
                    <label htmlFor="registerFormDateBirth">Date of birth</label><input id="registerFormDateBirth" type="date" value={dateBirth} onChange={handleDateBirthUpdate}/>
                    <label htmlFor="registerFormEmail">Email</label><input id="registerFormEmail" type="email" value={email} onChange={handleEmailUpdate}/>
                    <label htmlFor="registerFormPassword">Password</label><input id="registerFormPassword" type="password" value={password} onChange={handlePasswordUpdate}/>
                    {/* <label htmlFor="registerFormDateBirth">Country</label><input id="registerFormDateBirth" type="text" value={bio} onChange={handleDateBirthUpdate}/> */}
                </div>
                <button>Sign In</button>
                <a onClick={toggleLogin}>Already have an account ?</a>
            </form>
        </div>
    )
}

export default RegisterForm;