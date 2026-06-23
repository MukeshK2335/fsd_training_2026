import { useEffect, useState } from "react"
import "../../assets/css/all-booking-admin.css"
import axios from "axios"

function BookingAirlines() {

    const [bookings, setBookings] = useState([])
    const [errmsg, setErrmsg] = useState()
    const [currentPage, setCurrentPage] = useState(0)
    const [size, setSize] = useState(3)
    const [totalPages, setTotalPages] = useState(0)
    const [arry, setArry] = useState([])
    const [totalCount, setTotalCount] = useState()
    let count = 0

    const getAllBookingApi = "http://localhost:8080/api/booking/flight-owner"

    useEffect(() => {

        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        const getBooking = async () => {

            try {
                const response = await axios.get(getAllBookingApi + `?page=${currentPage}&size=${size}`, config)
                setBookings(response.data.data)
                setTotalCount(response.data.totalRecords)
                console.log(response.data.data)
                setTotalPages(response.data.totalPages)
                console.log(totalPages)
                console.log(response.data.totalPages)
                setArry(Array.from({ length: totalPages }))
                console.log(arry.length)

            }
            catch (err) {
                setErrmsg("Fail to Load")
                console.log(err)
            }
        }
        getBooking()

    }, [currentPage])
    return (
        <div className="card shadow-sm border-0 booking-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Bookings</h4>

                    <span className="badge bg-primary booking-count">
                        {totalCount} Bookings
                    </span>
                </div>

                <div className="table-responsive">

                    <table className="table align-middle booking-table mb-0">

                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Booked By</th>
                                <th>Flight</th>
                                <th>Route</th>
                                <th>Passengers</th>
                                <th>Seats</th>
                                <th>Departure</th>
                                <th>Amount</th>
                                <th>Status</th>
                            </tr>
                        </thead>

                        <tbody>

                            {
                                bookings.length > 0 ? (
                                bookings.map((b, index) => (
                                    <tr key={index}>
                                        <td>
                                            <span className="booking-id">
                                                {b.bookingId}
                                            </span>
                                        </td>
                                        <td>
                                            <div className="fw-semibold">
                                                {b.bookedBy}
                                            </div>
                                        </td>
                                        <td>
                                            <div className="d-flex align-items-center gap-2">
                                                <div className="flight-avatar">
                                                    <i className="bi bi-airplane-fill"></i>
                                                </div>
                                                <div>
                                                    <div className="fw-semibold">
                                                        {b.flightName}<br />
                                                        {b.flightNumber}
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <span className="route-text">
                                                {b.origin}
                                            </span>
                                            <i className="bi bi-arrow-right mx-2"></i>
                                            <span className="route-text">
                                                {b.destination}
                                            </span>
                                        </td>
                                        <td>
                                            {b.passengerNames?.join(", ")}
                                        </td>
                                        <td>
                                            {b.seatNumbers?.join(", ")}
                                        </td>
                                        <td>
                                            {new Date(b.departureTime).toLocaleString()}
                                        </td>
                                        <td className="amount-text">
                                            ₹{b.totalAmount}
                                        </td>
                                        <td>
                                            <span
                                                className={`badge ${b.bookingStatus === "CONFIRMED" ? "bg-success" : b.bookingStatus === "PENDING" ? "bg-warning text-dark" : "bg-danger"}`}>
                                                {b.bookingStatus}
                                            </span>
                                        </td>
                                    </tr>
                                ))
                            ):(
                                    <tr>
                                        <td colSpan="10" className="text-center py-5">
                                            <div className="d-flex flex-column align-items-center">
                                                <i
                                                    className="bi bi-calendar-x"
                                                    style={{ fontSize: "3rem" }}
                                                ></i>
                                                <h5 className="mt-3 mb-1">
                                                    No Bookings Found
                                                </h5>
                                                <p className="text-muted mb-0">
                                                    No Bookings are available at the moment.
                                                </p>
                                            </div>
                                        </td>
                                    </tr>
                                )
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
                                Array.from({ length: totalPages }).map((_, index) => (
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
export default BookingAirlines