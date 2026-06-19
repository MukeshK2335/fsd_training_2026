import { Route, Routes } from "react-router-dom";
import Dashboard from "./component/Dashboard";
import UserList from "./component/UserList";
import AddUser from "./component/AddUser";
import InfoList from "./component/Info";



function App(){ 
  return (   
    <div>

  
      <Routes>
        <Route path="/" element={<Dashboard/>}>
         <Route path="users" element={<UserList/>}></Route>
         <Route path="add-user" element={<AddUser/>}></Route>
         <Route path="/info" element={<InfoList/>}></Route>
        </Route>
       
      </Routes>
    </div>
  )

}


export default App; 