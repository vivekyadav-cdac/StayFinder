import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/auth/authSlice";
import pgListReducer from "../features/place/placesSlice";
import userReducer from "../features/user/userSlice";

export const store = configureStore({
    reducer:{
        auth: authReducer,
        pgList: pgListReducer,
        user: userReducer, 
    },
});