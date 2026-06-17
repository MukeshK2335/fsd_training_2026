import { Outlet } from "react-router-dom"
import NavBarAirlines from "../components/airlines-component/NavBarAirlines"
import SideBarAirlines from "../components/airlines-component/SideBarAirlines"

function AirlinesDashboard(){

    return(

       <div className="wrapper">
        <SideBarAirlines/>
           <div className="main-content">
            <NavBarAirlines/>
            <Outlet/>
           </div>
        </div>
    )
}

export default AirlinesDashboard