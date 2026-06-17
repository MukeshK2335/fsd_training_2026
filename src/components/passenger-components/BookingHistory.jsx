import { useEffect, useState } from "react"
import "../../assets/css/all-booking-admin.css"
import axios from "axios"
import { useNavigate } from "react-router-dom"

function BookingHistory() {

    const [bookings, setBookings] = useState([])
    const [errmsg, setErrmsg] = useState()
    const navigate = useNavigate()
    const getAllBookingApi = "http://localhost:8080/api/booking/history"
    const cancelApi = "http://localhost:8080/api/cancellation/add/"

    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }
    useEffect(() => {



        const getBooking = async () => {

            try {
                const response = await axios.get(getAllBookingApi, config)
                setBookings(response.data)
            }
            catch (err) {
                setErrmsg("Fail to Load")
                console.log(err)
            }
        }
        getBooking()

    }, [])
    const toCancel = async () => {
        try {
            const response = await axios.post(cancelApi,)
        }
        catch (err) {
            console.log(err)
        }
    }
    return (
        <div className="card shadow-sm border-0 booking-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Bookings</h4>

                    <span className="badge bg-primary booking-count">
                        {bookings.length} Bookings
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
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                bookings.map((b, index) => {

                                    return (
                                        <tr key={index}>
                                            <td>{b.bookingId}
                                            </td>
                                            <td>{b.bookedBy}
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
                                                {
                                                    b.passengerNames?.map((name, index) => (
                                                        <div key={index}>{name}</div>
                                                    ))
                                                }
                                            </td>
                                            <td>
                                                {
                                                b.seatNumbers?.map((seat, index) => (
                                                    <div key={index}>{seat}</div>
                                                ))
                                                }
                                            </td>
                                            <td>
                                                {new Date(b.departureTime).toLocaleString()}
                                            </td>
                                            <td className="amount-text">₹{b.totalAmount}
                                            </td>
                                            <td>
                                                <span
                                                    className={`badge ${b.bookingStatus === "CONFIRMED" ? "bg-success" : b.bookingStatus === "PENDING" ? "bg-warning text-dark" : "bg-danger"}`}>
                                                    {b.bookingStatus}
                                                </span>
                                            </td>
                                            <td>
                                                <button className="btn btn-sm btn-success me-2" disabled={b.bookingStatus === "CONFIRMED"}
                                                    onClick={() => navigate(`/passenger/payment/${b.bookingId}`)}>
                                                    Pay
                                                </button>
                                                <button className="btn btn-sm btn-danger"disabled={b.bookingStatus === "PENDING"}
                                                    onClick={() => navigate(`/passenger/cancellation/${b.bookingId}`)}>
                                                    Cancel
                                                </button>
                                            </td>
                                        </tr>
                                    )
                                })
                            }
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    )
}
export default BookingHistory