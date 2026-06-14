import { useEffect, useState } from 'react';
import '../../assets/css/all-passenger-admin.css'
import { useDispatch, useSelector } from 'react-redux';
import { getAll } from '../../store/action/PassengerAction';
import axios from 'axios';


function Passengers() {


    const [error, setError] = useState("");
    const dispatch = useDispatch()
    const { passengers } = useSelector(state => state.passengers)
    


    useEffect(() => {
        dispatch(getAll())
    }, [])

    const deletePassenger = async (id) => {

        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        try {
            const response = await axios.delete(
                `http://localhost:8080/api/passenger/delete/${id}`, config
            )

        }
        catch (err) {
            console.log(err)
            setError(err)
        }


    }


    return (
        <div className="card shadow-sm border-0 passenger-card">
            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="fw-bold mb-0">Passengers</h4>

                    <span className="badge bg-primary passenger-count">
                        {passengers.length} Passengers
                    </span>
                </div>

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

                </div>

            </div>
        </div>
    )
}
export default Passengers