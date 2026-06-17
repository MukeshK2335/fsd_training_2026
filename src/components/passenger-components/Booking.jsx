import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"

function BookTicket() {

    const { scheduleId } = useParams()
    const navigate = useNavigate()

    const [seats, setSeats] = useState([])
    const [selectedSeats, setSelectedSeats] = useState([])
    const [passengerNames, setPassengerNames] = useState([])
    const [passengerAges, setPassengerAges] = useState([])
    const [fare, setFare] = useState()
    const [errmsg, setErrmsg] = useState()
    const [successmsg, setSuccessmsg] = useState()

    const getSeatApi = "http://localhost:8080/api/seat/available/"
    const getScheduleApi = "http://localhost:8080/api/schedule/getBy-Id/"
    const addBookingApi = "http://localhost:8080/api/booking/addBooking"

    const token = localStorage.getItem("token")

    const config = {
        headers: {
            'Authorization': "Bearer " + token
        }
    }

    useEffect(() => {
        const loadSeats = async () => {
            try {
                const response = await axios.get(getSeatApi + scheduleId, config)
                setSeats(response.data)
            } catch (err) {
                console.log(err)
            }
        }
        const loadSchedule = async () => {
            try {
                const response = await axios.get(getScheduleApi + scheduleId, config)
                setFare(response.data.fare)
            } catch (err) {
                console.log(err)
            }
        }
        loadSeats()
        loadSchedule()
    }, [])

    const toggleSeat = (seat) => {

        let alreadySelected = false

        for (let i = 0; i < selectedSeats.length; i++) {
            if (selectedSeats[i].id === seat.id) {
                alreadySelected = true
            }
        }

        if (alreadySelected) {
            let updatedSeats = selectedSeats.filter((s) => s.id !== seat.id)
            setSelectedSeats(updatedSeats)
        } else {
            setSelectedSeats([...selectedSeats, seat])
            setPassengerNames([...passengerNames, ""])
            setPassengerAges([...passengerAges, ""])
        }
    }

    const toNameChange = (index, value) => {
        let updatedNames = [...passengerNames]
        updatedNames[index] = value
        setPassengerNames(updatedNames)
    }

    const toAgeChange = (index, value) => {
        let updatedAges = [...passengerAges]
        updatedAges[index] = value
        setPassengerAges(updatedAges)
    }

    const confirmBooking = async (e) => {
        e.preventDefault()

        let seatIds = []
        for (let i = 0; i < selectedSeats.length; i++) {
            seatIds.push(selectedSeats[i].id)
        }

        let ages = []
        for (let i = 0; i < passengerAges.length; i++) {
            ages.push(passengerAges[i])
        }

        let body = {
            scheduleId: scheduleId,
            passengerNames: passengerNames,
            ages: ages,
            seatIds: seatIds
        }
        console.log(body)

        try {
            const response = await axios.post(addBookingApi, body, config)
            setSuccessmsg("Booking Successfull")
            setErrmsg(undefined)
            navigate(`/passenger/payment/${response.data.id}`)
        } catch (err) {

            console.log(err)
            setErrmsg("Booking Failed")
            setSuccessmsg(undefined)
        }
    }

    return (
        <div className="container" style={{ marginTop: "100px", marginBottom: "50px" }}>

            <div className="card shadow-sm">
                <div className="card-header bg-primary text-white">
                    <h4 className="mb-0">Book Your Ticket</h4>
                </div>
                <div className="card-body">
                    {
                        errmsg !== undefined ?
                            <div className="alert alert-danger">{errmsg}</div>
                            : ""}
                    {
                        successmsg !== undefined ?
                            <div className="alert alert-danger">{successmsg}</div>
                            : ""
                    }
                    <form onSubmit={(e) => confirmBooking(e)}>
                        <label className="form-label fw-bold">Select Seats</label>
                        <div className="d-flex flex-wrap gap-2 mb-3">
                            {
                                seats.map((seat, index) => {
                                    let isSelected = false
                                    for (let i = 0; i < selectedSeats.length; i++) {
                                        if (selectedSeats[i].id === seat.id) {
                                            isSelected = true
                                        }
                                    }
                                    return (
                                        <button type="button" key={index} className={isSelected ? "btn btn-success" : "btn btn-outline-primary"}
                                            onClick={() => toggleSeat(seat)}>
                                            {seat.seatNumber}
                                        </button>
                                    )
                                })
                            }
                        </div>

                        {
                            selectedSeats.map((seat, index) => (
                                <div className="row mb-2 align-items-center" key={index}>
                                    <div className="col-md-2">
                                        <strong>Seat {seat.seatNumber}</strong>
                                    </div>
                                    <div className="col-md-5">
                                        <label>Passenger Name</label>
                                        <input type="text" className="form-control" placeholder="Name" value={passengerNames[index]}
                                            onChange={(e) => toNameChange(index, e.target.value)}
                                            required
                                        />
                                    </div>
                                    <div className="col-md-5">
                                        <label> Passenger Age</label>
                                        <input type="number" className="form-control" placeholder="Age" value={passengerAges[index]}
                                            onChange={(e) => toAgeChange(index, e.target.value)}
                                            required
                                        />
                                    </div>
                                </div>
                            ))
                        }
                        <hr />
                        <p>Seats Selected: {selectedSeats.length}</p>
                        <p>Fare per Seat: ₹{fare}</p>
                        <h5>Total: ₹{fare * selectedSeats.length}</h5>
                        <button type="submit" className="btn btn-success mt-3" disabled={selectedSeats.length === 0}>
                            Confirm Booking
                        </button>

                    </form>

                </div>
            </div>

        </div>
    )
}

export default BookTicket