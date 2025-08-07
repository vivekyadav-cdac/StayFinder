import "bootstrap/dist/css/bootstrap.min.css";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../features/auth/authSlice";

const Login = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { isAuthenticated, role, error } = useSelector((state) => state.auth);

  const [email, setEmail] = useState("user1@gmail.com");
  const [password, setPassword] = useState("123456");
  const [errors, setErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    if (isAuthenticated) {
      if (role === "admin") navigate("/admin");
      else if (role === "OWNER") navigate("/pg-owner");
      else if (role === "TENANT") navigate("/tenant");
    }
  }, [isAuthenticated, role, navigate]);

  const validate = () => {
    const newErrors = {};
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!email) newErrors.email = "Email is required.";
    else if (!emailRegex.test(email)) newErrors.email = "Invalid email format.";
    if (!password) newErrors.password = "Password is required.";
    else if (password.length < 6)
      newErrors.password = "Password must be at least 6 characters.";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    setIsLoading(true);
    try {
      const resultAction = await dispatch(loginUser({ email, password }));
      console.log("Login result:", resultAction);

      if (!loginUser.fulfilled.match(resultAction)) {
        alert("Login failed. Please check your credentials.");
      }
    } catch (error) {
      alert("Login failed. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="container-fluid vh-100 d-flex align-items-center justify-content-center">
      <div className="row w-100" style={{ maxWidth: "1000px" }}>
        <div className="col-md-6 d-flex align-items-center justify-content-center">
          <div
            className="card shadow p-4 w-100"
            style={{ maxWidth: "400px", borderRadius: "16px" }}
          >
            <h3 className="mb-4 text-center">Login</h3>
            <form onSubmit={handleLogin}>
              <div className="mb-3">
                <input
                  type="email"
                  className={`form-control ${errors.email ? "is-invalid" : ""}`}
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  placeholder="Email"
                />
                {errors.email && (
                  <div className="invalid-feedback">{errors.email}</div>
                )}
              </div>
              <div className="mb-3">
                <input
                  type="password"
                  className={`form-control ${
                    errors.password ? "is-invalid" : ""
                  }`}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  placeholder="Password"
                />
                {errors.password && (
                  <div className="invalid-feedback">{errors.password}</div>
                )}
              </div>
              <button
                type="submit"
                className="btn btn-primary w-100 mb-3"
                disabled={isLoading}
              >
                {isLoading ? "Logging in..." : "Login"}
              </button>
            </form>

            <div className="text-center my-2">OR</div>
            <div className="mb-3">
              <button className="btn btn-outline-danger w-100">
                <i className="fa-brands fa-google me-2"></i>
                Continue with Google
              </button>
            </div>

            <div className="text-center">
              <span>Don't have an account?</span>
              <button
                className="btn btn-link"
                onClick={() => navigate("/register")}
              >
                Register
              </button>
            </div>
          </div>
        </div>

        {/* Right Image */}
        <div className="col-md-6 d-none d-md-flex align-items-center justify-content-center">
          <div
            className="position-relative"
            style={{
              height: "620px",
              width: "100%",
              maxWidth: "500px",
              borderRadius: "16px",
            }}
          >
            <img
              src="https://visor.gumlet.io//public/assets/home/desktop/hero-img.png?compress=true&format=auto&quality=75&dpr=auto&h=480&w=522&ar=unset"
              alt="Stay Finder"
              className="position-absolute top-50 start-50 translate-middle"
              style={{
                height: "70%",
                width: "100%",
                objectFit: "fill",
                backgroundColor: "transparent",
              }}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
