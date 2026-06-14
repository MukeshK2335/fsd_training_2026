import { configureStore } from "@reduxjs/toolkit";
import { PassenegrReducer } from "./store/reducer/PassenegrReducer";

const store=configureStore({
    reducer :{
        passengers:PassenegrReducer
    }
})

export default store