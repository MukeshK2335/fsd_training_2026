import { useEffect, useState } from "react"
import "../../assets/css/all-payments-admin.css"
import axios from "axios"


function Payment() {
    const [payments, setPayments] = useState([])
    const [errmsg, setErrmsg] = useState()
    const [currentPage, setCurrentPage] = useState(0)
    const [size, setSize] = useState(3)
    const [totalPages, setTotalPages] = useState(0)
    const [arry, setArry] = useState([])
    const [totalCount, setTotalCount] = useState()
    let count = 0

    const getAllPaymentsApi = "http://localhost:8080/api/payment/all"

    useEffect(() => {
        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        const getPayments = async () => {
            try {
                const response = await axios.get(getAllPaymentsApi + `?page=${currentPage}&size=${size}`, config)
                setPayments(response.data.data)
                setTotalCount(response.data.totalRecords)
                console.log(response.data.data)
                setTotalPages(response.data.totalPages)
                console.log(totalPages)
                console.log(response.data.totalPages)
                setArry(Array.from({ length: totalPages }))
                console.log(arry.length)

            }
            catch (err) {
                setErrmsg("Failed To Load")
                console.log(err)
            }

        }
        getPayments()
    }, [currentPage])
    return (
        <div className="card shadow-sm border-0 payment-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Payments</h4>

                    <span className="badge bg-primary payment-count">
                        {totalCount} Payments
                    </span>
                </div>

                <div className="table-responsive">

                    <table className="table align-middle payment-table mb-0">

                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Transaction</th>
                                <th>Passenger</th>
                                <th>Flight</th>
                                <th>Route</th>
                                <th>Amount</th>
                                <th>Method</th>
                                <th>Payment Status</th>
                                <th>Booking Status</th>
                            </tr>
                        </thead>

                        <tbody>

                            {
                                payments?.map((p, index) => (
                                    <tr key={index}>

                                        <td>
                                            <span className="payment-id">
                                                #{p.paymentId}
                                            </span>
                                        </td>

                                        <td>
                                            <div className="fw-semibold">
                                                {p.transactionId}
                                            </div>
                                        </td>

                                        <td>
                                            {p.passengerName}
                                        </td>

                                        <td>
                                            <div className="d-flex align-items-center gap-2">

                                                <div className="payment-flight-avatar">
                                                    <i className="bi bi-airplane-fill"></i>
                                                </div>

                                                <div>
                                                    <div className="fw-semibold">
                                                        {p.flightName}
                                                    </div>

                                                    <small className="text-muted">
                                                        {p.flightNumber}
                                                    </small>
                                                </div>

                                            </div>
                                        </td>

                                        <td>
                                            {p.origin}
                                            <i className="bi bi-arrow-right mx-2"></i>
                                            {p.destination}
                                        </td>

                                        <td className="amount-text">
                                            ₹{p.amount}
                                        </td>

                                        <td>
                                            <span className="badge bg-light text-dark border">
                                                {p.paymentMethod}
                                            </span>
                                        </td>

                                        <td>
                                            <span
                                                className={`badge ${p.paymentStatus === "SUCCESS"
                                                    ? "bg-success"
                                                    : p.paymentStatus === "PENDING"
                                                        ? "bg-warning text-dark"
                                                        : "bg-danger"
                                                    }`}
                                            >
                                                {p.paymentStatus}
                                            </span>
                                        </td>

                                        <td>
                                            <span
                                                className={`badge ${p.bookingStatus === "CONFIRMED"
                                                    ? "bg-success"
                                                    : p.bookingStatus === "PENDING"
                                                        ? "bg-warning text-dark"
                                                        : "bg-danger"
                                                    }`}
                                            >
                                                {p.bookingStatus}
                                            </span>
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
                                Array.from({ length: totalPages }, (_, index) => (
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
export default Payment