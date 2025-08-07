import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import FindPG from "../pages/FindPG";
import PGDetails from "../pages/PGDetails";
import Unauthorized from "../pages/Unauthorized";
import ProtectedRoute from "../pages/ProtectedRoute";

// Your role-specific dashboard components
import PublicRoute from "./PublicRoute";
import AddPG from "../pages/AddPg";
import OwnerDashboard from "../pages/OwnerDeshboard";
import UserProfile from "../pages/UserProfile";

export default function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <PublicRoute>
              <Login />
              {/* <OwnerDashboard/> */}
            </PublicRoute>
          }
        />
        <Route
          path="/register"
          element={
            <PublicRoute>
              <Register />
            </PublicRoute>
          }
        />
        <Route path="/pgdetails" element={<PGDetails />} />

        {/* Protected Routes */}
        <Route
          path="/admin"
          element={
            <ProtectedRoute allowedRoles={["admin"]}>
              {/* Admin Deshboard */}
            </ProtectedRoute>
          }
        />
        <Route
          path="/pg-owner"
          element={
            <ProtectedRoute allowedRoles={["OWNER"]}>
             <OwnerDashboard/>
            </ProtectedRoute>
          }
        />
        <Route
          path="/tenant"
          element={
            <ProtectedRoute allowedRoles={["TENANT"]}>
              <FindPG />
            </ProtectedRoute>
          }
        />

        <Route path="/unauthorized" element={<Unauthorized />} />
        <Route path="/add-pg" element={<AddPG/>}/>
        <Route path="/user-profile" element={<UserProfile />} />
      </Routes>
    </BrowserRouter>
  );
}
