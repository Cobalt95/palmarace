import "./Profile.scss";

const Profile = () => {

    // state
    // handlers
    // render
    return (
        <div className="profile-container">
            <div className="profile-details-container">
                <div className="profile-picture-container">
                    <img src="0001.wp.jpg"/>
                </div>
                <div className="profile-infos-container">
                    <ul>
                        <li><div><h1>Romain Mounérat</h1></div></li>
                        <li><div>Montréal, QC <img src="https://flagsapi.com/CA/flat/32.png" /></div></li>
                    </ul>
                </div>
            </div>
            <div className="profile-bio-container">
                <p>Passionate runner who loves chasing personal bests, from 5Ks to marathons. For me, running is about staying healthy, clearing my mind, and connecting with others. I’m always exploring new trails, testing gear, and striving to improve. Proud moment: finishing my first marathon. Let’s hit the road and run together!</p>
            </div>
        </div>
    );
}

export default Profile;