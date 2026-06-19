import { Outlet } from "react-router-dom"
import NavBar from "./NavBar"

function Dashboard(){

    return(
        <div className="bg-light min-vh-100 d-flex flex-column p-3">
            <NavBar/>
            <div className="d-flex flex-grow-1 gap-3 align-items-stretch">
                <Outlet/>
            </div>
        </div>
    )
}
export default Dashboard