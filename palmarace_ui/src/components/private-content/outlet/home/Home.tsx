import { useEffect, useState } from "react";
import { request } from "../../../../helper/axios-helper";
import "./Home.scss";
import EventPreview from "../event/event-preview/EventPreview";
import Spinner from "../../../general/Spinner";

const Home = () => {
    // state
    const [username, setUsername] = useState("Guest user");
    const [dataLoaded, setDataLoaded] = useState(false);

    useEffect(() => {
        request("/athlete", "GET").then(response => {
            setUsername(response.data);
            setDataLoaded(true);
        })
    }, []);
    // handlers
    // render
    return (
        <div className="home-container">
            {dataLoaded ? (
                <>
                    <h1>{`Hi ${username}, welcome to Palmarace !`}</h1>
                    <div className="event-preview-container">
                        <EventPreview type="new"/>
                        <EventPreview type="next"/>
                        <EventPreview type="last"/>
                    </div>
                </>
            ) : (
                <Spinner />
            )}
        </div>
    )
}

export default Home;