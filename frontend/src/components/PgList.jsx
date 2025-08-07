import React from "react";

const PGList = ({ pgs }) => {
  return (
    <div className="px-4 md:px-8 lg:px-16 py-6">
      <h2 className="text-2xl font-bold mb-6 text-center">All PG Properties</h2>
      <div className="space-y-6">
        {pgs.map((pg) => (
          <div
            key={pg.id}
            className="flex flex-col md:flex-row bg-white border rounded-lg shadow hover:shadow-md overflow-hidden"
          >
            {/* PG Image */}
            <div className="md:w-1/3 h-48 md:h-auto overflow-hidden">
              <img
                src={pg.imageUrl}
                alt={pg.name}
                className="w-full h-full object-cover"
              />
            </div>

            {/* PG Info */}
            <div className="flex-1 p-4">
              <h3 className="text-xl font-semibold">{pg.name}</h3>
              <p className="text-gray-700">
                {pg.address}, {pg.city}, {pg.state} - {pg.pin}
              </p>
              <p className="text-sm text-gray-500 mt-1">
                Contact: {pg.contact}
              </p>
              <p className="text-sm text-gray-500">Owner ID: {pg.ownerId}</p>

              {/* Room Summary */}
              {pg.rooms?.length > 0 && (
                <div className="mt-3">
                  <h4 className="font-semibold text-sm">Rooms:</h4>
                  <ul className="list-disc list-inside text-sm text-gray-600">
                    {pg.rooms.map((room) => (
                      <li key={room.id}>
                        {room.type} Room #{room.number} - ₹{room.rent}{" "}
                        {room.available ? "(Available)" : "(Not Available)"}
                      </li>
                    ))}
                  </ul>
                </div>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PGList;
