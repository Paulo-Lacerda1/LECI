import React, { useState, useEffect } from 'react';
import { BrowserRouter, useParams } from 'react-router-dom';
import { FaStar, FaStarHalfAlt, FaRegStar } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import '../styles/ServiceManagement.css';

function getDaysInMonth(year, month) {
    const date = new Date(year, month, 1);
    const days = [];
    while (date.getMonth() === month) {
        days.push(new Date(date));
        date.setDate(date.getDate() + 1);
    }
    return days;
}

function formatDate(d) {
    return `${d.getDate()}/${d.getMonth() + 1}/${d.getFullYear()}`;
}

export default function AppointmentCalendar() {
    const { company } = useParams();
    const [stats, setStats] = useState({ total: 0, earnings: 0, rating: 0 });
    const [services, setServices] = useState([]);
    const [appointments, setAppointments] = useState([]);

    const [modalOpen, setModalOpen] = useState(false);
    const [appointmentEditing, setAppointmentEditing] = useState(null);
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

    const today = new Date();

    // Estado único para mês e ano (primeiro dia do mês atual)
    const [currentDate, setCurrentDate] = useState(new Date(today.getFullYear(), today.getMonth(), 1));
    const [selectedDate, setSelectedDate] = useState(today);

    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth();

    const daysInMonth = getDaysInMonth(currentYear, currentMonth);

    const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
    const lastDayOfMonth = new Date(currentYear, currentMonth + 1, 0);

    const startDayOfWeek = firstDayOfMonth.getDay() === 0 ? 7 : firstDayOfMonth.getDay();
    const endDayOfWeek = lastDayOfMonth.getDay() === 0 ? 7 : lastDayOfMonth.getDay();

    // Dias do mês anterior e seguinte para preencher os "blanks"
    const prevMonthDate = new Date(currentYear, currentMonth - 1, 1);
    const nextMonthDate = new Date(currentYear, currentMonth + 1, 1);

    const prevMonthDays = getDaysInMonth(prevMonthDate.getFullYear(), prevMonthDate.getMonth());
    const nextMonthDays = getDaysInMonth(nextMonthDate.getFullYear(), nextMonthDate.getMonth());

    const blanksBeforeCount = startDayOfWeek - 1;
    const blanksAfterCount = 7 - endDayOfWeek;

    const blanksBefore = prevMonthDays.slice(prevMonthDays.length - blanksBeforeCount);
    const blanksAfter = nextMonthDays.slice(0, blanksAfterCount);

    const allDays = [...blanksBefore, ...daysInMonth, ...blanksAfter];

    const selectedDateStr = selectedDate.toISOString().slice(0, 10);
    const appointmentsForSelectedDay = appointments.filter(appt => appt.date === selectedDateStr);

    function prevMonth() {
        setCurrentDate(d => new Date(d.getFullYear(), d.getMonth() - 1, 1));
    }

    function nextMonth() {
        setCurrentDate(d => new Date(d.getFullYear(), d.getMonth() + 1, 1));
    }

    function selectDay(day) {
        setSelectedDate(day);
    }

    function renderStars(value) {
        const full = Math.floor(value);
        const half = value % 1 >= 0.25 && value % 1 <= 0.75;
        const empty = 5 - full - (half ? 1 : 0);

        const stars = [];

        for (let i = 0; i < full; i++) stars.push(<FaStar key={`full-${i}`} />);
        if (half) stars.push(<FaStarHalfAlt key="half" />);
        for (let i = 0; i < empty; i++) stars.push(<FaRegStar key={`empty-${i}`} />);

        return stars;
    }

    // Cores para serviços
    const predefinedColors = ['#bde4ff', '#ffd8b1', '#b1ffd8', '#ffb1b1'];
    const excludedColors = ['#cccccc', ...predefinedColors];

    const serviceColors = {};
    services.forEach((service, index) => {
        if (index < predefinedColors.length) {
            serviceColors[service.title] = predefinedColors[index];
        } else {
            let color;
            do {
                color = '#' + Math.floor(Math.random() * 16777215).toString(16);
            } while (excludedColors.includes(color));
            serviceColors[service.title] = color;
        }
    });

    function darkenColor(hex, amount = 0.2) {
        let col = hex.substring(1);
        let num = parseInt(col, 16);

        let r = (num >> 16) & 0xff;
        let g = (num >> 8) & 0xff;
        let b = num & 0xff;

        r = Math.max(0, Math.min(255, Math.floor(r * (1 - amount))));
        g = Math.max(0, Math.min(255, Math.floor(g * (1 - amount))));
        b = Math.max(0, Math.min(255, Math.floor(b * (1 - amount))));

        return `rgb(${r}, ${g}, ${b})`;
    }

    useEffect(() => {
        setAppointments(prev => prev.map((a, i) => ({ ...a, _index: i })));
    }, [appointments.length]);

    function openModalForAdd() {
        setAppointmentEditing({
            date: selectedDate.toISOString().slice(0, 10),
            time: '',
            title: services.length > 0 ? services[0].title : '',
            client: '',
            vehicle: '',
            extras: '',
        });
        setModalOpen(true);
    }

    function openModalForEdit(appointment) {
        setAppointmentEditing(appointment);
        setModalOpen(true);
    }

    function closeModal() {
        setModalOpen(false);
        setAppointmentEditing(null);
    }

    function handleChange(e) {
        const { name, value } = e.target;
        setAppointmentEditing(prev => ({ ...prev, [name]: value }));
    }

    function saveChanges() {
        if (!appointmentEditing) return;

        setAppointments(prev => {
            if (typeof appointmentEditing._index === 'number') {
                const newAppointments = [...prev];
                newAppointments[appointmentEditing._index] = { ...appointmentEditing };
                return newAppointments;
            }
            return [...prev, appointmentEditing];
        });

        closeModal();
    }

    function deleteAppointment() {
        if (!appointmentEditing || typeof appointmentEditing._index !== 'number') return;

        setAppointments(prev => {
            const newAppointments = [...prev];
            newAppointments.splice(appointmentEditing._index, 1);
            return newAppointments;
        });

        closeModal();
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
                            className="company-logo" />
                    </Link>
                </div>
            </div>

            <div className="service-container">
                <Link to={`/dashboard/${company}`}>
                    <button style={{ backgroundColor: 'white', border: '0px', cursor: 'pointer' }}>
                        <span className='material-icons-round' style={{ fontSize: '45px' }}>arrow_back</span>
                    </button>
                </Link>

                <div className="calendar-section">
                    <div className="appointments-header">
                        <h1>Appointments</h1>
                        <button className="add-appointment-btn" onClick={openModalForAdd}>
                            Add Appointment
                        </button>
                    </div>

                    <div className="calendar-nav">
                        <button className="material-icons-round" onClick={prevMonth}>arrow_back</button>
                        <div style={{ fontWeight: 'bold' }}>
                            {currentDate.toLocaleString('default', { month: 'long' })}, {currentDate.getFullYear()}
                        </div>
                        <button className="material-icons-round" onClick={nextMonth}>arrow_forward</button>
                    </div>

                    <div className="calendar-grid">
                        {['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'].map(day => (
                            <div key={day} style={{ fontWeight: 'bold', textAlign: 'center' }}>{day}</div>
                        ))}

                        {allDays.map(day => {
                            const isoDate = day.toISOString().slice(0, 10);
                            const isCurrentMonth = day.getMonth() === currentMonth;
                            const isSelected = isoDate === selectedDateStr;
                            const dayAppointments = appointments.filter(a => a.date === isoDate);

                            return (
                                <div
                                    key={isoDate}
                                    onClick={() => selectDay(day)}
                                    className={`calendar-day ${!isCurrentMonth ? 'calendar-blank' : ''} ${isSelected ? 'selected' : ''}`}
                                >
                                    <div className="day-number" style={{ fontWeight: isCurrentMonth ? 'bold' : 'normal' }}>
                                        {day.getDate()}
                                    </div>

                                    <div className="badges-container">
                                        {dayAppointments.length > 3 ? (
                                            <>
                                                {dayAppointments.slice(0, 2).map((a, i) => {
                                                    const bgColor = serviceColors[a.title] || '#bde4ff';
                                                    const borderColor = darkenColor(bgColor, 0.5);
                                                    const textColor = borderColor;
                                                    return (
                                                        <div
                                                            key={i}
                                                            className="appointment-badge"
                                                            style={{ backgroundColor: bgColor, border: `1px solid ${borderColor}`, color: textColor }}
                                                        >
                                                            {a.title}
                                                        </div>
                                                    );
                                                })}
                                                <div
                                                    className="appointment-badge"
                                                    style={{ backgroundColor: '#ccc', border: '1px solid #999', color: '#666' }}
                                                >
                                                    ...
                                                </div>
                                            </>
                                        ) : (
                                            dayAppointments.map((a, i) => {
                                                const bgColor = serviceColors[a.title] || '#bde4ff';
                                                const borderColor = darkenColor(bgColor, 0.5);
                                                const textColor = borderColor;
                                                return (
                                                    <div
                                                        key={i}
                                                        className="appointment-badge"
                                                        style={{ backgroundColor: bgColor, border: `1px solid ${borderColor}`, color: textColor }}
                                                    >
                                                        {a.title}
                                                    </div>
                                                );
                                            })
                                        )}
                                    </div>
                                </div>
                            );
                        })}
                    </div>
                </div>

                <div className='appointments-section'>
                    <h2>Appointments for {formatDate(selectedDate)}</h2>

                    {appointmentsForSelectedDay.length === 0 && <p>No appointments</p>}

                    {appointmentsForSelectedDay.map((a, i) => (
                        <div key={i} className='appointment-card'>
                            <h2>{a.date} · {a.time} – {a.title}</h2>
                            <ul>
                                <li>Client: {a.client}</li>
                                <li>Vehicle Type: {a.vehicle}</li>
                                <li>Extras: {a.extras}</li>
                            </ul>
                            <button onClick={() => openModalForEdit(a)}>Edit</button>
                        </div>
                    ))}
                </div>
            </div>

            {modalOpen && (
                <div className="modal-overlay" onClick={closeModal}>
                    <div className="modal-content" onClick={e => e.stopPropagation()}>
                        <h2>{appointmentEditing && typeof appointmentEditing._index === 'number' ? 'Edit' : 'Add'} Appointment</h2>
                        <label>
                            Service:
                            <select
                                name="title"
                                value={appointmentEditing?.title || ''}
                                onChange={handleChange}
                            >
                                {services.map((s, i) => (
                                    <option key={i} value={s.title}>
                                        {s.title}
                                    </option>
                                ))}
                            </select>
                        </label>
                        <label>
                            Time:
                            <input
                                type="text"
                                name="time"
                                value={appointmentEditing?.time || ''}
                                onChange={handleChange}
                            />
                        </label>
                        <label>
                            Client:
                            <input
                                type="text"
                                name="client"
                                value={appointmentEditing?.client || ''}
                                onChange={handleChange}
                            />
                        </label>
                        <label>
                            Vehicle:
                            <input
                                type="text"
                                name="vehicle"
                                value={appointmentEditing?.vehicle || ''}
                                onChange={handleChange}
                            />
                        </label>
                        <label>
                            Extras:
                            <input
                                type="text"
                                name="extras"
                                value={appointmentEditing?.extras}
                                onChange={handleChange}
                            />
                        </label>
                        <button onClick={saveChanges}>Save</button>
                        <button onClick={closeModal}>Close</button>
                        {appointmentEditing && typeof appointmentEditing._index === 'number' && (
                            <button onClick={deleteAppointment} style={{ backgroundColor: 'red', color: 'white' }}>
                                Delete
                            </button>
                        )}
                    </div>
                </div>
            )}
        </div>
    );
}
