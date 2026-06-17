import { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "../../assets/css/ticket.css";

function Ticket() {
    const { bookingId } = useParams();

    const [ticket, setTicket] = useState();
    const [errmsg, setErrmsg] = useState();

    const ticketApi = "http://localhost:8080/api/generate/ticket/";

    const config = {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
        },
    };

    useEffect(() => {
        const fetchTicket = async () => {
            try {
                const response = await axios.get(ticketApi + bookingId,config);
                setTicket(response.data);
                setErrmsg(undefined);
            } catch (err) {
                console.log(err);
                setErrmsg("Failed to load ticket");
            }
        };
        fetchTicket();
    }, []);

    if (errmsg) {
        return (
            <div className="container mt-5">
                <h3 className="text-danger text-center">{errmsg}</h3>
            </div>
        );
    }

    if (!ticket) {
        return (
            <div className="container mt-5">
                <h3 className="text-center">Loading Ticket...</h3>
            </div>
        );
    }

    return (
    <div className="container py-5 d-flex justify-content-center">
        <div className="ticket-card">
            <div className="ticket-header">
                <div>
                    <h2 className="mb-0">
                        <i className="bi bi-airplane-fill me-2"></i>
                        E-Ticket
                    </h2>
                </div>
                <div className="text-end">
                    <h4 className="mb-0">Booking ID</h4>
                    <h3>{ticket.bookingId}</h3>
                </div>
            </div>
            <div className="ticket-route">
                <div className="city">
                    <h1>{ticket.origin}</h1>
                </div>
                <div className="flight-line">
                    <i className="bi bi-airplane-fill"></i>
                </div>
                <div className="city">
                    <h1>{ticket.destination}</h1>
                </div>
            </div>
            <div className="ticket-divider"></div>
            <div className="row p-4">
                <div className="col-md-6 mb-4">
                    <small className="ticket-label">Passenger</small>
                    <h5 className="ticket-value">
                        {ticket.passengerNames?.join(", ")}
                    </h5>
                </div>
                <div className="col-md-6 mb-4">
                    <small className="ticket-label">Seat Number</small>
                    <h5 className="ticket-value">
                        {ticket.seatNumbers?.join(", ")}
                    </h5>
                </div>
                <div className="col-md-6 mb-4">
                    <small className="ticket-label">Flight Name</small>
                    <h5 className="ticket-value">
                        {ticket.flightName}
                    </h5>
                </div>

                <div className="col-md-6 mb-4">
                    <small className="ticket-label">Flight Number</small>
                    <h5 className="ticket-value">
                        {ticket.flightNumber}
                    </h5>
                </div>

                <div className="col-md-6 mb-4">
                    <small className="ticket-label">Departure</small>
                    <h5 className="ticket-value">
                        {new Date(ticket.departureTime).toLocaleString()}
                    </h5>
                </div>
                <div className="col-md-6 mb-4">
                    <small className="ticket-label">Total Fare</small>
                    <h5 className="ticket-value">
                        ₹ {ticket.totalAmount}
                    </h5>
                </div>
            </div>
            <div className="ticket-divider"></div>
            <div className="ticket-footer ">
                <div>
                    <small className="ticket-label">Status</small>
                    <br />
                    <span
                        className={`badge px-3 py-2 ${ticket.bookingStatus === "CONFIRMED"? "bg-success": "bg-warning text-dark"}`}>
                        {ticket.bookingStatus}
                    </span>
                </div>

            </div>

        </div>

    </div>
);
}

export default Ticket;