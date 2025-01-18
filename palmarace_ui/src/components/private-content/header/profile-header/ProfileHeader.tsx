import { Link } from "react-router-dom";
import { logout } from "../../../../helper/axios-helper";
import "./ProfileHeader.scss"

type ProfileHeaderProps = {
    username: string,
    imgSrc: string,
};

const ProfileHeader = ({username, imgSrc}:ProfileHeaderProps) => {

    // state
    // handlers
    const handleLogout = () => {
        logout();
    }
    // render
    return (
        <>
            <Link to={"/profile"} className="no-after">
                <div className="profile-header-container">
                    <div className="profile-header-username-container">{username}</div>
                    <div className="profile-header-picture-container">
                        <img src={imgSrc}/>
                    </div>
                </div>
            </Link>
            <Link to="/login" className="no-after" onClick={() => handleLogout()}><button>Log out</button></Link>
        </>
    );
}

export default ProfileHeader;