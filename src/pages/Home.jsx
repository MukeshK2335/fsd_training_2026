import { Outlet } from "react-router-dom"
import Landing from "../components/Landing"
import NavBar from "../components/NavBar"

function Home(){

    return(

        <div>
            <NavBar/>
           <Outlet/>

        </div>
    )
}
export default Home