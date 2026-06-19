import { configureStore } from "@reduxjs/toolkit";
import { InfoReducer } from "./store/reducer/infoReducer";


const store=configureStore({

    reducer:{
        info:InfoReducer
    }

    
})
export default store