import { useEffect, useState } from "react";
import { request } from "../../../helper/axios-helper";
import "./Header.scss"
import ProfileHeader from "./profile-header/ProfileHeader";

type HeaderProps = {
    hidePrivateContent: () => void;
}

const Header = ({hidePrivateContent}:HeaderProps) => {

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
            <div className="header-logo">PALMARACE</div>
            <div className="profile-header-container">
                <ProfileHeader username={username} imgSrc="0001.wp.jpg" hidePrivateContent={hidePrivateContent}/>
            </div>
        </div>
    );
}

export default Header;