import axios from "axios"
import { useEffect, useState } from "react"

function UserList() {

    const [user, setUser] = useState([])

    const getAllApi = "https://jsonplaceholder.typicode.com/users"

    useEffect(() => {

        const getUser = async () => {

            try {
                const response = await axios.get(getAllApi)
                setUser(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getUser()
    }, [])

    const toDelete =  async (id) => {

        try {
            const respawait = await axios.delete(getAllApi+`/${id}`)
            let tempArry = [...user]
            tempArry = tempArry.filter((u) => u.id !== id)
            setUser(tempArry)
        }
        catch (err) {
            console.log(err)
        }

    }

    return (
        <div className="card shadow-sm border-0 booking-card  w-100">
            <div className="card-body p-4">
                <div className="table-responsive">

                    <table className="table align-middle booking-table mb-0">

                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Company Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                user.map((u, index) => (
                                    <tr key={index}>
                                        <td>{u.id}</td>
                                        <td>{u.name}</td>
                                        <td>{u.email}</td>
                                        <td>{u.phone}</td>
                                        <td>{u.company.name}</td>
                                        <td> <button className="btn btn-danger btn-sm passenger-delete-btn"
                                            onClick={() => toDelete(u.id)}>
                                            <i className="bi bi-trash3 me-2"></i>
                                            Delete
                                        </button></td>
                                    </tr>
                                ))

                            }

                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    )
}

export default UserList