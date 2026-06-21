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
                        <Link to="/admin/passengers" className="nav-link bi bi-people">
                        Passengers
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/airlines"className="nav-link bi bi-building">
                          Airlines
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/add-admin"className="nav-link bi bi-building">
                          Admin
                        </Link>
                    </li>
                    <li className="nav-item">
                         <Link  to="/admin/flights" className="nav-link bi bi-airplane">
                          Flights
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link  to="/admin/routes" className="nav-link bi bi-signpost">
                        Routes
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/schedules" className="nav-link bi bi-calendar-event">
                        Schedules
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/bookings" className="nav-link bi bi-ticket-perforated">
                        Bookings
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/payments" className="nav-link bi bi-credit-card">
                        Payments
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/admin/cancellations" className="nav-link bi bi-x-circle">
                         Cancellations
                        </Link>
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