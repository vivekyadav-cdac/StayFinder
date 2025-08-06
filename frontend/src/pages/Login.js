import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () =>{
  const navigate = useNavigate();

    return(
       <div className="container-fluid vh-100 d-flex align-items-center justify-content-center">
      <div className="row w-100" style={{ maxWidth: '1000px' }}>

        {/* Left - Login Box */}
        <div className="col-md-6 d-flex align-items-center justify-content-center">
          <div className="card shadow p-4 w-100" style={{ maxWidth: '400px', borderRadius: '16px' }}>
            <h3 className="mb-4 text-center">Login</h3>

            <form>
              <div className="mb-3">
                <input type="email" className="form-control" placeholder="Email" required />
              </div>
              <div className="mb-3">
                <input type="password" className="form-control" placeholder="Password" required />
              </div>
              <button  onClick={()=>{}} type="submit" className="btn btn-primary w-100 mb-3">Login</button>
            </form>

            <div className="text-center my-2">OR</div>

            {/* Google Button */}
            <div className="mb-3">
              <button className="btn btn-outline-danger w-100">
                <img
                  src="https://developers.google.com/identity/images/g-logo.png"
                  alt="Google"
                  style={{ width: '20px', marginRight: '10px' }}
                />
                Continue with Google
              </button>
            </div>

            {/* Register Link */}
            <div className="text-center">
              <span>Don't have an account?</span>
              <button
                className="btn btn-link"
                onClick={() => navigate('/register')}
              >
                Register
              </button>
            </div>
          </div>
        </div>

        {/* Right - Image */}
        <div className="col-md-6 d-none d-md-flex align-items-center justify-content-center">
  <div
    className="rounded overflow-hidden shadow-sm position-relative"
    style={{
      height: '620px',
      width: '100%',
      maxWidth: '500px',
      borderRadius: '16px'
    }}
  >
    <img
      src="https://visor.gumlet.io//public/assets/home/desktop/hero-img.png?compress=true&format=auto&quality=75&dpr=auto&h=480&w=522&ar=unset"
      alt="Zolo Visual"
      className="position-absolute top-50 start-50 translate-middle"
      style={{
        height: '100%',
        width: '100%',
        objectFit: 'cover',
      }}
    />
  </div>
</div>

      </div>
    </div>
    )
}

export default Login;