import { useEffect, useState } from "react";
import "../../assets/css/all-route-admin.css"
import axios from "axios";
import { useNavigate } from "react-router-dom";
function Route(){

    const [routes,setRoutes]=useState([])
    const [errmsg,setErrmsg]=useState()
    const [currentPage, setCurrentPage] = useState(0)
    const [size, setSize] = useState(5)
    const [totalPages, setTotalPages] = useState(0)
    const [arry, setArry] = useState([])
    const [totalCount,setTotalCount]=useState()
    const navigate=useNavigate()
    let count = 0

    const getApi="http://localhost:8080/api/route/all"

    useEffect(()=>{
        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        const getAllRoute= async ()=>{

            try{
                const response=await axios.get(getApi+ `?page=${currentPage}&size=${size}`, config)
                setRoutes(response.data.data)
                setTotalCount(response.data.totalRecords)
                console.log(response.data.data)
                setTotalPages(response.data.totalPages)
                console.log(totalPages)
                console.log(response.data.totalPages)
                setArry(Array.from({ length: totalPages }))
                console.log(arry.length)
            }
            catch(err){
                setErrmsg("Failed To Load")
                console.log(err)
            }
        }
        getAllRoute()


    },[currentPage])

    return(
        <div className="card shadow-sm border-0 route-card">

            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">

                    <h4 className="fw-bold mb-0">
                        Routes
                    </h4>

                    <button onClick={()=>navigate("/admin/add-route")} className="btn btn-primary">
                        <i className="bi bi-plus-circle me-2"></i>
                        Add Route
                    </button>

                </div>

                {errmsg && (
                    <div className="alert alert-danger">
                        {errmsg}
                    </div>
                )}

                <div className="table-responsive">

                    <table className="table align-middle route-table mb-0">

                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Origin</th>
                                <th>Destination</th>
                                <th>Status</th>
                            </tr>
                        </thead>

                        <tbody>

                            {routes?.map((route, index) => (

                                <tr key={index}>

                                    <td>
                                        <span className="route-id">
                                            #{route.id}
                                        </span>
                                    </td>

                                    <td>
                                        <div className="fw-semibold">
                                            {route.origin}
                                        </div>
                                    </td>

                                    <td>
                                        <div className="fw-semibold">
                                            {route.destination}
                                        </div>
                                    </td>

                                    <td>

                                        <span
                                            className={`badge ${
                                                route.routeStatus === "ACTIVE"
                                                    ? "bg-success"
                                                    : "bg-danger"
                                            }`}
                                        >
                                            {route.routeStatus}
                                        </span>

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
export default Route;