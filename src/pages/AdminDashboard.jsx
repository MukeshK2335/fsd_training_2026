import NavBarAdmin from "../components/admin-components/NavbarAdmin"
import SideBArAdmin from "../components/admin-components/SideBarAdmin"
import WidgetsAdmin from "../components/admin-components/WidgetsAdmin"

import '../assets/css/admin-dashboard.css'
import OnboardingAirline from "../components/admin-components/OnboardingAirline"
import { Outlet } from "react-router-dom"

function AdminDashboard(){

    return(
        <div className="wrapper">
           <SideBArAdmin/>
           <div className="main-content">
            <NavBarAdmin/>
            <Outlet/>
           </div>
        </div>
    )
}
export default AdminDashboard