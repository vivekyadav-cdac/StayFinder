import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { getAllUsers } from "../features/user/userSlice";
import axiosInstance from "../api/axios";
import LogOutButton from "../components/LogOutButton";
import UserList from "../components/UserList";
import PGList from "../components/PgList";

const AdminDashboard = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { users, loading, error } = useSelector((state) => state.user);
  const { role } = useSelector((state) => state.auth);
  const token = localStorage.getItem("token");

  const [pgs, setPgs] = useState([]);

  useEffect(() => {
    if (!token || role !== "ADMIN") {
      navigate("/login");
    } else {
      dispatch(getAllUsers());
      fetchPGs();
    }
  }, [dispatch]);

  const fetchPGs = async () => {
    try {
      const res = await axiosInstance.get("/api/pgs");
      setPgs(res.data.content || []);
    } catch (err) {
      console.error("Failed to load PGs:", err);
    }
  };

  const handleDeleteUser = async (userId) => {
    try {
      await axiosInstance.delete(`/api/v1/user/${userId}`);
      dispatch(getAllUsers());
    } catch (error) {
      console.error("Error deleting user:", error);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <nav className="navbar navbar-expand-lg navbar-light bg-white shadow-sm px-4">
        <Link className="navbar-brand" to="/">
          StayFinder
        </Link>
        <div className="ms-auto">
          <LogOutButton />
        </div>
      </nav>

      <div className="max-w-7xl mx-auto p-6">
        <h1 className="text-2xl font-bold mb-4">Admin Dashboard</h1>

        {loading && <p>Loading...</p>}
        {error && <p className="text-red-500">{error}</p>}

        <div className="flex justify-center">
          <UserList users={users} onDelete={handleDeleteUser} />
        </div>
        <PGList pgs={pgs} />
      </div>
    </div>
  );
};

export default AdminDashboard;
