import { useEffect, useState } from "react";
import { request, setAuthToken } from "../../helper/axios-helper";

type HomeProps = {
    hidePrivateContent: () => void;
}

const Home = ({hidePrivateContent} : HomeProps) => {
    // state
    const [welcomeMessage, setWelcomeMessage] = useState("Hi dear anonymous stranger, welcome to Palmarace !");
    useEffect(() => {
        request("/athlete", "GET").then(response => {
            setWelcomeMessage(response.data);
        })
    }, []);
    // handlers
    // render
    return (
        <div className="home-container">
            <h1>{welcomeMessage}</h1>
            <button onClick={() => {setAuthToken(""); hidePrivateContent()}}>Log out</button>
        </div>
    )
}

export default Home;