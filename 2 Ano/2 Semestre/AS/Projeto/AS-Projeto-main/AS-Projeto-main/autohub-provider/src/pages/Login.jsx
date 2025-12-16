import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import '../styles/Login.css';
import logo from '../assets/logo_transparente.png';
import { FaEye, FaEyeSlash } from 'react-icons/fa'; // Ícones

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('/data/companies.json');
      const companies = await response.json();

      const company = companies.find(
        (c) => c.email === email && c.password === password
      );

      if (company) {
        setError('');
        navigate(`/dashboard/${company.param}`);
      } else {
        setError('Invalid email or password');
      }
    } catch (err) {
      console.error(err);
      setError('Failed to login. Try again later.');
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <img src={logo} alt="AutoHub Logo" className="login-logo" />

        {error && (
          <div className="error-box">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input
              id="email"
              type="email"
              placeholder="Value"
              value={email}
              onChange={e => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Password</label>
            <div className="password-wrapper">
              <input
                id="password"
                type={showPassword ? 'text' : 'password'}
                placeholder="Value"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
              <span
                onClick={() => setShowPassword(!showPassword)}
                className="toggle-password"
              >
                {showPassword ? (
                  <>
                    <FaEyeSlash style={{ marginRight: '5px' }} />
                    Hide
                  </>
                ) : (
                  <>
                    <FaEye style={{ marginRight: '5px' }} />
                    Show
                  </>
                )}
              </span>
            </div>
          </div>

          <button type="submit">Sign in</button>
        </form>

        <div className="links-container">
          <Link to="/">Forgot password?</Link>
          <Link to="/provider-signup">Sign up</Link>
        </div>
      </div>
    </div>
  );
}
