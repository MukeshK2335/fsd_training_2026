import { Route, Routes } from "react-router-dom"
import Home from "./pages/Home"
import Auth from "./pages/Auth"
import PassengerDashboard from "./pages/PassengerDashboard"
import FlightOwnerDashboard from "./pages/FlightOwnerDashboard"
import AdminDashboard from "./pages/AdminDashboard"
import SignUpPage from "./pages/SignUpPage"
import WidgetsAdmin from "./components/admin-components/WidgetsAdmin"
import OnboardingAirline from "./components/admin-components/OnboardingAirline"
import OnboardingAdmin from "./components/admin-components/OnboardingAdmin"
import RouteAdd from "./components/admin-components/RouteAdd"

function app(){

  return(

    <div>
      <Routes>
        <Route path="/" element={<Home/>}></Route>
        <Route path="/login" element={<Auth/>}></Route>
        <Route path="/passenger" element={<PassengerDashboard/>}></Route>
        <Route path="/flight-owner" element={<FlightOwnerDashboard/>}></Route>

        <Route path="/admin" element={<AdminDashboard/>}>
        <Route path="" element={<WidgetsAdmin/>}></Route>
        <Route path="add-airlines" element={<OnboardingAirline/>}></Route>
        <Route path="add-admin" element={<OnboardingAdmin/>}></Route>
        <Route path="add-route" element={<RouteAdd/>}></Route>
        </Route>

        <Route path="/sign-up" element={<SignUpPage/>}></Route>
      </Routes>
    </div>
  )
}

export default app