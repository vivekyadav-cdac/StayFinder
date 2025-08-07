// src/features/user/userSlice.js
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axiosInstance from "../../api/axios";

// Fetch user
export const getUser = createAsyncThunk(
  "user/getUser",
  async (userId, thunkAPI) => {
    try {
      const res = await axiosInstance.get(`/api/v1/user/${userId}`);
      console.log("Fetched user:", res.data);
      return res.data;
    } catch (error) {
      console.error("Error fetching user:", error);
      if (error.response?.status === 401) {
        localStorage.removeItem("token");
        localStorage.removeItem("userId");
        window.location.href = "/";
      }
      return thunkAPI.rejectWithValue(
        error.response?.data || "Failed to fetch user"
      );
    }
  }
);

// Edit user
export const editUser = createAsyncThunk(
  "user/editUser",
  async ({ userId, userData }, thunkAPI) => {
    try {
      const res = await axiosInstance.put(`/api/v1/user/${userId}`, userData);
      return res.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(
        error.response?.data || "Failed to update user"
      );
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
    builder
      .addCase(getUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(getUser.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
      })
      .addCase(getUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(editUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(editUser.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
      })
      .addCase(editUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export default userSlice.reducer;
