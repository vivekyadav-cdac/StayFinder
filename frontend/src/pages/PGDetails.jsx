// src/pages/PGDetails.jsx
import { useState } from "react";

const PGDetails = () => {
  const [selectedSharing, setSelectedSharing] = useState("two");

  const handleSharingChange = (type) => {
    setSelectedSharing(type);
  };

  const roomTypes = [
    { name: "Room Type 1", price: 11400 },
    { name: "Room Type 2", price: 11950 },
    { name: "Room Type 3", price: 16050 },
  ];

  return (
    <div className="max-w-7xl mx-auto p-6">
      {/* Header section */}
      <div className="flex items-start justify-between flex-wrap mb-4">
        <div>
          <h1 className="text-2xl font-bold text-gray-800">Zolo Kings Landing</h1>
          <p className="text-gray-600">
            Survey No. 17, Housai Complex, Next to Rajwada Hotel, Someshwarwadi Road,
            Baner, Pune, Maharashtra 411008
          </p>
        </div>
        <button className="border border-blue-500 text-blue-500 px-4 py-1 rounded hover:bg-blue-50">
          📍 View in map
        </button>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="rounded-lg overflow-hidden shadow">
          <img
            src="https://via.placeholder.com/600x400"
            alt="PG Room"
            className="w-full h-auto object-cover"
          />
        </div>

        <div className="bg-white p-6 rounded-xl shadow space-y-4">
          <h2 className="text-lg font-semibold">Select Sharing Type</h2>
          <div className="flex gap-4">
            <button
              onClick={() => handleSharingChange("two")}
              className={`px-4 py-2 rounded border ${
                selectedSharing === "two"
                  ? "bg-blue-100 border-blue-500 text-blue-700"
                  : "bg-gray-100 text-gray-700"
              }`}
            >
              Two Sharing
            </button>
            <button
              onClick={() => handleSharingChange("three")}
              className={`px-4 py-2 rounded border ${
                selectedSharing === "three"
                  ? "bg-blue-100 border-blue-500 text-blue-700"
                  : "bg-gray-100 text-gray-700"
              }`}
            >
              Three Sharing
            </button>
          </div>

          <h3 className="text-md font-medium">Select Room Type</h3>
          <p className="text-sm text-gray-500 mb-2">You can change your room type before onboarding.</p>

          <div className="space-y-2">
            {roomTypes.map((room, i) => (
              <label
                key={i}
                className="flex justify-between items-center border p-3 rounded-lg cursor-pointer"
              >
                <div className="flex items-center gap-2">
                  <input type="checkbox" />
                  <span>{room.name}</span>
                </div>
                <span>₹{room.price}/month</span>
              </label>
            ))}
          </div>

          <div className="flex gap-4 mt-4">
            <button className="px-4 py-2 border rounded text-blue-600 border-blue-600 hover:bg-blue-50">
              SCHEDULE A VISIT
            </button>
            <button className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700">
              CONFIRM DETAILS
            </button>
          </div>

          <div className="bg-blue-50 p-3 mt-4 rounded text-sm text-center">
            To book for a period less than 30 days, Contact{" "}
            <a href="tel:8884518010" className="text-blue-600 underline">
              8884518010
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PGDetails;
