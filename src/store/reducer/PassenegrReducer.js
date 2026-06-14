const initialState={
    passengers:[]
}

export const PassenegrReducer=(state=initialState,action)=>{
    if(action.type === "GET_ALL"){
        return{
            ...state,
            passengers:action.payload
        }
    }
    return state
}