import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/auth/authSlice";
import userReducer from "../features/user/userSlice";
import pgReducer from "../features/pg/pgSlice";
import roomReducer from "../features/pg/roomSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    user: userReducer,
    pg: pgReducer,
    room: roomReducer,
  },
});
