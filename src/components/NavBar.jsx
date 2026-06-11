import { Link } from "react-router-dom";
import "../assets/css/public-navbar.css";

function NavBar() {

    return (

        <nav className="navbar navbar-expand-lg fixed-top public-navbar">

            <div className="container">


                {/* Brand */}
                <Link
                    to="/"
                    className="navbar-brand d-flex align-items-center"
                >

                    <span className="brand-logo">
                        ✈️
                    </span>

                    <div className="ms-2">

                        <div className="brand-name">
                            ATB
                        </div>

                        <small className="brand-sub">
                            Air Travel Booking
                        </small>

                    </div>

                </Link>




                {/* Toggle */}

                <button
                    className="navbar-toggler shadow-none"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#publicNav"
                >

                    <span className="navbar-toggler-icon"></span>

                </button>





                <div
                    className="collapse navbar-collapse"
                    id="publicNav"
                >



                    {/* Menu */}

                    <ul className="navbar-nav mx-auto mb-2 mb-lg-0 gap-lg-2">


                        <li className="nav-item">

                            <Link
                                to="/"
                                className="nav-link active"
                            >
                                Home
                            </Link>

                        </li>



                        <li className="nav-item">

                            <Link
                                to="/flights"
                                className="nav-link"
                            >
                                Flights
                            </Link>

                        </li>



                        <li className="nav-item">

                            <Link
                                to="/about"
                                className="nav-link"
                            >
                                About
                            </Link>

                        </li>



                        <li className="nav-item">

                            <Link
                                to="/contact"
                                className="nav-link"
                            >
                                Contact
                            </Link>

                        </li>


                    </ul>





                    {/* Actions */}

                    <div className="navbar-actions d-flex gap-3">


                        <Link to="/login">

                            <button className="btn btn-outline-primary px-4 rounded-pill">

                                Login

                            </button>

                        </Link>




                        <Link to="/sign-up">

                            <button className="btn btn-primary px-4 rounded-pill signup-button">

                                Get Started

                            </button>

                        </Link>


                    </div>



                </div>


            </div>

        </nav>

    )

}


export default NavBar;