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

type RegisterFormFields = {
    firstName: string,
    lastName: string,
    dateBirth: string,
    email: string,
    password: string,
    countryCode: string
}

const RegisterForm = () => {
    // state
    const [registerFormFields, setRegisterFormFields] = useState<RegisterFormFields>({
        firstName: "",
        lastName: "",
        dateBirth: "",
        email: "",
        password: "",
        countryCode: ""
    });
    const [countryList, setCountryList] = useState<CountryData[]>([]);
    const navigate = useNavigate();

    const {firstName, lastName, dateBirth, email, password, countryCode} = registerFormFields;

    useEffect(() => {
        fetch("https://restcountries.com/v3.1/all?fields=name,cca2")
        .then(response => response.json()) 
        .then(countriesDataAPI => {
            const convertedCountryArray: CountryData[] = countriesDataAPI.map((country: CountryDataAPI) => ({name: country.name.common, cca2: country.cca2}));
            convertedCountryArray.sort((countryA, countryB) => countryA.name > countryB.name ? 1 : -1);
            setRegisterFormFields({
                ...registerFormFields,
                countryCode: convertedCountryArray[0].cca2
            });
            setCountryList(convertedCountryArray);
    })
    }, []);

    // handlers
    const handleFieldUpdate = (e:BaseSyntheticEvent) => {
        e.preventDefault();
        setRegisterFormFields({
            ...registerFormFields,
            [e.target.name]: e.target.value
        });
    }
    const handleSubmit = (event:BaseSyntheticEvent) => {
        event.preventDefault();
        request("/register", "POST", registerFormFields)
        .then((response) => {
            setAuthToken(response.headers["authorization"]);
        })
        .then(() => {
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
                            <label htmlFor="registerFormFirstName">First name</label><input name="firstName" id="registerFormFirstname" type="text" value={firstName} onChange={handleFieldUpdate}/>
                            <label htmlFor="registerFormLastName">Last name</label><input name="lastName" id="registerFormLastName" type="text" value={lastName} onChange={handleFieldUpdate}/>
                            <label htmlFor="registerFormEmail">Email</label><input name="email" id="registerFormEmail" type="email" value={email} onChange={handleFieldUpdate}/>
                            <label htmlFor="registerFormPassword">Password</label><input name="password" id="registerFormPassword" type="password" value={password} onChange={handleFieldUpdate}/>
                            <label htmlFor="registerFormDateBirth">Date of birth</label><input name="dateBirth" id="registerFormDateBirth" type="date" value={dateBirth} onChange={handleFieldUpdate}/>
                            <label htmlFor="registerFormCountry">Country</label>
                            <select name="countryCode" id="registerFormCountry" value={countryCode} onChange={handleFieldUpdate}>
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
export type {CountryData, CountryDataAPI};