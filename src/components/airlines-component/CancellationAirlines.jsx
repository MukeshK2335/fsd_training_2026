import { useEffect, useState } from "react"
import "../../assets/css/all-cancellation-admin.css"
import axios from "axios"



function CancellationAirlines() {

    const [cancellations, setCancellation] = useState([])
    const [errmsg, setErrmsg] = useState()
    const [currentPage, setCurrentPage] = useState(0)
    const [size, setSize] = useState(1)
    const [totalPages, setTotalPages] = useState(0)
    const [arry, setArry] = useState([])
    const [totalCount,setTotalCount]=useState()
    let count = 0

    const getAllCancellationApi = "http://localhost:8080/api/cancellation/flight-owner"

    useEffect(() => {
        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        const getCancellation = async () => {

            try {
                const response = await axios.get(getAllCancellationApi + `?page=${currentPage}&size=${size}`, config)
                setCancellation(response.data.data)
                setTotalCount(response.data.totalRecords)
                console.log(response.data.data)
                setTotalPages(response.data.totalPages)
                console.log(totalPages)
                console.log(response.data.totalPages)
                setArry(Array.from({ length: totalPages }))
                console.log(arry.length)
            }
            catch (err) {
                setErrmsg("Failed to Load")
                console.log(err)
            }
        }
        getCancellation()
    }, [currentPage])
    return (
        <div className="card shadow-sm border-0 cancellation-card">
            <div className="card-body p-4">
                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Cancellations</h4>
                    <span className="badge bg-primary cancellation-count">
                        {totalCount} Cancellations
                    </span>
                </div>
                <div className="table-responsive">
                    <table className="table align-middle cancellation-table mb-0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Passenger</th>
                                <th>Flight</th>
                                <th>Route</th>
                                <th>Booking ID</th>
                                <th>Refund Amount</th>
                                <th>Refund Status</th>
                                <th>Cancellation Status</th>
                                <th>Reason</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                cancellations?.map((c, index) => (
                                    <tr key={index}>
                                        <td>
                                            <span className="cancellation-id">
                                                #{c.cancellationId}
                                            </span>
                                        </td>
                                        <td>
                                            <div className="fw-semibold">
                                                {c.passengerName}
                                            </div>
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

                                        <td className="refund-text">
                                            ₹{c.refundAmount}
                                        </td>
                                        <td>
                                            <span
                                                className={`badge ${c.refundStatus === "PROCESSED"
                                                    ? "bg-success"
                                                    : c.refundStatus === "PENDING"
                                                        ? "bg-warning text-dark"
                                                        : "bg-danger"
                                                    }`}
                                            >
                                                {c.refundStatus}
                                            </span>

                                        </td>

                                        <td>

                                            <span
                                                className={`badge ${c.cancellationStatus === "APPROVED"
                                                    ? "bg-success"
                                                    : c.cancellationStatus === "PENDING"
                                                        ? "bg-warning text-dark"
                                                        : "bg-danger"
                                                    }`}
                                            >
                                                {c.cancellationStatus}
                                            </span>

                                        </td>

                                        <td className="reason-text">
                                            {c.reason}
                                        </td>

                                    </tr>
                                ))
                            }

                        </tbody>

                    </table>
                    <nav aria-label="Page navigation example">
                        <ul className="pagination justify-content-center">

                            <li className="page-item">
                                <button className="page-link" disabled={currentPage === 0}
                                    onClick={() => setCurrentPage(currentPage - 1)}>Previous</button>
                            </li>
                            {
                                Array.from({ length: totalPages },(_, index) => (
                                    <li className="page-item" key={index} >
                                        <button className="page-link" onClick={() => setCurrentPage(index)}> {count = count + 1}
                                        </button>
                                    </li>
                                ))
                            }


                            <li className="page-item">
                                <button className="page-link" disabled={currentPage === (totalPages - 1)}
                                    onClick={() => setCurrentPage(currentPage + 1)}>Next</button>
                            </li>
                        </ul>
                    </nav>

                </div>

            </div>
        </div>
    )
}
export default CancellationAirlines