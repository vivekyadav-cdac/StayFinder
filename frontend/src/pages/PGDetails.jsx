// src/components/PGDetails.js
import React from "react";
import { useLocation } from "react-router-dom";
import './PGDetails.css';

const PGDetails = () => {
  const location = useLocation();
  const pgData = location.state?.pg; // Get PG data passed from FindPG

  if (!pgData) {
    return <div>Loading...</div>;
  }

  const {
    name,
    vicinity,
    rating,
    user_ratings_total,
    photos,
    opening_hours,
    geometry,
    place_id,
  } = pgData;

  const photo = photos?.[0]?.photo_reference
    ? `https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference=${photos[0].photo_reference}&key=${process.env.REACT_APP_GOOGLE_API_KEY}`
    : "https://via.placeholder.com/400x250";

  return (
    <div className="pg-details-container">
      <div className="pg-header">
        <img src={photo} alt="PG" className="pg-details-image" />
        <div className="pg-header-info">
          <h1>{name}</h1>
          <p>{vicinity}</p>
          <div className="rating">
            <span>{rating}⭐</span>
            <span>({user_ratings_total} reviews)</span>
          </div>
          <div className="status">
            <span>Status: {opening_hours?.open_now ? "Open" : "Closed"}</span>
          </div>
        </div>
      </div>

      <div className="pg-location">
        <h3>Location</h3>
        <div>{vicinity}</div>
        <iframe
          width="100%"
          height="400px"
          src={`https://www.google.com/maps/embed/v1/place?key=AIzaSyCK73bl2nIbFiv_dIm50g810LNopbUnE5o&q=${geometry.location.lat},${geometry.location.lng}`}
          allowFullScreen
        ></iframe>
      </div>
    </div>
  );
};

export default PGDetails;
