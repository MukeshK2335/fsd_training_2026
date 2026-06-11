import { useNavigate } from "react-router-dom"
import { Link } from "react-router-dom"


function SideBArAdmin() {
    const navigate = useNavigate()
    const toLogOut = () => {

        localStorage.clear()
        navigate("/login")
    }
    return (
        <aside className="sidebar">

            <div className="sidebar-content">

                <div className="logo">
                    <i className="bi bi-airplane-fill"></i>
                    <span>ATB</span>
                </div>

                <p className="menu-title">MAIN MENU</p>

                <ul className="nav flex-column">

                    <li className="nav-item">
                        <Link  to="/admin" className="nav-link active bi bi-grid">
                            Dashboard
                        </Link>
                    </li>

                    <li className="nav-item">
                        <a href="#" className="nav-link">
                            <i className="bi bi-people"></i>
                            Passengers
                        </a>
                    </li>

                    <li className="nav-item">
                        <Link to="/admin/add-airlines"className="nav-link bi bi-building">
                          Airlines
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/add-admin"className="nav-link bi bi-building">
                          Admin
                        </Link>
                    </li>

                    <li className="nav-item">
                        <a href="#" className="nav-link">
                            <i className="bi bi-airplane"></i>
                            Flights
                        </a>
                    </li>

                    <li className="nav-item">
                        <Link  to="/admin/add-route" className="nav-link bi bi-signpost">
                        Routes
                        </Link>
                       
                    </li>

                    <li className="nav-item">
                        <a href="#" className="nav-link">
                            <i className="bi bi-calendar-event"></i>
                            Schedules
                        </a>
                    </li>

                    <li className="nav-item">
                        <a href="#" className="nav-link">
                            <i className="bi bi-ticket-perforated"></i>
                            Bookings
                        </a>
                    </li>

                    <li className="nav-item">
                        <a href="#" className="nav-link">
                            <i className="bi bi-credit-card"></i>
                            Payments
                        </a>
                    </li>

                    <li className="nav-item">
                        <a href="#" className="nav-link">
                            <i className="bi bi-x-circle"></i>
                            Cancellations
                        </a>
                    </li>

                </ul>

            </div>

            <div className="logout-container">
                <button onClick={() => toLogOut()} className="logout-btn">
                    <i className="bi bi-box-arrow-right"></i>
                    Logout
                </button>
            </div>

        </aside>
    )
}
export default SideBArAdmin