import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Avaliacao from './pages/Avaliacao';
import Dashboard from './pages/Dashboard';
import ServiceManagement from './pages/ServiceManagement';
import ProviderRegistration from './pages/ProviderRegistration'; 
import EmailConfirmation from './pages/EmailConfirmation';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/avaliacao" element={<Avaliacao />} />
        <Route path="/dashboard/:company" element={<Dashboard />} />
        <Route path="/service_management/:company" element={<ServiceManagement />} />
        <Route path="/provider-signup" element={<ProviderRegistration />} /> 
        <Route path="/email-confirmation" element={<EmailConfirmation />} />
      </Routes>
    </Router>
  );
}

export default App;
