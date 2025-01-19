import "./EventPreview.scss"

type EventPreviewProps = {
    type: "next" | "last"
};

const EventPreview = ({type}:EventPreviewProps) => {

    // state
    // handlers
    // render
    return (
        <div>
            <h2>{type} event</h2>
            <div><img src={type === "next" ? "ironman-nice.png" : "semi-mont-tremblant.png"} width="250px" height="250px"></img></div>
        </div>
    )
}

export default EventPreview;