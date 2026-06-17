import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"

function Payments() {

    const { bookingId } = useParams()
    const navigate = useNavigate()

    const [booking, setBooking] = useState()
    const [paymentMethod, setPaymentMethod] = useState("")
    const [errmsg, setErrmsg] = useState()
    const [successmsg, setSuccessmsg] = useState()

    const getBookingApi = "http://localhost:8080/api/booking/get-by/"
    const addPaymentApi = "http://localhost:8080/api/payment/add/"



    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem("token")
        }
    }

    useEffect(() => {
        const loadBooking = async () => {
            try {
                const response = await axios.get(getBookingApi + `${bookingId}`, config)
                setBooking(response.data)
            } catch (err) {
                console.log(err)
            }

        }
        loadBooking()
    }, [])



    const makePayment = async (e) => {
        e.preventDefault()

        let body = {
            paymentMethod: paymentMethod
        }

        try {
            await axios.post(addPaymentApi + bookingId, body, config)
            setSuccessmsg("Payment Success")
            setErrmsg(undefined)

        } catch (err) {
            console.log(err)
            setErrmsg("Payment Failed")
            setSuccessmsg(undefined)
        }
    }

    return (
        <div className="container" style={{ marginTop: "100px", marginBottom: "50px" }}>

            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow-sm">
                        <div className="card-header bg-primary text-white">
                            <h4 className="mb-0">Make Payment</h4>
                        </div>
                        <div className="card-body">

                            {
                                errmsg !== undefined ?
                                    <div className="alert alert-danger">{errmsg}</div>
                                    : ""}
                            {
                                successmsg !== undefined ?
                                    <div className="alert alert-success">{successmsg}</div>
                                    : ""
                            }
                            <form onSubmit={(e)=>makePayment(e)}>
                                {
                                    booking !== undefined ?
                                        <div className="mb-3 p-3 border rounded">
                                            <p><strong>Booked By:</strong> {booking.bookedBy}</p>
                                            <p><strong>Flight:</strong> {booking.flightName} → {booking.flightNumber}</p>
                                            <p><strong>Route:</strong> {booking.origin} → {booking.destination}</p>
                                            <p><strong>Departure:</strong> {new Date(booking.departureTime).toLocaleString()}</p>
                                            <p><strong>Arrival:</strong> {new Date(booking.arrivalTime).toLocaleString()}</p>
                                            <p><strong>Passengers:</strong> {booking.passengerNames}</p>
                                            <p><strong>Seats:</strong> {booking.seatNumbers}</p>
                                            <p><strong>Cabin Baggage:</strong> {booking.cabinBaggage} Kg</p>
                                            <p><strong>Check-In Baggage:</strong> {booking.checkInBaggage} Kg</p>
                                            <p><strong>Status:</strong> {booking.bookingStatus}</p>
                                            <h5><strong>Total Amount:</strong> ₹{booking.totalAmount}</h5>
                                        </div>
                                        : ""
                                }
                                <div className="mb-3">
                                    <label className="form-label fw-bold">Payment Method</label>
                                    <select className="form-control"value={paymentMethod} required
                                        onChange={(e) => setPaymentMethod(e.target.value)}>
                                        <option value="">--Select Payment Method--</option>
                                        <option value="CREDIT_CARD">Credit CArd</option>
                                        <option value="DEBIT_CARD">Debit Card</option>
                                        <option value="UPI">UPI</option>
                                        <option value="NET_BANKING">Net Banking</option>
                                    </select>
                                </div>
                                <button type="submit" className="btn btn-success w-100">Pay Now</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Payments