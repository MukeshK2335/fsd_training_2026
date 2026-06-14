import axios from "axios"
import { useEffect, useState } from "react"

function Schedule() {

    const [schedules, setSchedules] = useState([])
    const [errmsg, setErrmsg] = useState()
    const [currentPage, setCurrentPage] = useState(0)
    const [size, setSize] = useState(5)
    const [totalPages, setTotalPages] = useState(0)
    const [arry, setArry] = useState([])
    const [totalCount, setTotalCount] = useState()
    let count = 0

    const getAllSchedule = "http://localhost:8080/api/schedule/all"

    useEffect(() => {

        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        const getSchedule = async () => {

            try {
                const response = await axios.get(getAllSchedule+`?page=${currentPage}&size=${size}`, config)
                setSchedules(response.data.data)
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
        getSchedule()

    }, [currentPage])

    return (
        <div className="card shadow-sm border-0 schedule-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Schedules</h4>

                    <span className="badge bg-primary schedule-count">
                        {totalCount} Schedules
                    </span>
                </div>

                <div className="table-responsive">

                    <table className="table align-middle schedule-table mb-0">

                        <thead>
                            <tr>
                                <th>S.No</th>
                                <th>Route</th>
                                <th>Flight</th>
                                <th>Airline</th>
                                <th>Departure</th>
                                <th>Arrival</th>
                                <th>Seats</th>
                                <th>Fare</th>
                                <th>Status</th>
                            </tr>
                        </thead>

                        <tbody>

                            {
                                schedules.map((s, index) => (
                                    <tr key={index}>
                                        <td>{index+1}</td>
                                        <td>
                                            <span className="route-text">
                                                {s.origin}
                                            </span>
                                            <i className="bi bi-arrow-right mx-2"></i>
                                            <span className="route-text">
                                                {s.destination}
                                            </span>
                                        </td>
                                        <td>
                                            <div className="d-flex align-items-center gap-2">
                                                <div className="flight-avatar">
                                                    <i className="bi bi-airplane-fill"></i>
                                                </div>
                                                <div>
                                                    <div className="fw-semibold">
                                                        {s.flightName}<br />
                                                        {s.flightNumber}
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td>{s.airline}</td>
                                        <td>
                                            {new Date(s.departureTime).toLocaleString()}
                                        </td>
                                        <td>
                                            {new Date(s.arrivalTime).toLocaleString()}
                                        </td>
                                        <td>
                                            <span className="badge bg-light text-dark border">
                                                {s.totalSeats}
                                            </span>
                                        </td>
                                        <td className="fare-text">
                                            ₹{s.fare}
                                        </td>
                                        <td>
                                            <span
                                                className={`badge ${s.scheduleStatus === "ACTIVE" ? "bg-success" : "bg-danger"}`}>{s.scheduleStatus}
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
export default Schedule