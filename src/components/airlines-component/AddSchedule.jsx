import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

function AddSchedule() {

    const [flight, setFlight] = useState([])
    const [route, setRoute] = useState([])
    const [fare, setfare] = useState()
    const [scheduleDate, setScheduleDate] = useState()
    const [arrivalTime, setArrivalTime] = useState()
    const [departureTime, setDepartureTime] = useState()
    const [flightId, setFlightId] = useState(0)
    const [routeId, setRouteId] = useState(0)
    const [successmsg,setSuccessmsg]=useState()
    const [errmsg,setErrmsg]=useState()
    const navigate=useNavigate()

    const addScheduleApi = "http://localhost:8080/api/schedule/add"
    const getFlightApi = "http://localhost:8080/api/flight/flight-owner"
    const getRouteApi = "http://localhost:8080/api/route/all/active"
    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }
    useEffect(() => {

        const getFlight = async () => {
            try {
                const resp = await axios.get(getFlightApi, config)
                setFlight(resp.data.data)

            }
            catch (err) {
                console.log(err)
            }
        }

        const getRoute = async () => {

            try {
                const response = await axios.get(getRouteApi, config)
                setRoute(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getFlight()
        getRoute()

    }, [])

    const addSchedule = async(e) => {
        e.preventDefault()

        let body = {
            "flightId": flightId,
            "routeId": routeId,
            "fare": fare,
            "departureTime": departureTime,
            "arrivalTime": arrivalTime,
            "scheduleDate":scheduleDate
        }
        try{
            const rep=await axios.post(addScheduleApi,body,config)
            setSuccessmsg("Added Successfully")
            setErrmsg(undefined)
            setFlightId("")
            setRouteId("")
            setArrivalTime("")
            setDepartureTime("")
            setScheduleDate("")
            setfare("")
            
        }
        catch(err){
            setErrmsg("Failed to Add")
            setSuccessmsg(undefined)
        }



    }
    return (
        <div className="container">
            <button onClick={() => navigate("/airlines/schedules")} className="btn btn-primary">
                <i className="bi bi-plus-arrow me-2"></i>
                Go Back
            </button>
            <div className="row mt-5 justify-content-center">
                <div className="col-md-5">
                    <div className="card shadow-lg border-0 rounded-4">
                        <div className="card-header text-center bg-primary text-white py-3 rounded-top-4">
                            <h5 className="mb-0 fw-semibold">✈️ Add Schedule</h5>
                        </div>
                        <div className="card-body px-4 py-4">
                            <form onSubmit={(e) => addSchedule(e)}>
                                {errmsg !== undefined ?
                                    <div className="alert alert-danger rounded-3 py-2 mb-3">
                                        {errmsg}
                                    </div> : ""}
                                {successmsg !== undefined ?
                                    <div className="alert alert-success rounded-3 py-2 mb-3">
                                        {successmsg}
                                    </div> : ""}

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Choose the Flight</label>
                                    <select className="form-control" onChange={(e) => setFlightId(e.target.value)}>
                                        <option>---Select Flight----</option>
                                        {
                                            flight.map((f, index) => (

                                                <option key={index} value={f.id}>{f.flightName}</option>
                                            ))
                                        }
                                    </select>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Choose the Route</label>
                                    <select className="form-control" onChange={(e) => setRouteId(e.target.value)}>
                                        <option >----Select Route-----</option>
                                        {
                                            route.map((r, index) => (

                                                <option key={index} value={r.id}>{r.origin} → {r.destination}</option>
                                            ))
                                        }
                                    </select>

                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Fare</label>
                                    <input type="number" className="form-control form-control-lg rounded-3 border-1" required
                                        onChange={(e) => setfare(e.target.value)}
                                        placeholder="Enter the fare price" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Date</label>
                                    <input type="date" className="form-control form-control-lg rounded-3 border-1" required
                                        onChange={(e) => setScheduleDate(e.target.value)}
                                        placeholder="Enter Cabin Baggage number" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Departure Time</label>
                                    <input type="datetime-local" className="form-control form-control-lg rounded-3 border-1" required
                                        onChange={(e) => setDepartureTime(e.target.value)}
                                        placeholder="Enter Check-In Baggage number" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Arrival Time</label>
                                    <input type="datetime-local" className="form-control form-control-lg rounded-3 border-1" required
                                        onChange={(e) => setArrivalTime(e.target.value)}
                                        placeholder="Enter Cabin Baggage number" />
                                </div>

                                <div className="mt-4 d-grid">
                                    <input type="submit" value="Add Flight" className="btn btn-primary btn-lg rounded-3" />
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddSchedule