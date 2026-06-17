import axios from "axios"
import { useEffect, useState } from "react"
import "../../assets/css/Profile.css"

function ProfileAirlines() {
    const [errmsg, setErrmsg] = useState()
    const [owner, setOwner] = useState()

    const profileApi = "http://localhost:8080/api/flight-owner/profile"

    const config = {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        }
    }

    useEffect(() => {
        const getDetail = async () => {
            try {
                const res = await axios.get(profileApi, config)
                setOwner(res.data)
                setErrmsg(undefined)
            } catch (err) {
                setErrmsg("Could Not Load the Information")
            }
        }
        getDetail()
    }, [])

    const initial = owner?.companyName?.charAt(0)?.toUpperCase() ?? "A"

    return (
        <div className="pp-content">
            {errmsg && <div className="alert alert-danger">{errmsg}</div>}

            <div className="pp-hero">
                <div className="pp-hero-avatar">
                    <span style={{ fontSize: "2rem", fontWeight: "bold", color: "#fff" }}>
                        {initial}
                    </span>
                </div>
                <div>
                    <div className="pp-hero-name">{owner?.companyName ?? "Airline Company"}</div>
                    <div className="pp-hero-email">{owner?.contactEmail ?? ""}</div>
                </div>
                <button
                    className="pp-change-pwd-btn"
                    style={{ position: "absolute", top: "1rem", right: "1rem" }}
                    onClick={() => navigate("/airlines/change-password")}
                >
                    🔒 Change Password
                </button>
            </div>

            <div className="pp-grid">
                <div className="pp-panel">
                    <div className="pp-panel-head">
                        <div className="pp-panel-head-icon blue">✈️</div>
                        <div>
                            <h3>Airline Information</h3>
                            <p>Your airline company details</p>
                        </div>
                    </div>
                    <div className="pp-panel-body">
                        <div className="pp-field">
                            <div className="pp-field-label">Company Name</div>
                            <div className="pp-field-value">{owner?.companyName ?? "—"}</div>
                        </div>
                        <div className="pp-field">
                            <div className="pp-field-label">Contact Email</div>
                            <div className="pp-field-value">{owner?.contactEmail ?? "—"}</div>
                        </div>
                        <div className="pp-field">
                            <div className="pp-field-label">Contact Number</div>
                            <div className="pp-field-value">{owner?.contactNumber ?? "—"}</div>
                        </div>
                        <div className="pp-field">
                            <div className="pp-field-label">Address</div>
                            <div className="pp-field-value">{owner?.address ?? "—"}</div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    )
}

export default ProfileAirlines