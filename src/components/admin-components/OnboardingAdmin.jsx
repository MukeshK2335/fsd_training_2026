import axios from "axios"
import { use, useState } from "react"
import { useNavigate } from "react-router-dom"

function OnboardingAdmin() {

    const [name, setName] = useState()
    const [conatctnumber, setContactnumber] = useState()
    const [email, setEmail] = useState()
    const [username, setUsername] = useState()
    const addApi = "http://localhost:8080/api/auth/admin/add"

    const [successmsg, setSuccessmsg] = useState()
    const [errmsg, setErrmsg] = useState()
    const [userExistmsg, setUserExistmsg] = useState()
    const [errphonemsg, setErrphonemsg] = useState()
    const [errusername, setErrusername] = useState()
    const navigate = useNavigate()

    const toAddAdmin = async (e) => {
        e.preventDefault()
        console.log(name)
        console.log(conatctnumber)
        console.log(email)
        console.log(username)


        let body = {
            "name": name,
            "contact_number": conatctnumber,
            "email": email,
            "username":username
        }

        try {
            const config = {
                headers: {
                    'Authorization': "Bearer " + localStorage.getItem('token')
                }
            }
            const reponse = await axios.post(addApi, body, config)
            setSuccessmsg("Added Successfull")
            setUserExistmsg(undefined)
            setContactnumber("")
            setEmail("")
            setName("")
            setUsername("")
            setErrmsg(undefined)
            setErrphonemsg(undefined)
            setErrusername(undefined)

        }
        catch (err) {
            let data = err.response?.data
            console.log("DATA:", data)
            console.log("MESSAGE:", data?.message)

            setErrmsg("Adding  Failed")
            setUserExistmsg(
                data?.message || undefined
            )
            setErrphonemsg(data?.contact_number || undefined)
            setErrusername(data?.username || undefined)
            setSuccessmsg(undefined)
        }
    }

    return (
        <div className="container">
            <div className="row mt-5 justify-content-center">
                <div className="col-md-5">
                    <div className="card shadow-lg border-0 rounded-4">
                        <div className="card-header text-center bg-primary text-white py-3 rounded-top-4">
                            <h5 className="mb-0 fw-semibold">✈️ Add Admin</h5>
                        </div>
                        <div className="card-body px-4 py-4">
                            <form onSubmit={(e) => toAddAdmin(e)}>
                                {errmsg !== undefined ?
                                    <div className="alert alert-danger rounded-3 py-2 mb-3">
                                        {errmsg}
                                    </div> : ""}
                                {successmsg !== undefined ?
                                    <div className="alert alert-success rounded-3 py-2 mb-3">
                                        {successmsg}
                                    </div> : ""}
                                {userExistmsg !== undefined ?
                                    <div className="alert alert-danger rounded-3 py-2 mb-3">
                                        {userExistmsg}
                                    </div> : ""}

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Name</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={name}
                                        onChange={(e) => setName(e.target.value)}
                                        placeholder="Enter your name" />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Contact Number</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={conatctnumber}
                                        onChange={(e) => setContactnumber(e.target.value)}
                                        placeholder="Enter your contact number" />
                                    {errphonemsg !== undefined ?
                                        <div className="alert alert-danger rounded-3 py-2 mt-2">
                                            {errphonemsg}
                                        </div> : ""
                                    }
                                </div>
                
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Email</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        placeholder="Enter your email" />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Username</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                        placeholder="Enter your username" />
                                    {
                                        errusername !== undefined ?
                                            <div className="alert alert-danger rounded-3 py-2 mt-2">
                                                {errusername}
                                            </div> : ""
                                    }
                                </div>
                                <div className="mt-4 d-grid">
                                    <input type="submit" value="Add Airline" className="btn btn-primary btn-lg rounded-3" />
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}
export default OnboardingAdmin