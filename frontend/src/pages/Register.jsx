import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../features/auth/authSlice";

const Register = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { isAuthenticated, role, error, user, loading } = useSelector(
    (state) => state.auth
  );

  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    password: "",
    confirmPassword: "",
    role: "OWNER",
  });

  useEffect(() => {
    if (!loading && isAuthenticated && role) {
      if (role === "admin") navigate("/admin");
      else if (role === "OWNER") navigate("/pg-owner");
      else if (role === "TENANT") navigate("/tenant");
    }
  }, [loading, isAuthenticated, role, navigate]);

  const [formErrors, setFormErrors] = useState({});

  const validate = () => {
    const errors = {};
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^[0-9]{10}$/;

    if (!formData.firstName.trim())
      errors.firstName = "First name is required.";
    if (!formData.lastName.trim()) errors.lastName = "Last name is required.";
    if (!formData.email) errors.email = "Email is required.";
    else if (!emailRegex.test(formData.email))
      errors.email = "Invalid email format.";
    if (!formData.phone) errors.phone = "Phone number is required.";
    else if (!phoneRegex.test(formData.phone))
      errors.phone = "Phone must be 10 digits.";
    if (!formData.password) errors.password = "Password is required.";
    else if (formData.password.length < 6)
      errors.password = "Password must be at least 6 characters.";
    if (!formData.confirmPassword)
      errors.confirmPassword = "Please confirm your password.";
    else if (formData.password !== formData.confirmPassword)
      errors.confirmPassword = "Passwords do not match.";

    setFormErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setFormErrors({ ...formErrors, [e.target.name]: "" });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    const result = await dispatch(registerUser(formData));
    if (registerUser.fulfilled.match(result)) {
      alert("Registration successful!");
      navigate("/TENANT");
    } else {
      alert(result.payload || "Registration failed.");
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
            <h3 className="mb-4 text-center">Register</h3>
            <form onSubmit={handleSubmit}>
              {[
                { name: "firstName", label: "First Name" },
                { name: "lastName", label: "Last Name" },
                { name: "email", label: "Email", type: "email" },
                { name: "phone", label: "Phone", type: "tel" },
                { name: "password", label: "Password", type: "password" },
                {
                  name: "confirmPassword",
                  label: "Confirm Password",
                  type: "password",
                },
              ].map(({ name, label, type = "text" }) => (
                <div className="mb-3" key={name}>
                  <input
                    type={type}
                    name={name}
                    className={`form-control ${
                      formErrors[name] ? "is-invalid" : ""
                    }`}
                    placeholder={label}
                    value={formData[name]}
                    onChange={handleChange}
                    required
                  />
                  {formErrors[name] && (
                    <div className="invalid-feedback">{formErrors[name]}</div>
                  )}
                </div>
              ))}

              <button
                type="submit"
                className="btn btn-primary w-100 mb-3"
                disabled={loading}
              >
                {loading ? "Registering..." : "Register"}
              </button>
            </form>

            {/* {error && <div className="alert alert-danger text-center">{error}</div>} */}

            <div className="text-center my-2">OR</div>
            <div className="mb-3">
              <button className="btn btn-outline-danger w-100">
                <i className="fa-brands fa-google me-2"></i>
                Continue with Google
              </button>
            </div>

            <div className="text-center">
              <span>Already have an account?</span>
              <button className="btn btn-link" onClick={() => navigate("/")}>
                Login
              </button>
            </div>
          </div>
        </div>

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
              alt="Zolo Visual"
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

export default Register;
