import { useEffect, useState } from "react";
import { request } from "../../../helper/axios-helper";
import "./Header.scss"
import ProfileHeader from "./profile-header/ProfileHeader";
import { Link } from "react-router-dom";

const Header = () => {

    // state
    const [username, setUsername] = useState("Guest user");
      useEffect(() => {
          request("/athlete", "GET").then(response => {
              setUsername(response.data);
          })
      }, []);
    // handlers
    
    // render
    return (
        <div className="header-container">
            <Link to="/" className="no-after">
                <div className="header-logo">PALMARACE</div>
            </Link>
            <div className="profile-header-container">
                <ProfileHeader username={username} imgSrc="0001.wp.jpg"/>
            </div>
        </div>
    );
}

export default Header;