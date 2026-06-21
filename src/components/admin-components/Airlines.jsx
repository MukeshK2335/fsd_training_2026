import { use, useEffect, useState } from "react"
import "../../assets/css/all-airlines-admin.css"
import axios from "axios"
import { useNavigate } from "react-router-dom"
function Airlines(){

    const [airlines,setAirlines]=useState([])
    const [errmsg,setErrmsg]=useState()
     const [currentPage, setCurrentPage] = useState(0)
    const [size, setSize] = useState(5)
    const [totalPages, setTotalPages] = useState(0)
    const [arry, setArry] = useState([])
    const [totalCount,setTotalCount]=useState()
    const navigate=useNavigate()
    let count = 0

    const getAirlinesApi="http://localhost:8080/api/flight-owner/all"

    useEffect(()=>{
        const config = {
            headers: {
                'Authorization': "Bearer " + localStorage.getItem('token')
            }
        }

        const getAllAirlines= async ()=>{

            try{
                const response=await axios.get(getAirlinesApi+ `?page=${currentPage}&size=${size}`, config)
                setAirlines(response.data.data)
                setTotalCount(response.data.totalRecords)
                console.log(response.data.data)
                setTotalPages(response.data.totalPages)
                console.log(totalPages)
                console.log(response.data.totalPages)
                setArry(Array.from({ length: totalPages }))
                console.log(arry.length)

            }
            catch(err){
                setErrmsg("Fail To Load")
                console.log(err)
            }
        }
        getAllAirlines()


    },[currentPage])
    return(
        <div className="card shadow-sm border-0 airline-card">

            <div className="card-body p-4">

                <div className="d-flex justify-content-between align-items-center mb-4">

                    <h4 className="fw-bold mb-0">
                        Airlines
                    </h4>
                    
                    <button onClick={()=>navigate("/admin/add-airlines")} className="btn btn-primary">
                        <i className="bi bi-plus-circle me-2"></i>
                        Add Route
                    </button>

                    {/* <span className="badge bg-primary airline-count">
                        {totalCount} Airlines
                    </span> */}

                </div>

                {errmsg && (
                    <div className="alert alert-danger">
                        {errmsg}
                    </div>
                )}

                <div className="table-responsive">

                    <table className="table align-middle airline-table mb-0">

                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Company</th>
                                <th>Username</th>
                                <th>Contact</th>
                                <th>Email</th>
                                <th>Address</th>
                            </tr>
                        </thead>

                        <tbody>

                            {
                                airlines?.map((a, index) => (
                                    <tr key={index}>

                                        <td>
                                            <span className="airline-id">
                                                #{a.id}
                                            </span>
                                        </td>
                                        <td>
                                            <div className="company-name">
                                                {a.companyName}
                                            </div>
                                        </td>

                                        <td>
                                            {a.userName}
                                        </td>

                                        <td>
                                            {a.contactNumber}
                                        </td>

                                        <td>
                                            {a.email}
                                        </td>

                                        <td>
                                            {a.address}
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
export default Airlines