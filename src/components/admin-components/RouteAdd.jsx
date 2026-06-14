import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

function RouteAdd() {
    const [origin, setOrigin] = useState()
    const [destination, setDestination] = useState()
    const [successmsg, setSuccessmsg] = useState()
    const [errmsg, setErrmsg] = useState()
    const [existMsg, setExistMsg] = useState()
    const addRouteApi = "http://localhost:8080/api/route/add"
    const navigate=useNavigate()

    const addRoute = async (e) => {
        e.preventDefault()
        console.log(origin)
        console.log(destination)

        let body = {
            "origin": origin,
            "destination": destination
        }

        try {
            const config = {
                headers: {
                    'Authorization': "Bearer " + localStorage.getItem('token')
                }
            }

            const response = await axios.post(addRouteApi, body, config)
            setSuccessmsg("Route Added Successfully")
            setOrigin("")
            setDestination("")
            setExistMsg(undefined)
            setErrmsg(undefined)
        }
        catch (err) {

            setErrmsg("Adding Route Failed")
            setExistMsg(err.response?.data?.message || undefined)
            setSuccessmsg(undefined)
        }

    }

    return (
        <div className="container">
            <div className="row mt-5 justify-content-center">
                <div className="d-flex justify-content-start">
                    <button onClick={() => navigate("/admin/routes")} className="btn btn-primary">
                        <i className="bi bi-plus-arrow me-2"></i>
                        Go Back
                    </button>
                </div>
                <div className="col-md-5">

                    <div className="card shadow-lg border-0 rounded-4">

                        <div className="card-header text-center bg-primary text-white py-3 rounded-top-4">
                            <h5 className="mb-0 fw-semibold">✈️ Add Route</h5>
                        </div>
                        <div className="card-body px-4 py-4">
                            <form onSubmit={(e) => addRoute(e)}>
                                {errmsg !== undefined ?
                                    <div className="alert alert-danger rounded-3 py-2 mb-3">
                                        {errmsg}
                                    </div> : ""}
                                {successmsg !== undefined ?
                                    <div className="alert alert-success rounded-3 py-2 mb-3">
                                        {successmsg}
                                    </div> : ""}
                                {
                                    existMsg !== undefined ?
                                        <div className="alert alert-danger rounded-3 py-2 mb-3">
                                            {existMsg}
                                        </div> : ""
                                }

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Origin</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={origin}
                                        onChange={(e) => setOrigin(e.target.value)}
                                        placeholder="Enter Origin" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Destination</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" value={destination} placeholder="Enter Destination"
                                        onChange={(e) => setDestination(e.target.value)} />

                                </div>




                                <div className="mt-4 d-grid">
                                    <input type="submit" value="Add Route" className="btn btn-primary btn-lg rounded-3" />
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}
export default RouteAdd