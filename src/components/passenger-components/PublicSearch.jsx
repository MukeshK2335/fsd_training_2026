import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function PublicSearch() {
    const [origin, setOrigin] = useState();
    const [destination, setDestination] = useState();
    const [scheduleDate, setScheduleDate] = useState();
    const [errmsg, setErrmsg] = useState();
    const [schedule, setSchedule] = useState([]);

    const getScheduleApi="http://localhost:8080/api/schedule/search"
    const navigate=useNavigate()

    const search = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.get(getScheduleApi+`?origin=${origin}&destination=${destination}&date=${scheduleDate}`);
            setSchedule(response.data);
            setErrmsg("");
        } catch (err) {
            console.error(err);
            setSchedule([]);
            setErrmsg("No flights");
        }
    };

    return (
    <div style={{minHeight: "100vh",background: "linear-gradient(to bottom, #f8fbff, #eef4ff)",paddingTop: "130px",paddingBottom: "50px"}}>
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-xl-8 col-lg-9 col-md-11">
                    <div className="card border-0 shadow-lg rounded-4 overflow-hidden">
                        <div className="text-center text-white py-4"style={{background:"linear-gradient(135deg,#0d6efd,#6610f2)"}}>
                            <h1 className="fw-bold mb-2">
                                Search Flights
                            </h1>
                            <p className="mb-0 fs-5">
                                Find your perfect flight
                            </p>
                        </div>
                        <div className="card-body p-4">
                            {errmsg && (
                                <div className="alert alert-danger">
                                    {errmsg}
                                </div>
                            )}
                            <form onSubmit={(e)=>search(e)}>
                                <div className="row g-4">
                                    <div className="col-md-4">
                                        <label className="form-label fw-bold">Origin</label>
                                        <select className="form-select form-select-lg" value={origin}
                                        onChange={(e) =>setOrigin(e.target.value)}>
                                        <option value="">Select Origin</option>
                                            <option>Chennai</option>
                                            <option>Mumbai</option>
                                            <option>Delhi</option>
                                            <option>Kerala</option>
                                        </select>
                                    </div>
                                    <div className="col-md-4">
                                        <label className="form-label fw-bold">Destination</label>
                                        <select className="form-select form-select-lg"value={destination}
                                        onChange={(e) =>setDestination(e.target.value)}>
                                            <option value="">Select Destination</option>
                                            <option>Chennai</option>
                                            <option>Mumbai</option>
                                            <option>Delhi</option>
                                            <option>Kerala</option>
                                        </select>
                                    </div>
                                    <div className="col-md-4">
                                        <label className="form-label fw-bold">Travel Date</label>
                                        <input type="date"className="form-control form-control-lg"value={scheduleDate}
                                        onChange={(e) =>setScheduleDate(e.target.value)}/>
                                    </div>
                                </div>
                                <div className="text-center mt-5">
                                    <button type="submit"className="btn btn-primary btn-lg px-5 rounded-pill shadow">Search Flights</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    {
                    schedule.length > 0 && (
                            <div className="mt-5">
                                <h2 className="fw-bold mb-4">
                                    Available Flights ({schedule.length})
                                </h2>
                                {
                                schedule.map((s,index) => (
                                    <div key={index}className="card border-0 shadow-sm rounded-4 mb-4">
                                        <div className="card-body p-4">
                                            <div className="row align-items-center">
                                                <div className="col-md-3 text-center">
                                                    <h5 className="fw-bold text-primary">{s.flightNumber}</h5>
                                                    <small className="text-muted">{s.airline}
                                                    </small>
                                                </div>

                                                <div className="col-md-2 text-center">
                                                    <h5>{s.origin}</h5>
                                                    <small className="text-muted">
                                                        {new Date(s.departureTime).toLocaleTimeString()}
                                                    </small>
                                                </div>

                                                <div className="col-md-2 text-center">
                                                    ➔
                                                </div>

                                                <div className="col-md-2 text-center">
                                                    <h5>{s.destination}</h5>
                                                    <small className="text-muted">
                                                        {new Date(s.arrivalTime).toLocaleTimeString()}
                                                    </small>
                                                </div>

                                                <div className="col-md-3 text-center">
                                                    <h4 className="text-success fw-bold">
                                                        ₹{s.fare}
                                                    </h4>
                                                    <button className="btn btn-success rounded-pill px-4" onClick={()=>navigate("/login")}>
                                                        Book Now
                                                    </button>
                                                </div>
                                            </div>
                                            <hr />
                                            <div className="row text-center">
                                                <div className="col-md-3">
                                                    <small className="text-muted">Flight Name</small>
                                                    <div>{s.flightName}</div>
                                                </div>

                                                <div className="col-md-3">
                                                    <small className="text-muted">
                                                        Total Seats
                                                    </small>
                                                    <div>
                                                        {s.totalSeats}
                                                    </div>
                                                </div>

                                                <div className="col-md-3">
                                                    <small className="text-muted">
                                                        Cabin Baggage
                                                    </small>
                                                    <div>
                                                        {s.cabinBaggage} Kg
                                                    </div>
                                                </div>

                                                <div className="col-md-3">
                                                    <small className="text-muted">Check-In Baggage </small>
                                                    <div>{s.checkInBaggage} Kg</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                ))}

                            </div>
                        )}

                
                </div>
            </div>
        </div>
    </div>
);
}

export default PublicSearch;