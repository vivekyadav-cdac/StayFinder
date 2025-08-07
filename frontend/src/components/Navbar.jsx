import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css"; // For extra styling
import { useDispatch } from "react-redux";
import { logoutUser } from "../features/auth/authSlice";

const Navbar = () => {
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const role = localStorage.getItem("role");

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    dispatch(logoutUser());
    navigate("/", { replace: true });
  };

  const handleSearch = () => {
    if (searchTerm.trim()) {
      navigate(`/search?query=${encodeURIComponent(searchTerm.trim())}`);
    }
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light shadow-sm px-4">
      <Link className="navbar-brand" to="/">
        StayFinder
      </Link>

      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div
        className="collapse navbar-collapse justify-content-between"
        id="navbarNav"
      >
        {/* LEFT NAV LINKS */}
        <ul className="navbar-nav">
          {/* <li className="nav-item">
            <Link className="nav-link" to="/">
              Home
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/find-pg">
              Find PG
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/my-bookings">
              My Bookings
            </Link>
          </li> */}
        </ul>
        
        {/* SEARCH BAR IN CENTER */}
        {role != "OWNER" ? <div className="d-flex mx-auto search-container">
          <input
            type="text"
            className="form-control rounded-start-pill"
            placeholder="Search..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            style={{ borderRight: "none" }}
          />
          <button
            className="btn btn-primary rounded-end-pill"
            onClick={handleSearch}
          >
            Search
          </button>
        </div> : null}
        {role === "OWNER" && (
          <Link className="btn btn-primary" style={{alignSelf: "flex-end"}} to="/add-pg">
            Add PG
          </Link>
        )}

        {/* RIGHT USER ICON DROPDOWN */}
        <div className="dropdown ms-3">
          <i
            className="fa-solid fa-circle-user fa-2x"
            onClick={() => setDropdownOpen(!dropdownOpen)}
            style={{ cursor: "pointer" }}
          ></i>
          {dropdownOpen && (
            <div className="dropdown-menu dropdown-menu-end show mt-2">
              <button
                className="dropdown-item"
                onClick={() => navigate("/user-profile")}
              >
                Profile
              </button>
              <button className="dropdown-item" onClick={handleLogout}>
                Logout
              </button>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
