import './PGCard.css';
import { MapPin } from 'lucide-react';

const PGCard = ({ data , onCardClick  }) => {
  const { name, vicinity, photos, price = 5113, gender = "MEN'S", discount = 5, distance = 4.47 } = data;
  const photo = photos?.[0]?.photo_reference
    ? `https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=${photos[0].photo_reference}&key=${process.env.REACT_APP_GOOGLE_API_KEY}`
    : "https://via.placeholder.com/400x250";

  return (
    <div className="pg-card" onClick={() => onCardClick()}>
      <div className="pg-image-container">
        <img src={photo} alt={name} className="pg-image" />
        <button className="pg-fav-btn">♡</button>
      </div>

      <div className="pg-body">
        <h3 className="pg-title">{name}</h3>
        <p className="pg-subtitle">
          <span className="pg-gender">{gender}</span> PG in {vicinity}
        </p>

        <div className="pg-price-section">
          <span className="pg-price">₹ {price.toLocaleString()}</span>
          <span className="pg-discount">UPTO {discount}% OFF</span>
        </div>

        <div className="pg-distance">
          <MapPin size={16} />
          <span>{distance} km from your location</span>
        </div>
      </div>
    </div>
  );
};

export default PGCard;
