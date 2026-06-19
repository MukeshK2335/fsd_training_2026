const initialState={
    info:[],
    totalPages: 1
}

export const InfoReducer=(state=initialState,action)=>{
    if(action.type === "GET_ALL"){
        return{
            ...state,
            info:action.payload.results,
            totalPages: action.payload.info.pages
        }
    }
    return state
}