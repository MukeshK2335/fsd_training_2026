function NavBarPassenger(){

    return(
        <div className="top-navbar">

            <h3>Air Ticket Booking</h3>

            <div className="user-box">
                <i className="bi bi-person-circle"></i>

                <div>
                    <small>Welcome Back</small>
                    <strong>{localStorage.getItem("username")}</strong>
                </div>
            </div>

        </div>
    )
}
export default NavBarPassenger