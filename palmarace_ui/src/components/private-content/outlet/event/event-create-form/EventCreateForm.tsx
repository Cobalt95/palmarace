import { BaseSyntheticEvent, useEffect, useState } from "react"
import "./EventCreateForm.scss"
import { request } from "../../../../../helper/axios-helper"
import { useNavigate } from "react-router-dom"

type EventCreateFormFields = {
    name: string,
    eventDate: string,
    distance: number,
    placeCreationDTO: {
        countryCode: string,
        region: string,
        city: string
    },
    sportCode: string
}

type Sport = {
    sportCode: string,
    name: string
}

const EventCreateForm = () => {

    // state & data

    const navigate = useNavigate();

    const [eventCreateFormFields, setEventCreateFormFields] = useState<EventCreateFormFields>({
        name: "",
        eventDate: new Date().getFullYear() + "-" + String(new Date().getMonth() + 1).padStart(2, "0") + "-" + String(new Date().getDate()).padStart(2, "0"),
        distance: 0.0,
        placeCreationDTO: {
            countryCode: "CA",
            region: "Québec",
            city: "Montréal"
        },
        sportCode: ""
    });
    
    const [sportList, setSportList] = useState<Sport[]>([]);

    useEffect(() => {
        request("/sport/all", "GET").then(response => {
            setEventCreateFormFields({
                ...eventCreateFormFields,
                sportCode: response.data[0].sportCode
            });
            setSportList(response.data);
        })
    }, []);

    const {name, eventDate, distance, sportCode} = eventCreateFormFields;

    // handlers

    const handleFieldUpdate = (e:BaseSyntheticEvent) => {
        e.preventDefault();
        setEventCreateFormFields({
            ...eventCreateFormFields,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmit = (event: BaseSyntheticEvent) => {
        event.preventDefault();
        request("/event/create", "POST", eventCreateFormFields).then(() =>
            navigate("/")
        );
    }

    // render

    return (
        <div className="event-create-form-container">
            <form onSubmit={handleSubmit} className="event-create-form">
                <h1>New event</h1>
                <div className="input-container">
                    <label htmlFor="eventCreateFormName">Event name</label><input name="name" id="eventCreateFormName" type="text" placeholder="Event name..." value={name} onChange={handleFieldUpdate}/>
                    <label htmlFor="eventCreateFormSport">Type of sport</label>
                    <select name="sportCode" id="eventCreateFormSport" value={sportCode} onChange={handleFieldUpdate}>
                        {sportList.map(sport => <option key={sport.sportCode} value={sport.sportCode}>{sport.name}</option>)}
                    </select>
                    <label htmlFor="eventCreateFormDistance">Total distance (km)</label><input name="distance" id="eventCreateFormDistance" type="number" step="0.001" placeholder="0.000" min={0.001} required value={distance > 0 ? distance : undefined} onChange={handleFieldUpdate}/>
                    <label htmlFor="eventCreateFormEventDate">Event date</label><input name="eventDate" id="eventCreateFormEventDate" type="date" value={eventDate} onChange={handleFieldUpdate}/>
                    <div className="button-container">
                        <button>Save</button>
                        <button type="button" onClick={() => navigate(-1)}>Cancel</button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default EventCreateForm;