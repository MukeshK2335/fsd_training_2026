import { Outlet } from "react-router-dom"
import NavBarPassenger from "../components/passenger-components/NavBarPassenger"
import SideBarPassenegr from "../components/passenger-components/SideBarpassenger"

function PassengerDashboard(){

    return(
       <div className="wrapper">
        <SideBarPassenegr/>
           <div className="main-content">
            <NavBarPassenger/>
            <Outlet/>
           </div>
        </div>
    )
}

export default PassengerDashboard