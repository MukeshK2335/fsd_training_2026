import { useEffect, useState } from "react"
import "../../assets/css/cancellation-history-passenger.css"
import axios from "axios"

function CancellationHistory() {

    const [cancellations, setCancellation] = useState([])
    const [errmsg, setErrmsg] = useState()


    const getCancellationApi = "http://localhost:8080/api/cancellation/passenger"

    useEffect(() => {
        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        const getCancellation = async () => {
            try {
                const response = await axios.get(getCancellationApi, config)
                setCancellation(response.data)

            }
            catch (err) {
                setErrmsg("Failed to Load")
                console.log(err)
            }
        }
        getCancellation()
    }, [])

    return (
        <div className="card shadow-sm border-0 cancellation-card">
            <div className="card-body p-4">
                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Cancellation History</h4>
                    <span className="badge bg-primary cancellation-count">
                        {cancellations.length} Cancellations
                    </span>
                </div>

                {
                    errmsg !== undefined ?
                        <div className="alert alert-danger">{errmsg}</div>
                        : ""
                }

                <div className="table-responsive">
                    <table className="table align-middle cancellation-table mb-0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Flight</th>
                                <th>Route</th>
                                <th>Booking ID</th>
                                <th>Booking Date</th>
                                <th>Cancellation Date</th>
                                <th>Reason</th>
                                <th>Refund Amount</th>
                                <th>Refund Status</th>
                                <th>Cancellation Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                cancellations?.map((c, index) => (
                                    <tr key={index}>
                                        <td>{c.cancellationId}
                                        </td>
                                        <td>
                                            <div className="d-flex align-items-center gap-2">
                                                <div className="flight-avatar">
                                                    <i className="bi bi-airplane-fill"></i>
                                                </div>
                                                <div>
                                                    <div className="fw-semibold">
                                                        {c.flightName}<br />
                                                        {c.flightNumber}
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            {c.origin}
                                            <i className="bi bi-arrow-right mx-2"></i>
                                            {c.destination}
                                        </td>
                                        <td>
                                            {c.bookingId}
                                        </td>
                                        <td>
                                            {c.bookingDate}
                                        </td>
                                        <td>
                                            {new Date(c.cancellationDate).toLocaleString()}
                                        </td>
                                        <td className="reason-text">
                                            {c.reason}
                                        </td>
                                        <td className="refund-text">
                                            ₹{c.refundAmount}
                                        </td>
                                        <td>
                                            <span
                                                className={`badge ${c.refundStatus === "PROCESSED" ? "bg-success" : c.refundStatus === "PENDING" ? "bg-warning text-dark" : "bg-danger"}`}>
                                                {c.refundStatus}
                                            </span>
                                        </td>
                                        <td>
                                            <span
                                                className={`badge ${c.cancellationStatus === "APPROVED" ? "bg-success" : c.cancellationStatus === "REQUESTED" ? "bg-warning text-dark" : "bg-danger"}`}>
                                                {c.cancellationStatus}
                                            </span>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>


                </div>
            </div>
        </div>
    )
}
export default CancellationHistory