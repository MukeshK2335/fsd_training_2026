import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import "../../assets/css/password-reset.css"

function PasswordAirlinesReset() {

    const [passsword, setPassword] = useState()
    const [errmsg, setErrmsg] = useState()
    const [succmsg, setSuccmsg] = useState()
    const naviagte = useNavigate()

    const changeApi = "http://localhost:8080/api/flight-owner/reset/password"

    const config = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }

    const toChangePassword = async (e) => {
        e.preventDefault()

        let body = {
            "password": passsword
        }

        try {
            await axios.post(changeApi, body, config)
            setSuccmsg("Password Changed Successfully")
            setErrmsg(undefined)
            setPassword("")
            localStorage.clear()
            naviagte("/login")
        }
        catch (err) {
            setErrmsg("Failed to Change")
            setSuccmsg(undefined)
        }
    }

    return (
        <div className="password-page">

            <div className="password-center-container">

                <div className="card password-card shadow-lg">

                    <div className="card-header password-header text-center">
                        <i className="bi bi-shield-lock-fill password-icon"></i>
                        <h2 className="mt-3">Reset Password</h2>
                        <p className="mb-0">
                            Enter your new password below
                        </p>
                    </div>

                    <div className="card-body p-4">

                        {
                            errmsg &&
                            <div className="alert alert-danger">
                                {errmsg}
                            </div>
                        }

                        {
                            succmsg &&
                            <div className="alert alert-success">
                                {succmsg}
                            </div>
                        }

                        <form onSubmit={(e) => toChangePassword(e)}>

                            <div className="mb-4">
                                <label className="form-label fw-bold">
                                    New Password
                                </label>

                                <div className="input-group">
                                    <span className="input-group-text">
                                        <i className="bi bi-key-fill"></i>
                                    </span>

                                    <input type="text"className="form-control"placeholder="Enter new password"requiredvalue={passsword || ""}
                                        onChange={(e) => setPassword(e.target.value)}/>
                                </div>
                            </div>

                            <button type="submit"className="btn btn-primary w-100 password-btn">
                                <i className="bi bi-arrow-repeat me-2"></i>
                                Change Password
                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </div>
    )
}

export default PasswordAirlinesReset