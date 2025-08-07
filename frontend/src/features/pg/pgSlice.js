import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axiosInstance from "../../api/axios";

// Create PG - with formData (multipart)
export const addPg = createAsyncThunk("pg/addPg", async (pgData, thunkAPI) => {
  try {
    const res = await axiosInstance.post("/api/pgs", pgData);
    if (res.status === 201) {
      return res.data;
    }
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response?.data || "Add PG failed");
  }
});

// Get All PGs - for admin or filtered view
export const getAllPgs = createAsyncThunk(
  "pg/getAllPgs",
  async (_, thunkAPI) => {
    try {
      const res = await axiosInstance.get("/api/pgs");
      return res.data.content || [];
    } catch (error) {
      return thunkAPI.rejectWithValue(
        error.response?.data || "Failed to fetch PGs"
      );
    }
  }
);

const initialState = {
  loading: false,
  error: null,
  pg: null, // single added PG
  pgList: [], // all PGs fetched
};

const pgSlice = createSlice({
  name: "pg",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      // Add PG
      .addCase(addPg.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(addPg.fulfilled, (state, action) => {
        state.loading = false;
        state.pg = action.payload;
        state.error = null;
      })
      .addCase(addPg.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload || "Failed to add PG";
      })

      // Get All PGs
      .addCase(getAllPgs.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(getAllPgs.fulfilled, (state, action) => {
        state.loading = false;
        state.pgList = action.payload;
        state.error = null;
      })
      .addCase(getAllPgs.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload || "Failed to fetch PGs";
      });
  },
});

export const { actions, reducer } = pgSlice;
export default reducer;
