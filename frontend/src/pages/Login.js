import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();

  return (
    <div className="container-fluid vh-100 d-flex align-items-center justify-content-center">
      <div className="row w-100" style={{ maxWidth: "1000px" }}>
        {/* Left - Login Box */}
        <div className="col-md-6 d-flex align-items-center justify-content-center">
          <div
            className="card shadow p-4 w-100"
            style={{ maxWidth: "400px", borderRadius: "16px" }}
          >
            <h3 className="mb-4 text-center">Login</h3>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
