import axios from "axios"
import { use, useState } from "react"
import { useNavigate } from "react-router-dom"

function OnboardingAirline() {

    const [companyName, setCompanyName] = useState()
    const [conatctnumber, setContactnumber] = useState()
    const [address, setAddress] = useState()
    const [email, setEmail] = useState()
    const [username, setUsername] = useState()
    const addApi = "http://localhost:8080/api/auth/flight-owner/add"

    const [successmsg, setSuccessmsg] = useState()
    const [errmsg, setErrmsg] = useState()
    const [userExistmsg, setUserExistmsg] = useState()
    const [errphonemsg, setErrphonemsg] = useState()
    const [erraddress, setErraddress] = useState()
    const [errusername, setErrusername] = useState()
    const [errcompanyname, setErrCompanyName] = useState()
    const navigate = useNavigate()

    const toAddAirline = async (e) => {
        e.preventDefault()
        console.log(name)
        console.log(conatctnumber)
        console.log(address)
        console.log(email)
        console.log(username)


        let body = {
            "companyName": companyName,
            "contactNumber": conatctnumber,
            "address": address,
            "contactEmail": email,
            "username": username
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
            setAddress("")
            setContactnumber("")
            setEmail("")
            setUsername("")
            setCompanyName("")
            setErraddress(undefined)
            setErrmsg(undefined)
            setErrphonemsg(undefined)
            setErrusername(undefined)
            setErrCompanyName(undefined)

        }
        catch (err) {
            let data = err.response?.data
            console.log("DATA:", data)
            console.log("MESSAGE:", data?.message)

            setErrmsg("Adding  Failed")
            setUserExistmsg(
                data?.message || undefined
            )
            setErraddress(data?.address || undefined)
            setErrphonemsg(data?.contactNumber || undefined)
            setErrusername(data?.username || undefined)
            setErrCompanyName(data?.companyName || undefined)
            setSuccessmsg(undefined)
        }
    }

    return (
        <div className="container">
            <button onClick={() => navigate("/admin/airlines")} className="btn btn-primary">
                <i className="bi bi-plus-arrow me-2"></i>
                Go Back
            </button>
            <div className="row mt-5 justify-content-center">

                <div className="col-md-5">

                    <div className="card shadow-lg border-0 rounded-4">
                        <div className="card-header text-center bg-primary text-white py-3 rounded-top-4">
                            <h5 className="mb-0 fw-semibold">✈️ Add Airline</h5>
                        </div>
                        <div className="card-body px-4 py-4">
                            <form onSubmit={(e) => toAddAirline(e)}>
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
                                    <label className="form-label fw-medium text-secondary">Company Name</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" value={companyName} placeholder="Enter Comapny Name"
                                        onChange={(e) => setCompanyName(e.target.value)} />
                                    {
                                        errcompanyname !== undefined ?
                                            <div className="alert alert-danger rounded-3 py-2 mt-2">
                                                {errcompanyname}
                                            </div> : ""
                                    }
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
                                    <label className="form-label fw-medium text-secondary">Address</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={address}
                                        onChange={(e) => setAddress(e.target.value)}
                                        placeholder="Enter your address" />
                                    {erraddress !== undefined ?
                                        <div className="alert alert-danger rounded-3 py-2 mt-2">
                                            {erraddress}
                                        </div> : ""}
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
export default OnboardingAirline