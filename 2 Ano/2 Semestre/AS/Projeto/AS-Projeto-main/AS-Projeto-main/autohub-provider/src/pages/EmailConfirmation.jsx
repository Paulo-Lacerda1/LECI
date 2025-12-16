import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/EmailConfirmation.css';
import logo from '../assets/logo_transparente.png';

const EmailConfirmation = () => {
  const navigate = useNavigate();

  return (
  <div className="confirmation-container">
    <img src={logo} alt="AutoHub Logo" className="logo-static" />

    <div className="confirmation-wrapper">
      <p>We've sent a verification link to your email address.</p>
      <p>Click the link to confirm your email and continue using AutoHub.</p>
      <div className="button-group">
        <button onClick={() => alert("The email was sent successfully, please check your email inbox!")}>Resend email</button>
        <button onClick={() => navigate('/')}>Sign in</button>
      </div>
    </div>
  </div>
);


}

export default EmailConfirmation;
