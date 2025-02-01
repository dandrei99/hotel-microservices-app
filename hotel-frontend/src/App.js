import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Index from './pages/Index';
import Services from './pages/Services';
import Rooms from './pages/Rooms';
import RoomView from './pages/RoomView';
import Login from "./pages/Login";
import Reservation from './pages/Reservation';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Index />} />
                <Route path="/login" element={<Login />} />
                <Route path="/services" element={<Services />} />
                <Route path="/rooms" element={<Rooms />} />
                <Route path="/rooms/:roomId" element={<RoomView />} />
                <Route path="/reservation/:roomId" element={<Reservation />} />
            </Routes>
        </Router>
    );
}

export default App;
