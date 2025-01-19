import { BaseSyntheticEvent, useEffect, useState } from "react";
import { request, setAuthToken } from "../../../helper/axios-helper";
import "./RegisterForm.scss"
import { Link, useNavigate } from "react-router-dom";

type CountryDataAPI = {
    name: {
        common: string,
        official: string,
        nativeName: {
            eng: {
                official: string,
                common: string
            }
        }
    },
    cca2: string
}

type CountryData = {
    cca2: string,
    name: string
}

const RegisterForm = () => {
    // state
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [dateBirth, setDateBirth] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [countryCode, setCountryCode] = useState("");

    const [countryList, setCountryList] = useState<CountryData[]>([]);

    const navigate = useNavigate();

    useEffect(() => {
        fetch("https://restcountries.com/v3.1/all?fields=name,cca2")
        .then(response => response.json()) 
        .then(countriesDataAPI => {
            const convertedCountryArray: CountryData[] = countriesDataAPI.map((country: CountryDataAPI) => ({name: country.name.common, cca2: country.cca2}));
            convertedCountryArray.sort((countryA, countryB) => countryA.name > countryB.name ? 1 : -1);
            setCountryCode(convertedCountryArray[0].cca2)
            setCountryList(convertedCountryArray);
    })
    }, []);

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
    const handleCountryUpdate = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        setCountryCode(event.target.value);
    }
    const handleSubmit = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        request("/register", "POST", {
            firstName,
            lastName,
            dateBirth,
            email,
            password,
            countryCode
        }).then((response) => {
            setAuthToken(response.headers["authorization"]);
        }).then(() => {
            navigate("/");
        })
    }
    // render
    return (
        <div className="register-form-container">
            <div className="register-form-sub-container">
                <form onSubmit={handleSubmit} className="register-form">
                    <div>
                        <h1>Create an account</h1>
                        <div className="input-container">
                            <label htmlFor="registerFormFirstName">First name</label><input id="registerFormFirstname" type="text" value={firstName} onChange={handlefirstNameUpdate}/>
                            <label htmlFor="registerFormLastName">Last name</label><input id="registerFormLastName" type="text" value={lastName} onChange={handleLastNameUpdate}/>
                            <label htmlFor="registerFormEmail">Email</label><input id="registerFormEmail" type="email" value={email} onChange={handleEmailUpdate}/>
                            <label htmlFor="registerFormPassword">Password</label><input id="registerFormPassword" type="password" value={password} onChange={handlePasswordUpdate}/>
                            <label htmlFor="registerFormDateBirth">Date of birth</label><input id="registerFormDateBirth" type="date" value={dateBirth} onChange={handleDateBirthUpdate}/>
                            <label htmlFor="registerFormCountry">Country</label>
                            <select id="registerFormCountry" value={countryCode} onChange={handleCountryUpdate}>
                                {countryList.map(country => <option key={country.cca2} value={country.cca2}>{country.name}</option>)}
                            </select>
                        </div>
                    </div>
                    <div>
                        <button>Sign In</button>
                        <Link to="/login">Already have an account ?</Link>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default RegisterForm;