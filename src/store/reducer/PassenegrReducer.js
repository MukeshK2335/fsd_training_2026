const initialState={
    passengers:[],
    Pages:1,
    totalRecord:0
}

export const PassenegrReducer=(state=initialState,action)=>{
    if(action.type === "GET_ALL"){
        return{
            ...state,
            passengers:action.payload,
            Pages:action.page,
            totalRecord:action.record
        }
    }
    return state
}