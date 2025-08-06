import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/auth/authSlice";
import pgListReducer from "../features/place/placesSlice";

export const store = configureStore({
    reducer:{
        auth: authReducer,
        pgList: pgListReducer,
    },
});