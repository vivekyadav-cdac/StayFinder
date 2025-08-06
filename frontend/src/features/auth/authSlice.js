import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const loginUser = createAsyncThunk('auth/loginUser',
    async (CredentialsContainer, thunkAPI) =>{
        const res = await axios.post('/login', CredentialsContainer);
        if(res.status === 200){
            localStorage.setItem("token", res.data.token);
            return res.data;
        }
    }
)

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        user: null,
        isAuthenticated: false,
        loading: false,
        error: null
    },
    reducers: {
        logoutUser: (state) => {
            localStorage.removeItem("token");
            state.user = null;
            state.isAuthenticated = false;
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(loginUser.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(loginUser.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload.user;
                state.isAuthenticated = true;
            })
            .addCase(loginUser.rejected, (state, action) => {
                state.loading = false;
                state.error = action.error.message;
            });
    }

})

export const { logoutUser } = authSlice.actions;
export default authSlice.reducer;
