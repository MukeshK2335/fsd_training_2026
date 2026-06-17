import axios from "axios"
import { useState } from "react"
import { useNavigate, useParams } from "react-router-dom"

function AddCancellation() {

    const { bookingId } = useParams()
    const navigate = useNavigate()

    const [reason, setReason] = useState("")
    const [errmsg, setErrmsg] = useState()
    const [successmsg, setSuccessmsg] = useState()

    const addCancellationApi = "http://localhost:8080/api/cancellation/add/"

    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem("token")
        }
    }

    const submitCancellation = async (e) => {
        e.preventDefault()

        let body = {
            reason: reason
        }

        try {
            await axios.post(addCancellationApi + `${bookingId}`, body, config)
            setSuccessmsg("Cancellation Request Submitted")
            setErrmsg(undefined)
        } catch (err) {
            console.log(err)
            setErrmsg("Failed to Submit Cancellation")
            setSuccessmsg(undefined)
        }
    }

    return (
        <div className="container" style={{ marginTop: "100px", marginBottom: "50px" }}>

            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow-sm">
                        <div className="card-header bg-danger text-white">
                            <h4 className="mb-0">Cancel Booking</h4>
                        </div>

                        <div className="card-body">

                            {errmsg !== undefined ?
                                <div className="alert alert-danger">{errmsg}</div>
                                : ""}
                            {successmsg !== undefined ?
                                <div className="alert alert-success">{successmsg}</div>
                                : ""}

                            <form onSubmit={submitCancellation}>

                                <div className="mb-3">
                                    <label className="form-label fw-bold">Reason for Cancellation</label>
                                    <textarea
                                        className="form-control"
                                        rows="4"
                                        maxLength="255"
                                        value={reason}
                                        onChange={(e) => setReason(e.target.value)}
                                        required
                                    ></textarea>
                                </div>

                                <button type="submit" className="btn btn-danger w-100">
                                    Submit Cancellation Request
                                </button>

                            </form>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default AddCancellation