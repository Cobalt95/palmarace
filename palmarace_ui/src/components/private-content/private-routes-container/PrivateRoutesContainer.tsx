import { Outlet, useNavigate } from "react-router-dom"
import { isAuthenticated } from "../../../helper/axios-helper"
import { useEffect } from "react";
import Footer from "../footer/Footer";
import Header from "../header/Header";
import "./PrivateRoutesContainer.scss"

const PrivateRoutesContainer = () => {
    
    // state & data
    const navigate = useNavigate();
    useEffect(() => {
        if(!isAuthenticated()) {
            navigate("/login")
        }
    }, [])
    // handlers
    // render
    return (
        <div className="main-container">
            <Header />
            <Outlet />
            <Footer />
        </div>
    )
}

export default PrivateRoutesContainer;