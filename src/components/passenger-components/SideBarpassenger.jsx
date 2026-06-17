import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

function SideBarPassenger() {

    const navigate = useNavigate();

    const toLogOut = () => {
        localStorage.clear();
        navigate("/login");
    };

    return (
        <aside className="sidebar">

            <div className="sidebar-content">

                <div className="logo">
                    <i className="bi bi-airplane-fill"></i>
                    <span>ATBS</span>
                </div>

                <p className="menu-title">PASSENGER MENU</p>

                <ul className="nav flex-column">

                    <li className="nav-item">
                        <Link to="/passenger" className="nav-link bi bi-grid">
                            Dashboard
                        </Link>
                    </li>

                    <li className="nav-item">
                        <Link to="/passenger/profile" className="nav-link bi bi-person-circle">
                            Profile Information
                        </Link>
                    </li>

                    <li className="nav-item">
                        <Link to="/passenger/search" className="nav-link bi bi-search">
                            Search Flights
                        </Link>
                    </li>

                    {/* <li className="nav-item">
                        <Link to="/passenger/book-ticket" className="nav-link bi bi-ticket-perforated">
                            Book Ticket
                        </Link>
                    </li> */}

                    <li className="nav-item">
                        <Link to="/passenger/booking-history" className="nav-link bi bi-journal-check">
                            My Bookings
                        </Link>
                    </li>

                    {/* <li className="nav-item">
                        <Link to="/passenger/upcoming-trips" className="nav-link bi bi-airplane-engines">
                            Upcoming Trips
                        </Link>
                    </li> */}

                    <li className="nav-item">
                        <Link to="/passenger/cancellation-history" className="nav-link bi bi-x-circle">
                            Cancelled Tickets
                        </Link>
                    </li>

                    <li className="nav-item">
                        <Link to="/passenger/view-ticket" className="nav-link bi bi-download">
                            View Ticket
                        </Link>
                    </li>

                </ul>

            </div>

            <div className="logout-container">
                <button onClick={toLogOut} className="logout-btn">
                    <i className="bi bi-box-arrow-right"></i>
                    Logout
                </button>
            </div>

        </aside>
    );
}

export default SideBarPassenger;