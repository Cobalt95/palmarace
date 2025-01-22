import { BaseSyntheticEvent, useEffect, useState } from "react";
import "./ProfileUpdateForm.scss"
import { CountryData, CountryDataAPI } from "../../../../public-content/register-form/RegisterForm";

type ProfileUpdateFormProps = {
    handleCancel: () => void
}

type ProfileUpdateFormFields = {
    firstName: string,
    lastName: string,
    countryCode: string,
    bio: string
}

const ProfileUpdateForm = ({handleCancel}:ProfileUpdateFormProps) => {
    
    // state & data
    const [profileUpdateFormFields, setProfileUpdateFormFields] = useState<ProfileUpdateFormFields>({
        firstName: "",
        lastName: "",
        countryCode: "", 
        bio: ""
    });
    
    const [countryList, setCountryList] = useState<CountryData[]>([]);
    const {firstName, lastName, countryCode, bio} = profileUpdateFormFields;

    useEffect(() => {
            fetch("https://restcountries.com/v3.1/all?fields=name,cca2")
            .then(response => response.json()) 
            .then(countriesDataAPI => {
                const convertedCountryArray: CountryData[] = countriesDataAPI.map((country: CountryDataAPI) => ({name: country.name.common, cca2: country.cca2}));
                convertedCountryArray.sort((countryA, countryB) => countryA.name > countryB.name ? 1 : -1);
                setProfileUpdateFormFields({
                    ...profileUpdateFormFields,
                    countryCode: convertedCountryArray[0].cca2
                });
                setCountryList(convertedCountryArray);
        })
    }, []);
    // handlers
    const handleFieldUpdate = (e:BaseSyntheticEvent) => {
        e.preventDefault();
        setProfileUpdateFormFields({
            ...profileUpdateFormFields,
            [e.target.name]: e.target.value
        });
    }
    const handleSubmit = (e:BaseSyntheticEvent) => {
        e.preventDefault();
        console.log(profileUpdateFormFields);
    }
    // render
    return (
        <div className="profile-update-form-container">
            <form onSubmit={handleSubmit} className="profile-update-form">
                <h1>Update account</h1>
                <div className="input-container">
                    <label htmlFor="profileUpdateFormFirstName">First name</label><input name="firstName" id="profileUpdateFormFirstname" type="text" value={firstName} onChange={handleFieldUpdate}/>
                    <label htmlFor="profileUpdateFormLastName">Last name</label><input name="lastName" id="profileUpdateFormLastName" type="text" value={lastName} onChange={handleFieldUpdate}/>
                    <label htmlFor="profileUpdateFormCountry">Country</label>
                    <select name="countryCode" id="profileUpdateFormCountry" value={countryCode} onChange={handleFieldUpdate}>
                        {countryList.map(country => <option key={country.cca2} value={country.cca2}>{country.name}</option>)}
                    </select>
                    <label htmlFor="profileUpdateFormBio">Bio</label><textarea name="bio" id="profileUpdateFormBio" placeholder="Bio..." value={bio} onChange={handleFieldUpdate}/>
                    <div className="button-container">
                        <button>Save</button>
                        <button type="button" onClick={handleCancel}>Cancel</button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default ProfileUpdateForm;