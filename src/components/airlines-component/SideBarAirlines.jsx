import { useNavigate } from "react-router-dom"
import { Link } from "react-router-dom"
function SideBarAirlines() {

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
                        <Link  to="/airlines" className="nav-link active bi bi-grid">
                            Dashboard
                        </Link>
                    </li>
                    
                    <li className="nav-item">
                         <Link  to="/airlines/my-flights" className="nav-link bi bi-airplane">
                          My Flights
                        </Link>
                    </li>
                    {/* <li className="nav-item">
                        <Link  to="/admin/routes" className="nav-link bi bi-signpost">
                        Routes
                        </Link>
                    </li> */}
                    <li className="nav-item">
                        <Link to="/airlines/schedules" className="nav-link bi bi-calendar-event">
                        My Schedules
                        </Link>
                    </li>

                    <li className="nav-item">
                        <Link to="/airlines/bookings" className="nav-link bi bi-ticket-perforated">
                        My Bookings
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/airlines/payments
                        " className="nav-link bi bi-credit-card">
                        My Payments
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/airlines/cancellation-request" className="nav-link bi bi-x-circle">
                         Cancellations Request
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/airlines/cancellations" className="nav-link bi-calendar-event">
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
export default SideBarAirlines