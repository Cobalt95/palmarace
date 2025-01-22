import { useEffect, useState } from "react";
import "./Profile.scss";
import { request } from "../../../../helper/axios-helper";
import ProfileUpdateForm from "./profile-update-form/ProfileUpdateForm";
import Spinner from "../../../general/Spinner";

type AthleteInfos = {
    firstName: string,
    age: number,
    bio: string,
    countryCode: string
}

const Profile = () => {

    // state
    const [editMode, setEditMode] = useState(false);
    const [dataLoaded, setDataLoaded] = useState(false);
    const [athleteInfos, setAthleteInfos] = useState<AthleteInfos>({
        firstName: "",
        age: 0,
        bio: "",
        countryCode: ""
    });
    const {firstName, age, bio, countryCode} = athleteInfos;

    useEffect(() => {
        request("/athleteInfos", "GET")
        .then(response => {
            setAthleteInfos(response.data);
            setDataLoaded(true);
        })
    }, []);
    // handlers
    // render
    return (
        <div className="profile-container">
            {editMode ? (
                <div className="profile-sub-container">
                    <ProfileUpdateForm handleCancel={() => setEditMode(false)}/>
                </div>
            ) : (
                <div className="profile-sub-container">
                    {dataLoaded ? (
                        <>
                            <div className="profile-details-container">
                                <div className="profile-picture-container">
                                    <img src="0001.wp.jpg"/>
                                </div>
                                <div className="profile-infos-container">
                                    <h1>{`${firstName}, ${age}`}</h1>
                                    <img src={`https://flagsapi.com/${countryCode}/flat/32.png`} alt={countryCode} title={countryCode} />
                                </div>
                            </div>
                            <div className="profile-bio-container">
                                <p>{bio}</p>
                            </div>
                        </>
                    ) : (
                        <Spinner />
                    )}
                    <button onClick={() => setEditMode(true)}>Edit</button>
                </div>
            )}
        </div>
    );
}

export default Profile;