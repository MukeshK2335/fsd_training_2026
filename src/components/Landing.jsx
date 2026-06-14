import { useNavigate } from "react-router-dom"
import "../assets/css/landing.css"
import Login from "../auth/Login"

function Landing(){
    const navigate=useNavigate()

    return(
        <div>

        
         <section className="hero-section">
                <div className="container">
                    <div className="row align-items-center min-vh-100">

                        <div className="col-lg-6">
                            <span className="hero-badge">
                                ✈️ Trusted Airline Booking Platform
                            </span>

                            <h1 className="hero-title">
                                Fly Anywhere,
                                <span className="text-primary"> Book Anytime</span>
                            </h1>

                            <p className="hero-subtitle">
                                Discover the best flight deals, instant bookings,
                                secure payments and seamless travel experiences.
                            </p>

                            <div className="hero-buttons">
                                <button  onClick={()=>navigate("/login")}className="btn btn-primary btn-lg">
                                    Book Flight
                                </button>

                                <button className="btn btn-outline-primary btn-lg ms-3">
                                    Explore Routes
                                </button>
                            </div>
                        </div>

                        <div className="col-lg-6 text-center">
                            <img
                                src="https://images.unsplash.com/photo-1436491865332-7a61a109cc05"
                                alt="Flight"
                                className="img-fluid hero-image"
                            />
                        </div>

                    </div>
                </div>
            </section>
            </div>
    )
}
export default Landing