import { useEffect, useState } from "react"
import "../../assets/css/all-booking-admin.css"
import axios from "axios"
import { useNavigate } from "react-router-dom"

function ViewTicket() {

    const [bookings, setBookings] = useState([])
    const [errmsg, setErrmsg] = useState()
    const navigate = useNavigate()

    const viewTicketApi = "http://localhost:8080/api/booking/ticket"

    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }

    useEffect(() => {

        const getBooking = async () => {
            try {
                const response = await axios.get(viewTicketApi, config)
                setBookings(response.data)
            }
            catch (err) {
                setErrmsg("Fail to Load")
                console.log(err)
            }
        }
        getBooking()

    }, [])



    return (
        <div className="card shadow-sm border-0 booking-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0"> Confirmed Bookings</h4>
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
                                <th>View Ticket</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                bookings.length >0 ?(
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
                                            <td>
                                                <button className="btn btn-sm btn-primary"
                                                    onClick={() => navigate(`/passenger/ticket/${b.bookingId}`)}>
                                                    View
                                                </button>
                                            </td>
                                        </tr>
                                    )
                                })
                            ):(
                                    <tr>
                                        <td colSpan="10" className="text-center py-5">
                                            <div className="d-flex flex-column align-items-center">
                                                <i
                                                    className="bi bi-calendar-x"
                                                    style={{ fontSize: "3rem" }}
                                                ></i>
                                                <h5 className="mt-3 mb-1">
                                                    No Ticket Found
                                                </h5>
                                                <p className="text-muted mb-0">
                                                    No Ticket are available at the moment.
                                                </p>
                                            </div>
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    )
}
export default ViewTicket