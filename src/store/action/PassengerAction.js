import axios from "axios"

const config = {
    headers: {
        'Authorization': "Bearer " + localStorage.getItem('token')
    }
}

const getAllApi="http://localhost:8080/api/passenger/all"

export const getAll=()=>{
    return async (dispatch)=>{
         console.log("getAll action called");
        const response=await axios.get(getAllApi,config)
        let action={
            type:"GET_ALL",
            payload:response.data.data
        }
        dispatch(action)

    }
}