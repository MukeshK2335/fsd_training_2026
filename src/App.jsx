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
import Passengers from "./components/admin-components/Passengers"
import Flights from "./components/admin-components/Flights"
import Schedule from "./components/admin-components/Schedules"
import Booking from "./components/admin-components/Bookings"
import Payment from "./components/admin-components/Payments"
import Cancellation from "./components/admin-components/Cancellation"
import Rout from "./components/admin-components/Route"
import Airlines from "./components/admin-components/Airlines"
import WidgetsAirlines from "./components/airlines-component/WidgetAirlines"
import AddFlight from "./components/airlines-component/AddFlight"
import MyFlights from "./components/airlines-component/MyFlights"
import AddSchedule from "./components/airlines-component/AddSchedule"
import BookingAirlines from "./components/airlines-component/BookingAirlines"

function app(){

  return(

    <div>
      <Routes>
        <Route path="/" element={<Home/>}></Route>
        <Route path="/login" element={<Auth/>}></Route>
        <Route path="/passenger" element={<PassengerDashboard/>}></Route>
        <Route path="/airlines" element={<FlightOwnerDashboard/>}>
                <Route path="" element={<WidgetsAirlines/>}></Route>
                <Route path="add-flight" element={<AddFlight/>}></Route>
                <Route path="my-flights" element={<MyFlights/>}></Route>
                <Route path="add-schedule" element={<AddSchedule/>}></Route>
                <Route path="bookings" element={<BookingAirlines/>}></Route>

        
        </Route>

        <Route path="/admin" element={<AdminDashboard/>}>
           <Route path="" element={<WidgetsAdmin/>}></Route>
           <Route path="add-airlines" element={<OnboardingAirline/>}></Route>
           <Route path="airlines" element={<Airlines/>}></Route>
           <Route path="routes" element={<Rout/>}></Route>
               <Route path="add-route" element={<RouteAdd/>}></Route>
           <Route path="add-admin" element={<OnboardingAdmin/>}></Route>
           <Route path="passengers" element={<Passengers/>}></Route>
           <Route path="flights" element={<Flights/>}></Route>
           <Route path="schedules" element={<Schedule/>}></Route>
           <Route path="bookings" element={<Booking/>}></Route>
           <Route path="payments" element={<Payment/>}></Route>
           <Route path="cancellations" element={<Cancellation/>}></Route>
        </Route>


        <Route path="/sign-up" element={<SignUpPage/>}></Route>
      </Routes>
    </div>
  )
}

export default app