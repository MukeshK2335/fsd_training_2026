import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

function SignUp() {

    const [name, setName] = useState()
    const [gender, setGender] = useState()
    const [conatctnumber, setContactnumber] = useState()
    const [address, setAddress] = useState()
    const [email, setEmail] = useState()
    const [username, setUsername] = useState()
    const [password, setPassword] = useState()
    const addApi = "http://localhost:8080/api/auth/passenger/add"

    const [successmsg, setSuccessmsg] = useState()
    const [errmsg, setErrmsg] = useState()
    const [userExistmsg, setUserExistmsg] = useState()
    const [errphonemsg, setErrphonemsg] = useState()
    const [erraddress, setErraddress] = useState()
    const [errusername, setErrusername] = useState()
    const navigate = useNavigate()

    const toSignUp = async (e) => {
        e.preventDefault()
        console.log(name)
        console.log(gender)
        console.log(conatctnumber)
        console.log(address)
        console.log(email)
        console.log(username)
        console.log(password)

        let body = {
            "name": name,
            "gender": gender,
            "contact_number": conatctnumber,
            "address": address,
            "email": email,
            "username": username,
            "password": password
        }

        try {
            const reponse = await axios.post(addApi, body)
            setSuccessmsg("SignUp Successfull")
            setUserExistmsg(undefined)
            setAddress("")
            setContactnumber("")
            setEmail("")
            setName("")
            setGender("")
            setUsername("")
            setPassword("")
            setErraddress(undefined)
            setErrmsg(undefined)
            setErrphonemsg(undefined)
            setErrusername(undefined)
            setTimeout(
                navigate("/login"), 1000
            )
        }
        catch (err) {
            setErrmsg("SignUp Failed")
            setUserExistmsg(err.response?.data?.message || undefined)
            setErraddress(err.response?.data?.address || undefined)
            setErrphonemsg(err.response?.data?.contact_number || undefined)
            setErrusername(err.response?.data?.username || undefined)
            setSuccessmsg(undefined)
        }
    }

    return (
        <div className="container">
            <div className="row mt-5 justify-content-center">
                <div className="col-md-5">
                    <div className="card shadow-lg border-0 rounded-4">
                        <div className="card-header text-center bg-primary text-white py-3 rounded-top-4">
                            <h5 className="mb-0 fw-semibold">✈️ Sign Up</h5>
                        </div>
                        <div className="card-body px-4 py-4">
                            <form onSubmit={(e) => toSignUp(e)}>
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
                                    <label className="form-label fw-medium text-secondary">Gender</label>
                                    <select className="form-control form-control-lg rounded-3 border-1" required value={gender}
                                        onChange={(e) => setGender(e.target.value)}>
                                        <option value="">---select category----</option>
                                        <option>MALE</option>
                                        <option>FEMALE</option>
                                        <option>OTHER</option>
                                    </select>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Contact Number</label>
                                    <input type="text" className="form-control form-control-lg rounded-3 border-1" required value={conatctnumber}
                                        onChange={(e) => setContactnumber(e.target.value)}
                                        placeholder="Enter your contact number" />
                                    {errphonemsg !== undefined ?
                                        <div className="alert alert-danger rounded-3 py-2 mt-2">
                                            {errphonemsg}
                                        </div> : ""}
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
                                    {errusername !== undefined ?
                                        <div className="alert alert-danger rounded-3 py-2 mt-2">
                                            {errusername}
                                        </div> : ""}
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-medium text-secondary">Password</label>
                                    <input type="password" className="form-control form-control-lg rounded-3 border-1" required value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        placeholder="Enter your password" />
                                </div>
                                <div className="mt-4 d-grid">
                                    <input type="submit" value="Sign Up" className="btn btn-primary btn-lg rounded-3" />
                                </div>
                            </form>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    )
}
export default SignUp