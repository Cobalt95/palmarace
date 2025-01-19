import { useEffect, useState } from "react";
import "./Profile.scss";
import { request } from "../../../../helper/axios-helper";

type AthleteInfos = {
    firstName: string,
    age: number,
    bio: string,
    countryCode: string
}

const Profile = () => {

    // state
    const [athleteInfos, setAthleteInfos] = useState<AthleteInfos>();
    useEffect(() => {
        request("/athleteInfos", "GET").then(response => {
            setAthleteInfos(response.data!);
        })
    }, []);
    // handlers
    // render
    return (
        <div className="profile-container">
            <div className="profile-sub-container">
                <div className="profile-details-container">
                    <div className="profile-picture-container">
                        <img src="0001.wp.jpg"/>
                    </div>
                    <div className="profile-infos-container">
                        <h1>{`${athleteInfos?.firstName}, ${athleteInfos?.age}`}</h1>
                        <img src={`https://flagsapi.com/${athleteInfos?.countryCode}/flat/32.png`} alt={athleteInfos?.countryCode} title={athleteInfos?.countryCode} />
                    </div>
                </div>
                <div className="profile-bio-container">
                    <p>Passionate runner who loves chasing personal bests, from 5Ks to marathons. For me, running is about staying healthy, clearing my mind, and connecting with others. I’m always exploring new trails, testing gear, and striving to improve. Proud moment: finishing my first marathon. Let’s hit the road and run together !</p>
                    {/* <p>{athleteInfos?.bio}</p> */}
                </div>
            </div>
        </div>
    );
}

export default Profile;