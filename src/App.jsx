import { Route, Routes } from "react-router-dom"
import Home from "./pages/Home"
import Auth from "./pages/Auth"
import PassengerDashboard from "./pages/PassengerDashboard"
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
import ScheduleAirlines from "./components/airlines-component/ScheduleAirlines"
import PaymentAirlines from "./components/airlines-component/PaymentAirlines"
import CancellationAirlines from "./components/airlines-component/CancellationAirlines"
import CancellationRequest from "./components/airlines-component/CancellationRequest"
import PublicSearch from "./components/passenger-components/PublicSearch"
import Landing from "./components/Landing"
import PassenegrSearch from "./components/passenger-components/PassenegerSearch"
import BookTicket from "./components/passenger-components/Booking"
import PaymentPass from "./components/passenger-components/Payments"
import BookingHistory from "./components/passenger-components/BookingHistory"
import AddCancellation from "./components/passenger-components/AddCancellation"
import CancellationHistory from "./components/passenger-components/CancelationHistory"
import ViewTicket from "./components/passenger-components/ViewTicket"
import Ticket from "./components/passenger-components/Ticket"
import PassengerProfile from "./components/passenger-components/PassenegrProfile"
import WidgetsPasseneger from "./components/passenger-components/WidegetPassenger"
import ProfileAirlines from "./components/airlines-component/ProfileAirlines"
import AirlinesDashboard from "./pages/AirlinesDashboard"
import PasswordAirlinesReset from "./components/airlines-component/PasswordAirlinesReset"
import PasswordPassengerReset from "./components/passenger-components/PasswordPassenegrReset"

function app(){

  return(

    <div>
      <Routes>
        <Route path="/" element={<Home/>}>
                <Route path="/" element={<Landing/>}></Route>
                <Route path="search" element={<PublicSearch/>}></Route>
        </Route>
        <Route path="/login" element={<Auth/>}></Route>
        <Route path="/passenger" element={<PassengerDashboard/>}>
        <Route path="" element={<WidgetsPasseneger/>}></Route>
                <Route path="search" element={<PassenegrSearch/>}></Route>
                <Route path="booking/:scheduleId" element={<BookTicket/>}></Route>
                <Route path="payment/:bookingId" element={<PaymentPass/>}></Route>
                <Route path="booking-history" element={<BookingHistory/>}></Route>
                <Route path="cancellation/:bookingId" element={<AddCancellation/>}></Route>
                <Route path="cancellation-history" element={<CancellationHistory/>}></Route>
                <Route path="view-ticket" element={<ViewTicket/>}></Route>
                <Route path="ticket/:bookingId" element={<Ticket/>}></Route>
                <Route path="profile" element={<PassengerProfile/>}></Route>
                <Route path="change-password" element={<PasswordPassengerReset/>}></Route>
        </Route>
        <Route path="/airlines" element={<AirlinesDashboard/>}>
                <Route path="" element={<WidgetsAirlines/>}></Route>
                <Route path="add-flight" element={<AddFlight/>}></Route>
                <Route path="my-flights" element={<MyFlights/>}></Route>
                <Route path="add-schedule" element={<AddSchedule/>}></Route>
                <Route path="schedules" element={<ScheduleAirlines/>}></Route>
                <Route path="bookings" element={<BookingAirlines/>}></Route>
                <Route path="payments" element={<PaymentAirlines/>}></Route>
                <Route path="cancellations" element={<CancellationAirlines/>}></Route>
                <Route path="cancellation-request" element={<CancellationRequest/>}></Route>
                <Route path="profile" element={<ProfileAirlines/>}></Route>
                <Route path="change-password" element={<PasswordAirlinesReset/>}></Route>
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