import axios from "axios"
import { useDispatch, useSelector } from 'react-redux';
import { getAll } from "../store/action/InfoAction";
import { useEffect, useState } from "react";

function InfoList() {

        const dispatch = useDispatch()

        const {info,totalPages}=useSelector(state=>state.info)
        const [currentPage,setCurrentPage]=useState(0)
        let count=0


   
    useEffect(() => {

       dispatch(getAll(currentPage+1))
    }, [currentPage])

   

    return (
        <div className="card shadow-sm border-0 booking-card  w-100">
            <div className="card-body p-4">
                <div className="table-responsive">

                    <table className="table align-middle booking-table mb-0">

                        <thead>
                            <tr>
                                <td>Id</td>
                                <th>Name</th>
                                <th>Status</th>
                                <th>Species</th>
                                <th>Origin Name</th>
                                <th>Location Name</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                info.map((i, index) => (
                                    <tr key={index}>
                                        <td>{i.id}</td>
                                        <td>{i.name}</td>
                                        <td>{i.status}</td>
                                        <td>{i.species}</td>
                                        <td>{i.origin.name}</td>
                                        <td>{i.location.name}</td>
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

export default InfoList