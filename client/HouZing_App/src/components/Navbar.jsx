import React from 'react'
import '../styles/Navbar.css'
import { Link } from 'react-router-dom'
import { useContext } from 'react';
import { LoggedInContext } from '../context/AuthContext.jsx';
import { useNavigate } from 'react-router-dom';

function Navbar() {
    const {isLoggedIn, setIsLoggedIn, userlogout} = useContext(LoggedInContext);
    const navigate = useNavigate();

    const handleLogOut = () => {
        userlogout();
        setIsLoggedIn(false);
        console.log('Logged out successfully');
        navigate('/');
    }

    return (
        <div className="navhead">
            <h2>HouZing</h2>
            {isLoggedIn ? <button onClick={() => handleLogOut()}>Logout</button> : <Link to="/login">Login</Link>}
        </div>
    )
}

export default Navbar