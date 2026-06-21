import axios from "axios"
import { useEffect, useState } from "react"
import "../../assets/css/Profile.css"
import { useNavigate } from "react-router-dom"

function PassengerProfile() {
    const [file, setFile] = useState()
    const [errmsg, setErrmsg] = useState()
    const [successmsg, setSuccessmsg] = useState()
    const [passenger, setPassenger] = useState()
    const [uploading, setUploading] = useState()
    const navigate=useNavigate()

    const uploadApi = "http://localhost:8080/api/passenger/id/upload"
    const profileApi = "http://localhost:8080/api/passenger/profile"

    const config = {
        headers:
        {
            Authorization: "Bearer " + localStorage.getItem("token")

        }
    }

    useEffect(() => {
        const getDetail = async () => {
            try {
                const res = await axios.get(profileApi, config)
                setPassenger(res.data)
                setErrmsg(undefined)
            }
            catch (err) {
                setErrmsg("Could Not Load the Information")
            }
        }
        getDetail()
    }, [])

    const toHandleFile = (e) => {
        setFile(e.target.files[0])
        setErrmsg(undefined)
    }

    const handleUpload = async () => {

        if (!file) {
            setErrmsg("Please select a photo first.");
            return
        }
        const formData = new FormData()
        formData.append("file", file)

        try {
            await axios.post(uploadApi, formData, config)

            const resp = await axios.get(profileApi, config)
            setPassenger(resp.data)
            setSuccessmsg("Photo uploaded successfully.")
            setFile(undefined)
            setErrmsg(undefined)
        } catch {
            setErrmsg("Upload failed. Please try again.")
        }
    }
    const initial = passenger?.name?.charAt(0)?.toUpperCase() ?? "P"
    const photoUrl = passenger?.idPath ? `/images/${passenger.idPath}` : null
    return (
        <div className="pp-content">
            <div className="pp-hero">
                <div className="pp-hero-avatar">
                    {
                        photoUrl ?
                            <img src={photoUrl} alt="Profile" />
                            : initial
                    }
                </div>
                <div>
                    <div className="pp-hero-name">{passenger?.name}</div>
                    <div className="pp-hero-email">{passenger?.email ?? ""}</div>
                </div>
                <button
                    className="pp-change-pwd-btn"
                    style={{ position: "absolute", top: "1rem", right: "1rem" }}
                    onClick={() => navigate("/passenger/change-password")}
                >
                    🔒 Reset Password
                </button>
            </div>
            <div className="pp-grid">
                <div className="pp-panel">
                    <div className="pp-panel-head">
                        <div className="pp-panel-head-icon blue">👤</div>
                        <div>
                            <h3>Personal Information</h3>
                            <p>Your basic profile details</p>
                        </div>
                    </div>
                    <div className="pp-panel-body">
                        <div className="pp-field">
                            <div className="pp-field-label">Full Name</div>
                            <div className="pp-field-value">{passenger?.name ?? "—"}</div>
                        </div>
                        <div className="pp-field">
                            <div className="pp-field-label">Gender</div>
                            <div className="pp-field-value">{passenger?.gender ?? "—"}</div>
                        </div>
                        <div className="pp-field">
                            <div className="pp-field-label">Contact Number</div>
                            <div className="pp-field-value">{passenger?.contactNumber ?? "—"}</div>
                        </div>
                        <div className="pp-field">
                            <div className="pp-field-label">Address</div>
                            <div className="pp-field-value">{passenger?.address ?? "—"}</div>
                        </div>
                    </div>
                </div>
                <div className="pp-panel">
                    <div className="pp-panel-head">
                        <div className="pp-panel-head-icon purple">📷</div>
                        <div>
                            <h3>ID Photo</h3>
                            <p>Upload a clear photo of your ID</p>
                        </div>
                    </div>
                    <div className="pp-panel-body">
                        {
                            successmsg !== undefined ?
                                <div className="pp-alert pp-alert--success">✓ {successmsg}</div>
                                : ""
                        }
                        {
                            errmsg !== undefined ?
                                <div className="pp-alert pp-alert--error">! {errmsg}</div>
                                : ""
                        }
                        <div className="pp-dropzone">
                            <input type="file" accept="image/*"
                                onChange={(e) => toHandleFile(e)} />
                            <div className="pp-dropzone-icon">📂</div>
                            <div className="pp-dropzone-text">
                                {
                                    file ? <><span>{file.name}</span> ready</>
                                        : <><span>Choose a file</span> or drag it here</>
                                }
                            </div>
                            {!file && <div className="pp-dropzone-sub">JPG, PNG, WEBP</div>}
                        </div>
                        <button className="pp-btn" onClick={(e)=>handleUpload(e)} disabled={!file || uploading}>
                            {uploading ? "Uploading…" : "Upload ID Photo"}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PassengerProfile