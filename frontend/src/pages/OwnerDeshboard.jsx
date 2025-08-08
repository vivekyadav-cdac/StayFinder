import React, { useEffect, useState } from "react";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import LogOutButton from "../components/LogOutButton";
import { pgDataList } from "./PGList";
import PGCard from "../components/PGCard";
import { getPgsByOwner } from "../features/pg/pgSlice";

const OwnerDashboard = () => {
  const [pgs, setPgList] = useState([]);
  const dispatch = useDispatch();

  const { role } = useSelector((state) => state.auth);
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId"); // This must be saved in login thunk

  const navigate = useNavigate();
  const { pgList, loading, error } = useSelector((state) => state.pg);

  useEffect(() => {
    if (!token || role !== "OWNER") {
      navigate("/login");
      return;
    }

    if (userId) {
      dispatch(getPgsByOwner(userId));
    }
  }, [dispatch, token, role, userId, navigate]);


  const deletePG = async (pgId) => {
    // if (!window.confirm("Are you sure you want to delete this PG?")) return;
    // try {
    //   await axios.delete(`http://localhost:8000/api/pgs/${pgId}`, {
    //     headers: {
    //       Authorization: `Bearer ${token}`,
    //       "X-User-Id": userId,
    //     },
    //   });
    //   setPgList((prev) => prev.filter((pg) => pg.id !== pgId));
    // } catch (err) {
    //   alert("Delete failed");
    //   console.error(err);
    // }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <div className="max-w-6xl mx-auto p-6">
        {/* {loading && <p>Loading...</p>}
        {error && <p className="text-red-500">{error}</p>}
        {pgList.length === 0 && !loading && <p>No PGs found.</p>} */}

        {/* <div className="grid grid-cols-3 md:grid-cols-2 lg:grid-cols-3 gap-6 bg-dark">
          {pgList.map((pg) => (
            <PGCard data={pg}/>
          ))}
        </div> */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 m-5">
          {pgList.map((pg, index) => (
            <PGCard key={index} data={pg} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default OwnerDashboard;
