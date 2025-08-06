import React, { useState, useEffect } from "react";
import "./FindPG.css";
import "bootstrap/dist/css/bootstrap.min.css";
import PGCard from "../components/PGCard";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import { useDispatch, useSelector } from "react-redux";
import { pgDataList } from "./PGList";

const FindPG = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  // const [pgs, setPgs] = useState([]);
  const [pgs, setPgs] = useState(pgDataList);
  const {pgList, nextPageToken, loading, error} = useSelector((state) => state.pgList);

  const [filters, setFilters] = useState({
    gender: "",
    roomType: "",
    maxBudget: "",
  });

  // useEffect(()=>{
  //   console.log("PG List Updated:", pgList);
    
  //   setPgs(pgList);
  // },[pgList])

  useEffect(() => {}, [filters]);


  useEffect(() => {
    if('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        const { latitude, longitude } = position.coords;
        localStorage.setItem("latitude", latitude);
        localStorage.setItem("longitude", longitude);
        console.log("Current Location:", latitude, longitude);
      }, (error) => {
        console.error("Error getting location:", error);
      });
    }
  },[])

  useEffect(()=>{
    // dispatch(clearPGs());
    // dispatch(fetchPGs({lat: localStorage.getItem("latitude"), lng: localStorage.getItem("longitude")}));
  },[dispatch])

  const handleFilterChange = (e) => {
    setFilters({
      ...filters,
      [e.target.name]: e.target.value,
    });
  };

  return (
    <div style={styles.container}>
      <Navbar />
      <div style={styles.body}>
        {/* Filter Section */}
        <div style={styles.filterSection}>
          <h3>Filter Section</h3>
        </div>

        {/* PG View Section */}
        <div style={styles.pgViewSection}>
          <div className="flex-1">
            <div className="pg-grid">
              {pgs.map((pg) => (
                <PGCard
                  key={pg.place_id}
                  data={pg}
                  onCardClick={() => {
                    navigate("/pgdetails",{ state: { pg } });
                  }}
                />
              ))}
            </div>
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
  navbar: {
    height: "50px",
    backgroundColor: "#3b82f6",
    display: "flex",
    alignItems: "center",
    justifyContent: "flex-end",
    padding: "0 16px",
    color: "#fff",
  },
  userIcon: {
    cursor: "pointer",
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
