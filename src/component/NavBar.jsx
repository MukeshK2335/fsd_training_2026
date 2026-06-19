import { useNavigate } from "react-router-dom"
import { Link } from "react-router-dom";


function NavBar(){

    const navigate=useNavigate()
    const username=localStorage.getItem("username")
    
    return(

        <header className="navbar navbar-expand-lg bg-white shadow-sm border border-light rounded-3 px-4 py-3 mb-3 d-flex justify-content-between align-items-center">
        <div className="d-flex align-items-center ">
            <div className="cms-logo d-flex align-items-center justify-content-center fw-black bg-dark text-white rounded-2 px-3 py-1 font-monospace shadow-sm">
            User Management
            </div>
             <Link to="/users" className="navbar-link fw-bold text-dark fs-5 tracking-tight text-decoration-none transition-all ms-4">
             User List
            </Link>
            <Link to="/add-user" className="navbar-link fw-bold text-dark fs-5 tracking-tight text-decoration-none transition-all ms-4">
            Add User
            </Link>
            <Link to="/info" className="navbar-link fw-bold text-dark fs-5 tracking-tight text-decoration-none transition-all ms-4">
            Info
            </Link>
        </div>
      
    </header>
    )
}

export default NavBar