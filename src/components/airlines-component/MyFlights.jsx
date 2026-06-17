import { useEffect, useState } from 'react'
import '../../assets/css/all-flights-admin.css'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'


function MyFlights() {

    const [flights, setFlights] = useState([])
const [errmsg, setErrmsg] = useState()
  const [currentPage, setCurrentPage] = useState(0)
    const [size, setSize] = useState(3)
    const [totalPages, setTotalPages] = useState(0)
    const [arry, setArry] = useState([])
    const [totalCount,setTotalCount]=useState()
    const navigate=useNavigate()
    let count = 0

const getAllFlight = "http://localhost:8080/api/flight/flight-owner"

useEffect(  () => {
    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }

    const getAll= async ()=>{
        try {
        const response = await axios.get(getAllFlight+`?page=${currentPage}&size=${size}`, config)
        setFlights(response.data.data)
         setTotalCount(response.data.totalRecords)
                console.log(response.data.data)
                setTotalPages(response.data.totalPages)
                console.log(totalPages)
                console.log(response.data.totalPages)
                setArry(Array.from({ length: totalPages }))
                console.log(arry.length)

    }
    catch(err){
        setErrmsg("Failed to Load")
        console.log(err)
    }
    }
    getAll()

    
}, [currentPage])

    return (
        <div className="card shadow-sm border-0 flight-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Flights</h4>
                    <button onClick={()=>navigate("/airlines/add-flight")} className="btn btn-primary">
                        <i className="bi bi-plus-circle me-2"></i>
                        Add Flight
                    </button>
                    {/* <span className="badge bg-primary flight-count">
                        {totalCount} Flights
                    </span> */}
                </div>

                <div className="table-responsive">

                    <table className="table align-middle flight-table mb-0">

                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Flight</th>
                                <th>Flight Number</th>
                                <th>Airline</th>
                                <th>Total Seats</th>
                                <th>Check-In Baggage</th>
                                <th>Cabin Baggage</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>

                            {flights?.map((flight) => (

                                <tr key={flight.id}>

                                    <td>
                                        <span className="flight-id">
                                            #{flight.id}
                                        </span>
                                    </td>

                                    <td>
                                        <div className="d-flex align-items-center gap-3">

                                            <div className="flight-avatar">
                                                <i className="bi bi-airplane-fill"></i>
                                            </div>

                                            <span className="fw-semibold">
                                                {flight.flightName}
                                            </span>

                                        </div>
                                    </td>

                                    <td>
                                        <span className="flight-number">
                                            {flight.flightNumber}
                                        </span>
                                    </td>

                                    <td>
                                        {flight.flightOwner?.companyName}
                                    </td>

                                    <td>
                                        <span className="badge bg-light text-dark border">
                                            {flight.totalSeats}
                                        </span>
                                    </td>

                                    <td>
                                        {flight.checkInBaggage} kg
                                    </td>

                                    <td>
                                        {flight.cabinBaggage} kg
                                    </td>
                                    <td>
                                        <button className="btn btn-danger btn-sm passenger-delete-btn">
                                            <i className="bi bi-trash3 me-2"></i>
                                            Delete
                                        </button>
                                    </td>

                                </tr>

                            ))}

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
export default MyFlights