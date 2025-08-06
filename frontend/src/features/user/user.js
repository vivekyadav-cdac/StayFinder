import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axiosInstance from "../../api/axios";

// Thunk to fetch user data
export const getUser = createAsyncThunk(
  "user/getUser",
  async (userId, thunkAPI) => {
    try {
      const res = await axiosInstance.get(`/api/users/${userId}`);
      if (res.status === 200) {
        return res.data;
      }
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response?.data || "Failed to fetch user");
    }
  }
);

// Thunk to edit user data
export const editUser = createAsyncThunk(
  "user/editUser",
  async ({ userId, userData }, thunkAPI) => {
    try {
      const res = await axiosInstance.put(`/api/users/${userId}`, userData);
      if (res.status === 200) {
        return res.data;
      }
    } catch (error) {
      return thunkAPI.rejectWithValue(error.response?.data || "Failed to update user");
    }
  }
);

const initialState = {
  user: null,
  loading: false,
  error: null,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    // Handling get user action states
    builder
      .addCase(getUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(getUser.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
        state.error = null;
      })
      .addCase(getUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload || "Failed to fetch user";
      })
      
      // Handling edit user action states
      .addCase(editUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(editUser.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
        state.error = null;
      })
      .addCase(editUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload || "Failed to update user";
      });
  },
});

export default userSlice.reducer;
