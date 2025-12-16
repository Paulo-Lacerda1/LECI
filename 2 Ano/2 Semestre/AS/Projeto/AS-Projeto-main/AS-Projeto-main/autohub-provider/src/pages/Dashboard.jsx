import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { FaStar, FaStarHalfAlt, FaRegStar } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import '../styles/Dashboard.css';

export default function Dashboard() {
    const { company } = useParams();
    const [appointments, setAppointments] = useState([]);
    const [services, setServices] = useState([]);
    const [stats, setStats] = useState({ total: 0, earnings: 0, rating: 0 });

    const [modalOpen, setModalOpen] = useState(false);
    const [serviceEditing, setServiceEditing] = useState(null);
    const [editService, setEditService] = useState(null);
    const [companyName, setCompanyName] = useState('');

    useEffect(() => {
        fetch(`/data/${company}/appointments.json`).then(res => res.json()).then(setAppointments);
        fetch(`/data/${company}/services.json`).then(res => res.json()).then(setServices);
        fetch(`/data/${company}/stats.json`).then(res => res.json()).then(setStats);
        fetch('/data/companies.json').then(res => res.json()).then(companies => {
            const found = companies.find(c => c.param === company);
            if (found) setCompanyName(found.name);
        })
    }, [company]);

    function renderStars(value) {
        const full = Math.floor(value);
        const half = value % 1 >= 0.25 && value % 1 <= 0.75;
        const empty = 5 - full - (half ? 1 : 0);

        const stars = [];

        for (let i = 0; i < full; i++) {
            stars.push(<FaStar key={`full-${i}`} />);
        }

        if (half) {
            stars.push(<FaStarHalfAlt key="half" />);
        }

        for (let i = 0; i < empty; i++) {
            stars.push(<FaRegStar key={`empty-${i}`} />);
        }

        return stars;
    }

    function openModal(service) {
        setServiceEditing(service);
        setEditService(service);
        setModalOpen(true);
    }

    function handleChange(e) {
        const { name, value } = e.target;
        setEditService(prev => ({ ...prev, [name]: value }));
    }

    function saveChanges() {
        if (serviceEditing) {
            setServices(prevServices =>
                prevServices.map(s =>
                    s.title === serviceEditing.title ? editService : s
                )
            );
        } else {
            setServices(prev => [...prev, editService]);
        }

        closeModal();
    }

    function deleteService() {
        if (serviceEditing) {
            setServices(prev => prev.filter(s => s.title !== serviceEditing.title));
            closeModal();
        }
    }

    function closeModal() {
        setModalOpen(false);
        setServiceEditing(null);
    }

    return (
        <div>
            <div className="top-bar">
                <div className="logo-area">
                    <Link to="/">
                        <img 
                            src="/src/assets/logo_transparente.png" 
                            alt="AutoHub Logo" 
                            className="autohub-logo" />
                    </Link>
                </div>
                <div className="company-info">
                    <div className="company-text">
                        <span className="company-name">{companyName}</span>
                        <div className="stars">{renderStars(stats.stars)}</div>
                    </div>
                    <Link to={`/dashboard/${company}`}>
                        <img
                            src={`/data/${company}/logo.png`}
                            alt={`${company} Logo`}
                            className="company-logo"
                        />
                    </Link>
                </div>
            </div>

            <div className="dashboard-container">
                <section className="appointments-section">
                    <header className="dashboard-header">
                        <h1>Upcoming Appointments</h1>
                        <Link to={`/service_management/${company}`}><button className='see-all-link' style={{ backgroundColor: 'white', border: '0px', cursor: 'pointer' }}>See all</button></Link>
                    </header>

                    {appointments.map((a, i) => (
                    <div key={i} className="appointment-card">
                        <h2>{a.date} · {a.time} - {a.title}</h2>
                        <ul>
                            <li>Client: {a.client}</li>
                            <li>Vehicle Type: {a.vehicle}</li>
                            <li>Extras: {a.extras}</li>
                        </ul>
                        <Link to={`/service_management/${company}`}><button>See more</button></Link>
                    </div>
                    ))}
                </section>

                <section className="stats-section">
                    <h2>Summary Statistics</h2>
                    <div className="stats-grid">
                        <div className="stat-box">
                            <p><span className='material-icons-round'>build</span></p>
                            <p>Total services this month</p>
                            <p className="stat-value">{stats.total}</p>
                        </div>
                        <div className="stat-box">
                            <p><span className='material-icons-round'>attach_money</span></p>
                            <p>Earnings</p>
                            <p className="stat-value">€{stats.earnings}</p>
                        </div>
                        <Link to={`/avaliacao`} className="stat-box-star">
                                <p><FaRegStar></FaRegStar></p>
                                <p>Average Rating</p>
                                <p className="stat-value">{stats.rating}</p>
                        </Link>
                    </div>
                </section>

                <section className="services-section">
                    <h2>Your Services</h2>
                    <button className='add-service-btn' onClick={() => {
                        setEditService({ title: '', price: '', duration: '', details: '' });
                        setServiceEditing(null);
                        setModalOpen(true);
                    }}>
                        Add Service
                    </button>
                    <div className="services-grid">
                    {services.map((s, i) => (
                        <div key={i} className="service-card">
                            <button className='service-icon' onClick={() => openModal(s)}>
                                <span className='material-icons-round'>open_in_new</span>
                            </button>
                            <div>
                                <h3>{s.title}</h3>
                                <ul>
                                    <li>{s.price}</li>
                                    <li>{s.duration}</li>
                                    <li>{s.details}</li>
                                </ul>
                            </div>
                        </div>
                    ))}
                    </div>
                </section>
            </div>

            {modalOpen && (
                <div className='modal-overlay' onClick={closeModal}>
                    <div className='modal-content' onClick={e => e.stopPropagation()}>
                        <h2>Edit Service</h2>
                        <label>
                            Title:
                            <input
                                type="text"
                                name="title"
                                value={editService.title}
                                onChange={handleChange}
                            />
                        </label>
                        <label>
                            Price:
                            <input
                                type="text"
                                name="price"
                                value={editService.price}
                                onChange={handleChange}
                            />
                        </label>
                        <label>
                            Duration:
                            <input
                                type="text"
                                name="duration"
                                value={editService.duration}
                                onChange={handleChange}
                            />
                        </label>
                        <label>
                            Details:
                            <input
                                type="text"
                                name="details"
                                value={editService.details}
                                onChange={handleChange}
                            />
                        </label>
                        <button onClick={saveChanges}>Save</button>
                        <button onClick={closeModal}>Close</button>
                        {serviceEditing && (
                            <button onClick={deleteService} style={{ backgroundColor: 'red', color: 'white' }}>
                                Delete
                            </button>
                        )}
                    </div>
                </div>
            )}
        </div>
    );
}