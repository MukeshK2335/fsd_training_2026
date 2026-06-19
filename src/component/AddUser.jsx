import axios from "axios"
import { useState } from "react"

function AddUser(){
    const [name,setName]=useState()
    const [email,setEmail]=useState()
    const [phone,setPhone]=useState()
    const [comapny,setCompany]=useState()
    const [errmsg,setErrmsg]=useState()
    const [succmsg,setSuccmsg]=useState()

    const addApi="https://jsonplaceholder.typicode.com/users"

    const toAddUser= async (e)=>{
        e.preventDefault()

        let body={
            "name":name,
            "email":email,
            "phone":phone,
            "company":comapny
        }
        try{
            const res=await axios.post(addApi,body)
            setSuccmsg("Added Successfully")
            setErrmsg(undefined)
        }
        catch(err){
            console.log(err)
            setErrmsg("Failed to Add")
            setSuccmsg(undefined)
        }
    }
    return(
        <div className="container d-flex justify-content-center align-items-center flex-grow-1">
            <div className="row w-100">
            <div className="col-sm-3"></div>

            <div className="col-sm-6">
                <div className="card">
                    <div className="card-header text-center">
                        Add User
                    </div>
                    <div className="card-body">
                        <form onSubmit={(e)=>toAddUser(e)}>
                             {errmsg !== undefined ?
                                    <div className="alert alert-danger rounded-3 py-2 mb-3">
                                        {errmsg}
                                    </div> : ""}
                                {succmsg !== undefined ?
                                    <div className="alert alert-success rounded-3 py-2 mb-3">
                                        {succmsg}
                                    </div> : ""}

                            <div className="mt-4">
                                <label>Name</label>
                                <input type="text" className="form-control" onChange={(e)=>setName(e.target.value)}/>
                            </div>
                            <div className="mt-4">
                                <label>Email</label>
                                <input type="text" className="form-control" onChange={(e)=>setEmail(e.target.value)}/>
                            </div>
                            <div className="mt-4">
                                <label>Phone</label>
                                <input type="text" className="form-control" onChange={(e)=>setPhone(e.target.value)}/>
                            </div>
                            <div className="mt-4">
                                <label>Comapny Name</label>
                                <input type="text" className="form-control" onChange={(e)=>setCompany(e.target.value)}/>
                            </div>
                            <div className="mt-4 d-flex justify-content-center">
                                <input type="submit" value="Add user" className="btn btn-primary"/>
                            </div>
                        </form>
                    </div>
                    <div className="card-footer">

                    </div>
                </div>
            </div>
            <div className="col-sm-3"></div>
            </div>

        </div>
    )
}
export default AddUser