import { useEffect, useState } from 'react';
import '../../assets/css/all-passenger-admin.css'
import { useDispatch, useSelector } from 'react-redux';
import { getAll } from '../../store/action/PassengerAction';
import axios from 'axios';


function Passengers() {


    const [error, setError] = useState();
    const [successmsg, setSuccessmsg] = useState()
    const dispatch = useDispatch()
    const { passengers, Pages, totalRecord } = useSelector(state => state.passengers)
    const [currentPage, setCurrentPage] = useState(0)
    let count = 0;



    useEffect(() => {
        dispatch(getAll(currentPage))
    }, [currentPage])

    const deletePassenger = async (id) => {

        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        try {
            const response = await axios.delete(`http://localhost:8080/api/passenger/delete/${id}`, config)
            setError(undefined)
            setSuccessmsg("Successfully Deleted")
            dispatch(getAll(currentPage))


        }
        catch (err) {
            console.log(err)
            setError(err)
            setSuccessmsg(undefined)
        }


    }


    return (
        <div className="card shadow-sm border-0 passenger-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Passengers</h4>

                    <span className="badge bg-primary passenger-count">
                        {totalRecord} Passengers
                    </span>
                </div>
                {error !== undefined ?
                    <div className="alert alert-danger rounded-3 py-2 mb-3">
                        {error}
                    </div> : ""}
                {successmsg !== undefined ?
                    <div className="alert alert-success rounded-3 py-2 mb-3">
                        {successmsg}
                    </div> : ""}

                <div className="table-responsive">

                    <table className="table align-middle passenger-table mb-0">

                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Passenger</th>
                                <th>Email</th>
                                <th>Contact</th>
                                <th>Gender</th>
                                <th>Address</th>
                                <th className="text-center">Action</th>
                            </tr>
                        </thead>

                        <tbody>

                            {passengers.map((p) => (

                                <tr key={p.id}>

                                    <td>
                                        <span className="passenger-id">
                                            {p.id}
                                        </span>
                                    </td>

                                    <td>
                                        <div className="d-flex align-items-center gap-3">

                                            <div className="passenger-avatar">
                                                {p.name?.charAt(0).toUpperCase()}
                                            </div>

                                            <span className="fw-semibold">
                                                {p.name}
                                            </span>

                                        </div>
                                    </td>

                                    <td>{p.email}</td>

                                    <td>{p.contactNumber}</td>

                                    <td>
                                        <span className="badge rounded-pill bg-light text-dark border">
                                            {p.gender}
                                        </span>
                                    </td>

                                    <td>{p.address}</td>

                                    <td className="text-center">

                                        <button
                                            className="btn btn-danger btn-sm passenger-delete-btn"
                                            onClick={() => deletePassenger(p.id)}
                                        >
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
                                Array.from({ length: Pages }, (_, index) => (
                                    <li className="page-item" key={index} >
                                        <button className="page-link" onClick={() => setCurrentPage(index)}> {count = count + 1}
                                        </button>
                                    </li>
                                ))
                            }


                            <li className="page-item">
                                <button className="page-link" disabled={currentPage === (Pages - 1)}
                                    onClick={() => setCurrentPage(currentPage + 1)}>Next</button>
                            </li>
                        </ul>
                    </nav>

                </div>

            </div>
        </div>
    )
}
export default Passengers