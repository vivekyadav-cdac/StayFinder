import React, { useState, useEffect } from "react";
import "./FindPG.css";
import "bootstrap/dist/css/bootstrap.min.css";
import PGCard from "../components/PGCard";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import { useDispatch, useSelector } from "react-redux";
import { getAllPgs } from "../features/pg/pgSlice";

const FindPG = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { pgList, loading, error } = useSelector((state) => state.pg);
  const [pgs, setPgs] = useState([]);

  const [filters, setFilters] = useState({
    gender: "",
    roomType: "",
    maxBudget: "",
  });

  // Load PGs from API
  useEffect(() => {
    dispatch(getAllPgs());
  }, [dispatch]);

  // Update PGs to display when PG list changes
  useEffect(() => {
    setPgs(pgList);
  }, [pgList]);

  // Filter PGs on filter change
  useEffect(() => {
    let filtered = pgList;

    if (filters.gender) {
      filtered = filtered.filter(
        (pg) => pg.type?.toLowerCase() === filters.gender.toLowerCase()
      );
    }

    if (filters.roomType) {
      filtered = filtered.filter((pg) =>
        pg.rooms?.some(
          (room) =>
            room?.roomType &&
            room.roomType.toLowerCase() === filters.roomType.toLowerCase()
        )
      );
    }

    if (filters.maxBudget) {
      const max = parseInt(filters.maxBudget);
      filtered = filtered.filter((pg) =>
        pg.rooms?.some((room) => room?.rent <= max)
      );
    }

    setPgs(filtered);
  }, [filters, pgList]);

  // Get current location once
  useEffect(() => {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const { latitude, longitude } = position.coords;
          localStorage.setItem("latitude", latitude);
          localStorage.setItem("longitude", longitude);
          console.log("Current Location:", latitude, longitude);
        },
        (error) => {
          console.error("Error getting location:", error);
        }
      );
    }
  }, []);

  const handleFilterChange = (e) => {
    setFilters({
      ...filters,
      [e.target.name]: e.target.value,
    });
  };

  const clearFilters = () => {
    setFilters({
      gender: "",
      roomType: "",
      maxBudget: "",
    });
  };

  return (
    <div style={styles.container}>
      <Navbar />
      <div style={styles.body}>
        {/* Filter Section */}
        <div style={styles.filterSection}>
          <h3>Filter PGs</h3>

          <div className="mb-3">
            <label htmlFor="gender">Gender:</label>
            <select
              id="gender"
              name="gender"
              className="form-control"
              value={filters.gender}
              onChange={handleFilterChange}
            >
              <option value="">All</option>
              <option value="Boys">Boys</option>
              <option value="Girls">Girls</option>
              <option value="Unisex">Unisex</option>
            </select>
          </div>

          <div className="mb-3">
            <label htmlFor="roomType">Room Type:</label>
            <select
              id="roomType"
              name="roomType"
              className="form-control"
              value={filters.roomType}
              onChange={handleFilterChange}
            >
              <option value="">All</option>
              <option value="Single">Single</option>
              <option value="Double">Double</option>
              <option value="Triple">Triple</option>
            </select>
          </div>

          <div className="mb-3">
            <label htmlFor="maxBudget">Max Budget (₹):</label>
            <input
              type="number"
              id="maxBudget"
              name="maxBudget"
              className="form-control"
              value={filters.maxBudget}
              onChange={handleFilterChange}
            />
          </div>

          <button className="btn btn-secondary mt-3" onClick={clearFilters}>
            Clear Filters
          </button>
        </div>

        {/* PG View Section */}
        <div style={styles.pgViewSection}>
          <div className="flex-1">
            {loading ? (
              <p>Loading PGs...</p>
            ) : (
              <div className="pg-grid">
                {pgs.length === 0 ? (
                  <p>No PGs match the selected filters.</p>
                ) : (
                  pgs.map((pg) => (
                    <PGCard
                      key={pg.id}
                      data={pg}
                      onCardClick={() => {
                        navigate("/pgdetails", { state: { pg } });
                      }}
                    />
                  ))
                )}
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

const styles = {
  container: {
    height: "100vh",
    display: "flex",
    flexDirection: "column",
    fontFamily: "sans-serif",
  },
  body: {
    display: "flex",
    flex: 1,
  },
  filterSection: {
    width: "350px",
    backgroundColor: "#f1f5f9",
    padding: "16px",
    borderRight: "1px solid #cbd5e1",
  },
  pgViewSection: {
    flex: 1,
    padding: "16px",
    alignItems: "center",
  },
};

export default FindPG;
