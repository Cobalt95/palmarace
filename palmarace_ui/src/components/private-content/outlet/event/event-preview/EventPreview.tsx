import { Link } from "react-router-dom";
import "./EventPreview.scss"

type EventPreviewProps = {
    type: "next" | "last" | "new"
};

const EventPreview = ({type}:EventPreviewProps) => {

    // state
    // handlers
    // render
    return (
        <div>
            <h2>{type}</h2>
            <div>
                {type === "new" ? (
                        <Link to="/event-create" className="no-after">
                            <button className="new-event">+</button>
                        </Link>
                    ) : (
                        <img src={type === "next" ? "ironman-nice.png" : "semi-mont-tremblant.png"} width="250px" height="250px"></img>
                    )
                }
            </div>
        </div>
    )
}

export default EventPreview;