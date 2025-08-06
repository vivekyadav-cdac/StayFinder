import './PGCard.css';
import { MapPin, Star } from 'lucide-react';

const PGCard = ({ data, onCardClick }) => {
  const {
    name,
    vicinity,
    photos,
    rating,
    price = 5113,
    gender = "MEN'S",
    discount = 5,
    distance = 4.47,
  } = data;

  const photo = photos?.[0]?.photo_reference
    ? `https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=${photos[0].photo_reference}&key=${process.env.REACT_APP_GOOGLE_API_KEY}`
    : "https://via.placeholder.com/400x250";

  return (
    <div className="pg-card" onClick={() => onCardClick()}>
      <div className="pg-image-container">
        <img src={photo} alt={name || "PG Image"} className="pg-image" />
        <button className="pg-fav-btn">♡</button>
      </div>

      <div className="pg-body">
        {/* PG Name */}
        {name && <h3 className="pg-title">{name}</h3>}

        {/* Vicinity and Gender */}
        {(vicinity || gender) && (
          <p className="pg-subtitle">
            {gender && <span className="pg-gender">{gender}</span>}
            {vicinity && <> PG in {vicinity}</>}
          </p>
        )}

        {/* Rating */}
        {rating && (
          <div className="pg-rating">
            <Star size={16} color="#ffc107" />
            <span>{rating}</span>
          </div>
        )}

        {/* Price & Discount
        <div className="pg-price-section">
          <span className="pg-price">₹ {price.toLocaleString()}</span>
          <span className="pg-discount">UPTO {discount}% OFF</span>
        </div> */}

        {/* Distance */}
        {/* {distance && (
          <div className="pg-distance">
            <MapPin size={16} />
            <span>{distance} km from your location</span>
          </div>
        )} */}
      </div>
    </div>
  );
};

export default PGCard;
