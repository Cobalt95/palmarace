import { useEffect, useState } from "react";
import { request } from "../../../helper/axios-helper";
import "./Home.scss";
import EventPreview from "./event-preview/EventPreview";

const Home = () => {
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
        <div className="home-container">
            <h1>{`Hi ${username}, welcome to Palmarace !`}</h1>
            <div className="event-preview-container">
                <EventPreview type="last"/>
                <EventPreview type="next"/>
            </div>
        </div>
    )
}

export default Home;