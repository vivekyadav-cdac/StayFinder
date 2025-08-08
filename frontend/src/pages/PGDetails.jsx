import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import SecureImage from "../components/SecureImage";
import AddRoomModal from "../components/AddRoomModal";

const PGDetails = () => {
  const { state } = useLocation();
  const [pg, setPg] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const role = localStorage.getItem("role");

  useEffect(() => {
    setPg(state?.pg);
  }, [state?.pg]);

  if (!pg)
    return <div className="text-center mt-10">PG details not found.</div>;

  return (
    <div className="max-w-7xl mx-auto" style={{ padding: 10 }}>
      <nav className="navbar navbar-expand-lg navbar-light bg-white shadow-sm px-4">
        <Link className="navbar-brand font-bold text-xl" to="/">
          StayFinder
        </Link>
        <div className="ms-auto"></div>
      </nav>

      {/* PG main info */}
      <div style={{ display: "flex", flexDirection: "row" }}>
        <div className=" mb-6" style={{ width: "50%" }}>
          {pg.imageUrl && (
            <SecureImage
              filename={pg.imageUrl.split("/").pop()}
              alt={pg.name}
              className="object-cover w-full h-full"
              height="400px"
              width="400px"
            />
          )}
        </div>
        <div style={{ width: "50%", marginTop: 20 }}>
          <h1 className="text-3xl font-bold">{pg.name}</h1>
          <p className="text-gray-700">
            {pg.address}, {pg.city}, {pg.state} - {pg.pin}
          </p>
          <div className="text-sm text-gray-600 space-y-2 mt-4">
            <div>
              <span className="font-medium">Type:</span> {pg.type}
            </div>
            <div>
              <span className="font-medium">Contact:</span> {pg.contact}
            </div>
            <div>
              <span className="font-medium">Owner ID:</span> {pg.ownerId}
            </div>
            <div>
              <span className="font-medium">Created:</span>{" "}
              {new Date(pg.createdAt).toLocaleString()}
            </div>
          </div>
        </div>
      </div>

      {/* Map + Rooms */}
      <div
        style={{
          display: "flex",
          flexDirection: "row",
          marginTop: 30,
          marginBottom: 20,
        }}
      >
        <div style={{ height: 300, width: "50%" }}>
          <iframe
            title="map"
            className="w-full h-full rounded-lg"
            loading="lazy"
            allowFullScreen
            src={`https://maps.google.com/maps?q=${pg.latitude},${pg.longitude}&z=15&output=embed`}
          ></iframe>
        </div>

        <div className="text-center" style={{ width: "50%" }}>
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-2xl font-bold">Rooms</h2>
            {role === "OWNER" && (
              <button
                className="btn btn-primary"
                onClick={() => setShowModal(true)}
              >
                + Add Room
              </button>
            )}
          </div>

          {pg.rooms?.length > 0 ? (
            <div className="pg-grid">
              {pg.rooms.map((room) => (
                <div key={room.id} className="pg-card" style={{ margin: 2 }}>
                  <div className="flex justify-between items-center mb-2">
                    <h3 className="text-lg font-semibold">
                      Room #{room.number}
                    </h3>
                    <span
                      className={`px-2 py-1 text-xs font-medium rounded-full ${
                        room.available
                          ? "bg-green-100 text-green-700"
                          : "bg-red-100 text-red-700"
                      }`}
                    >
                      {room.available ? "Available" : "Not Available"}
                    </span>
                  </div>
                  <p className="text-gray-600 mb-1">Type: {room.type}</p>
                  <p className="text-gray-600">Rent: ₹{room.rent}</p>

                  {role === "TENANT" && (
                    <button
                      className="btn btn-success me-2 mb-2"
                      onClick={() => {}}
                    >
                      Book Now
                    </button>
                  )}
                </div>
              ))}
            </div>
          ) : (
            <p className="text-gray-500">No rooms available.</p>
          )}
        </div>
      </div>

      {/* Modal */}
      {showModal && (
        <AddRoomModal
          pgId={pg.id}
          onClose={() => setShowModal(false)}
          onRoomAdded={(newRoom) =>
            setPg((prev) => ({
              ...prev,
              rooms: [...(prev.rooms || []), newRoom],
            }))
          }
        />
      )}
    </div>
  );
};

export default PGDetails;
