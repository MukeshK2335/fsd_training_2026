import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

function AddFlight() {

    const [flightname, setFlightName] = useState()
    const [flightnumber, setFlightNumber] = useState()
    const [totalSeats, setTotalSeats] = useState()
    const [checkBaggage, setCheckBaggage] = useState()
    const [cabinBaggage, setCabinBaggage] = useState()

    const [errmsg, setErrmsg] = useState()
    const [successmsg, setSuccessmsg] = useState()
    const navigate=useNavigate()

    const addApi = "http://localhost:8080/api/flight/add"

    const toAddFlight = async(e) => {
        e.preventDefault()
        console.log(flightname)
        console.log(flightnumber)
        console.log(totalSeats)
        console.log(checkBaggage)
        console.log(cabinBaggage)

        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        let body = {
            "flightName":flightname,
            "flightNumber":flightnumber,
            "totalSeats":totalSeats,
            "checkInBaggage":checkBaggage,
            "cabinBaggage":cabinBaggage
        }
        try{
            const resp=await axios.post(addApi,body,config)
            setSuccessmsg("Added Successfully")
            setErrmsg(undefined)

        }
        catch(err){
            setErrmsg("Failed to Add")
            setSuccessmsg(undefined)
        }


    }

    return (
        <div className="container">
            <button onClick={() => navigate("/airlines/my-flights")} className="btn btn-primary">
                <i className="bi bi-plus-arrow me-2"></i>
                Go Back
            </button>
            <div className="row mt-5 justify-content-center">
                <div className="col-md-5">
                    <div className="card shadow-lg border-0 rounded-4">
                        <div className="card-header text-center bg-primary text-white py-3 rounded-top-4">
                            <h5 className="mb-0 fw-semibold">✈️ Add Flight</h5>
                        </div>
                        <div className="card-body px-4 py-4">
                            <form onSubmit={(e) => toAddFlight(e)}>
                                {errmsg !== undefined ?
                                    <div className="alert alert-danger rounded-3 py-2 mb-3">
                                        {errmsg}
                                    </div> : ""}
                                {successmsg !== undefined ?
                                    <div className="alert alert-success rounded-3 py-2 mb-3">
                                        {successmsg}
                                    </div> : ""}

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Flight Name</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={flightname}
                                        onChange={(e) => setFlightName(e.target.value)}
                                        placeholder="Enter Flight name" />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Flight Number</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={flightnumber}
                                        onChange={(e) => setFlightNumber(e.target.value)}
                                        placeholder="Enter Flight number" />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Total Seat</label>
                                    <input type="number" className="form-control form-control-lg rounded-3 border-1" required value={totalSeats}
                                        onChange={(e) => setTotalSeats(e.target.value)}
                                        placeholder="Enter Total seat number" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Check-In Baggage</label>
                                    <input type="number" className="form-control form-control-lg rounded-3 border-1" required value={checkBaggage}
                                        onChange={(e) => setCheckBaggage(e.target.value)}
                                        placeholder="Enter Check-In Baggage number" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Cabin-In Baggage</label>
                                    <input type="number" className="form-control form-control-lg rounded-3 border-1" required value={cabinBaggage}
                                        onChange={(e) => setCabinBaggage(e.target.value)}
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
export default AddFlight