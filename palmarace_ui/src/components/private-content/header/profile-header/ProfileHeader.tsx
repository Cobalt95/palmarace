import { setAuthToken } from "../../../../helper/axios-helper";
import "./ProfileHeader.scss"

type ProfileHeaderProps = {
    username: string,
    imgSrc: string,
    hidePrivateContent: () => void;
};

const ProfileHeader = ({username, imgSrc, hidePrivateContent}:ProfileHeaderProps) => {

    // state
    // handlers
    const handleLogout = () => {
        setAuthToken("");
        hidePrivateContent();
    }
    // render
    return (
        <>
            <div>{username}</div>
            <div className="header-profile-picture-container">
                <img src={imgSrc}/>
            </div>
            <div><button onClick={() => handleLogout()}>Log out</button></div>
        </>
    );
}

export default ProfileHeader;