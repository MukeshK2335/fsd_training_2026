import axios from "axios"

const config = {
    headers: {
        'Authorization': "Bearer " + localStorage.getItem('token')
    }
}
const size=3;

const getAllApi="http://localhost:8080/api/passenger/all"

export const getAll=(currentPage)=>{
    return async (dispatch)=>{
         console.log("getAll action called");
        const response=await axios.get(getAllApi+`?page=${currentPage}&size=${size}`,config)
        let action={
            type:"GET_ALL",
            payload:response.data.data,
            page:response.data.totalPages,
            record:response.data.totalRecords
        }
        dispatch(action)

    }
}