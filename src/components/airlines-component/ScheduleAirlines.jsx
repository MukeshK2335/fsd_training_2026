import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

function ScheduleAirlines() {

    const [schedules, setSchedules] = useState([])
    const [errmsg, setErrmsg] = useState()
    const [currentPage, setCurrentPage] = useState(0)
    const [size, setSize] = useState(5)
    const [totalPages, setTotalPages] = useState(0)
    const [arry, setArry] = useState([])
    const [totalCount, setTotalCount] = useState()
    const navigate = useNavigate()
    let count = 0

    const getAllSchedule = "http://localhost:8080/api/schedule/all/flight-owner"
    const delayApi = "http://localhost:8080/api/schedule/delay/"
    const cancelApi = "http://localhost:8080/api/schedule/cancel/"
    const activeApi="http://localhost:8080/api/schedule/active/"

    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }
    useEffect(() => {



        const getSchedule = async () => {

            try {
                const response = await axios.get(getAllSchedule + `?page=${currentPage}&size=${size}`, config)
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
    const handleDelay = async (scheduleId) => {
        console.log("Delay Schedule:", scheduleId);

        try {
            const re = await axios.put(delayApi + `${scheduleId}`, {}, config)
             window.location.reload();

        }
        catch (err) {
            console.log(err)
        }
    };

    const handleCancel = async (scheduleId) => {
        console.log("Cancel Schedule:", scheduleId);

        try {
            const r = await axios.put(cancelApi + `${scheduleId}`, {}, config)
             window.location.reload();
        }
        catch (err) {
            console.log(err)
        }

    };
    const handleActive = async (scheduleId) => {
        try {
            await axios.put(activeApi + `${scheduleId}`,{},config);
            window.location.reload();

        } catch (err) {
            console.log(err);
        }
    };

    return (
        <div className="card shadow-sm border-0 schedule-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Schedules</h4>
                    <button onClick={() => navigate("/airlines/add-schedule")} className="btn btn-primary">
                        <i className="bi bi-plus-circle me-2"></i>
                        Add Schedule
                    </button>
                    {/* <span className="badge bg-primary schedule-count">
                        {totalCount} Schedules
                    </span> */}
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
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>

                            {
                                schedules.length > 0 ? (
                                    schedules.map((s, index) => (
                                        <tr key={index}>
                                            <td>{index + 1}</td>
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
                                            <td>
                                                <div className="d-flex gap-2">

                                                    <button
                                                        className="btn btn-success btn-sm"
                                                        disabled={s.scheduleStatus === "ACTIVE"}
                                                        onClick={() => handleActive(s.id)}
                                                    >
                                                        <i className="bi bi-check-circle me-1"></i>
                                                        Active
                                                    </button>
                                                    <button
                                                        className="btn btn-warning btn-sm"
                                                        disabled={s.scheduleStatus === "DELAYED"}
                                                        onClick={() => handleDelay(s.id)}
                                                    >
                                                        <i className="bi bi-clock-history me-1"></i>
                                                        Delay
                                                    </button>
                                                    {

                                                    }

                                                    <button
                                                        className="btn btn-danger btn-sm"
                                                        disabled={s.scheduleStatus === "CANCELLED"}
                                                        onClick={() => handleCancel(s.id)}
                                                    >
                                                        <i className="bi bi-x-circle me-1"></i>
                                                        Cancel
                                                    </button>
                                                    <button
                                                        className="btn btn-primary btn-sm"
                                                        onClick={() => navigate(`/airlines/edit-schedule/${s.id}`)}
                                                    >
                                                        <i className="bi bi-pencil-square me-1"></i>
                                                        Edit
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan="10" className="text-center py-5">
                                            <div className="d-flex flex-column align-items-center">
                                                <i
                                                    className="bi bi-calendar-x"
                                                    style={{ fontSize: "3rem" }}
                                                ></i>
                                                <h5 className="mt-3 mb-1">
                                                    No Schedules Found
                                                </h5>
                                                <p className="text-muted mb-0">
                                                    No schedules are available at the moment.
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
export default ScheduleAirlines