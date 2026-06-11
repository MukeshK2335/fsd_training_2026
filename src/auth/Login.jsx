import { useState } from "react"
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../assets/css/login.css";


function Login() {

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [errmsg, setErrmsg] = useState("")

    const navigate = useNavigate()

    const loginApi = "http://localhost:8080/api/auth/login"
    const userDetailApi = "http://localhost:8080/api/auth/get-details"


    const toLogin = async (e) => {

        e.preventDefault();

        try {

            const config = {
                headers: {
                    Authorization:
                        "Basic " + window.btoa(username + ":" + password)
                }
            }


            const response = await axios.get(loginApi, config)

            let token = response.data.token

            localStorage.setItem("token", token)
            localStorage.setItem("username", username)



            const config_detail = {
                headers: {
                    Authorization: "Bearer " + token
                }
            }


            const rep = await axios.get(userDetailApi, config_detail)

            let role = rep.data.role


            switch (role) {

                case "PASSENGER":
                    navigate("/passenger")
                    break;

                case "FLIGHT_OWNER":
                    navigate("/flight-owner")
                    break;

                case "ADMIN":
                    navigate("/admin")
                    break;

            }

        }
        catch (err) {

            setErrmsg("Invalid Credentials")

        }

    }



    return (

        <div className="login-page">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-lg-5 col-md-7 col-sm-10">
                        <div className="login-card shadow-lg">
                            <div className="text-center mb-4">
                                <div className="login-icon">
                                    ✈️
                                </div>
                                <h3 className="fw-bold">
                                    Welcome Back
                                </h3>
                                <p className="text-muted">
                                    Login to continue your journey
                                </p>
                            </div>
                            <form onSubmit={toLogin}>
                                {
                                    errmsg &&
                                    <div className="alert alert-danger rounded-3">
                                        {errmsg}
                                    </div>
                                }
                                <div className="mb-3">
                                    <label className="form-label fw-semibold">Username</label>
                                    <input type="text"className="form-control form-control-lg input-box"
                                        placeholder="Enter username"
                                        onChange={(e) => setUsername(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-semibold"> Password</label>
                                    <input type="password"className="form-control form-control-lg input-box"
                                        placeholder="Enter password"
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                </div>
                                <button className="btn login-btn btn-lg w-100"
                                    type="submit">Login </button>
                            </form>
                            <div className="text-center mt-4">
                                <small className="text-muted">
                                    © 2026 ATB Air Ticket Booking
                                </small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    )

}


export default Login