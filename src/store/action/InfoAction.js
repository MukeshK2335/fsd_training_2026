import axios from "axios";


const getAllApi="https://rickandmortyapi.com/api/character/"

export const getAll=(page)=>{
    return async (dispatch)=>{
        const response=await axios.get(getAllApi+`?page=${page}`)
        let action={
            type:"GET_ALL",
            payload:response.data
        }
        dispatch(action)

    }
}