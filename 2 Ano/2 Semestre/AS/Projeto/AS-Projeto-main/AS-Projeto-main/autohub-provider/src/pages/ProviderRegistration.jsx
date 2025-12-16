import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { IoArrowBack } from 'react-icons/io5';
import '../styles/ProviderRegistration.css';
import logo from '../assets/logo_transparente.png';

const ProviderRegistration = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    businessName: '',
    email: '',
    password: '',
    confirmPassword: '',
    nif: '',
    phone: '',
    address: '',
    role: 'Service Provider',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
  e.preventDefault();
  console.log('Dados registados:', formData);

  // Redireciona para confirmação de email
  navigate('/email-confirmation');
};

  return (
    <React.Fragment>
      <div className="provider-container">
        <img src={logo} alt="AutoHub Logo" className="logo" />

        <div className="header">
          <div className="back-button" onClick={() => navigate('/')}>
            <IoArrowBack size={24} />
          </div>
          <h1>Criar conta</h1>
        </div>

        <form onSubmit={handleSubmit}>
          <p className="description">
            Registe a sua oficina ou centro de serviços para oferecer serviços na plataforma AutoHub.
          </p>
          <select name="role" value={formData.role} onChange={handleChange}>
            <option value="Service Provider">Service Provider</option>
          </select>

          <label>Business Name</label>
          <input
            name="businessName"
            value={formData.businessName}
            onChange={handleChange}
            required
          />

          <label>Email</label>
          <input
            name="email"
            type="email"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <label>Password</label>
          <input
            name="password"
            type="password"
            value={formData.password}
            onChange={handleChange}
            required
          />

          <label>Confirm password</label>
          <input
            name="confirmPassword"
            type="password"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />

          <label>NIF</label>
          <input
            name="nif"
            value={formData.nif}
            onChange={handleChange}
          />

          <label>Phone Number</label>
          <input
            name="phone"
            value={formData.phone}
            onChange={handleChange}
          />

          <label>Address</label>
          <input
            name="address"
            value={formData.address}
            onChange={handleChange}
          />

          <button type="submit">Register</button>
        </form>
      </div>
    </React.Fragment>
  );
};

export default ProviderRegistration;
