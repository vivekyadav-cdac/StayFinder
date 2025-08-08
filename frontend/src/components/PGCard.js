import "./PGCard.css";
import { MapPin, Phone } from "lucide-react";
import SecureImage from "./SecureImage"; // Update path if needed
import { useNavigate } from "react-router-dom";

const PGCard = ({ data: pg, onCardClick }) => {
  const navigate = useNavigate();
  const { name, type, address, city, state, pin, contact, imageUrl } = pg;

  const location = `${address}, ${city}, ${state} - ${pin}`;
  const filename = imageUrl?.split("/").pop();
  const role = localStorage.getItem("role");

  return (
    <div className="pg-card my-3" onClick={() =>  navigate("/pgdetails", { state: { pg } })}>
      <div className="pg-image-container md:w-1/3 h-48 md:h-auto overflow-hidden">
        {filename && (
          <SecureImage
            filename={filename}
            alt={name}
            width="100%"
            height="100%"
          />
        )}
      </div>

      <div className="pg-body">
        {name && <h3 className="pg-title">{name}</h3>}

        {type && <p className="pg-subtitle pg-gender">{type} PG</p>}

        {location && (
          <div className="pg-distance">
            <MapPin size={16} />
            <span>{location}</span>
          </div>
        )}

        {contact && (
          <div className="pg-distance">
            <Phone size={16} />
            <span>{contact}</span>
          </div>
        )}
      </div>
    </div>
  );
};

export default PGCard;
