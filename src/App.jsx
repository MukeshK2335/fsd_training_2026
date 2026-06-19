import { Route, Routes } from "react-router-dom";
import Dashboard from "./component/Dashboard";
import UserList from "./component/UserList";
import AddUser from "./component/AddUser";



function App(){ 
  return (   
    <div>

  
      <Routes>
        <Route path="/" element={<Dashboard/>}>
         <Route path="users" element={<UserList/>}></Route>
         <Route path="add-user" element={<AddUser/>}></Route>
        </Route>
       
      </Routes>
    </div>
  )

}


export default App; 