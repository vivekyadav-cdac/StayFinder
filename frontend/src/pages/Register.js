import React from 'react';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const navigate = useNavigate();
  return (
    <div className="container-fluid vh-100">
      <div className="row h-100">

        {/* Left - Register Form */}
        <div className="col-md-6 d-flex align-items-center justify-content-center bg-light">
          <div className="w-100 px-5" style={{ maxWidth: '400px' }}>
            <h2 className="mb-4 text-center">Create Your Account</h2>

            <form>
              <div className="mb-3">
                <input type="text" className="form-control" placeholder="Full Name" required />
              </div>
              <div className="mb-3">
                <input type="email" className="form-control" placeholder="Email" required />
              </div>
              <div className="mb-3">
                <input type="tel" className="form-control" placeholder="Phone Number" required />
              </div>
              <div className="mb-3">
                <input type="password" className="form-control" placeholder="Password" required />
              </div>
              <div className="mb-3">
                <input type="password" className="form-control" placeholder="Confirm Password" required />
              </div>
              <button
                onClick={() => {
                  navigate('/findpg');
                }}
              type="submit" className="btn btn-primary w-100 mb-3">Register</button>
            </form>

            <div className="text-center my-2">OR</div>

            {/* Google Signup Button */}
            <div className="d-flex justify-content-center">
              <button className="btn btn-outline-danger w-100">
                <img
                  src="https://developers.google.com/identity/images/g-logo.png"
                  alt="Google"
                  style={{ width: '20px', marginRight: '10px' }}
                />
                Sign up with Google
              </button>
            </div>
          </div>
        </div>

        {/* Right - Image Section */}
        <div className="col-md-6 d-none d-md-block p-0">
          <img
            src="https://zolostays.com/assets/images/login-banner.jpg"
            alt="Zolo Visual"
            className="img-fluid vh-100 w-100"
            style={{ objectFit: 'cover' }}
          />
        </div>

      </div>
    </div>
  );
};

export default Register;
