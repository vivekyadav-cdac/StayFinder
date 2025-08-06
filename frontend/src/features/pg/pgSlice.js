import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axiosInstance from "../../api/axios";

export const addPg = createAsyncThunk(
    "pg/addPg",
    async (pgData, thunkAPI) => {
        try{
            const res = await axiosInstance.post("/api/pgs", pgData);

            if(res.status === 201) {
                return res.data;
            }
        }catch (error) {
            return thunkAPI.rejectWithValue(error.response?.data || "Add PG failed");
        }


    }
)

const initialState = {
    loading : false,
    error: null,
}

const pgSlice = createSlice({
    name: "pg",
    initialState,
    reducers:{

    },
    extraReducers:(builder) =>{
        builder.addCase(addPg.pending, (state)=>{
            state.loading = true;
            state.error = null;
        }).addCase(addPg.fulfilled,(state, action)=>{
            state.loading = false;
            state.pg = action.payload; 
            if(action.payload && action.payload.data){

            }else{}
            state.error = null;
        }).addCase(addPg.rejected, (state, action)=>{
            state.loading = false;
            state.error = action.payload || "Failed to add PG";
        })
    }
});

export const { actions, reducer } = pgSlice;