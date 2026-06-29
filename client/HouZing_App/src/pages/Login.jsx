import '../styles/Login.css'
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { LoggedInContext } from '../context/AuthContext.jsx';
import { login } from '../services/authService.js';


const Login = () => {
    
    const {isLoggedIn, setIsLoggedIn, userlogin} = useContext(LoggedInContext);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await login(username, password);
            console.log('Login response:', response);
            userlogin(response);
            setIsLoggedIn(true);
            console.log('Logged in successfully');
            if (response.userRole === 'ADMIN') {
                navigate('/admin');
            } else if (response.userRole === 'RESIDENT') {
                navigate('/resident');
            } else if (response.userRole === 'GUARD') {
                navigate('/security');
            } else {
                alert('Login failed: Invalid user role');
            }
        }
        catch (error) {
            alert(error?.data?.message || 'Login failed. Please try again.');
        }
        setUsername('');
        setPassword('');
    };

    return (
        <div className="loginBox">
            <h2>Login</h2>
            <form onSubmit={(e) => {
                handleSubmit(e)
            }}>
                <div>
                <label htmlFor="username">Username:</label>
                <input
                    type="text"
                    id="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                </div>
                <div>
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                </div>
                
                <button type="submit">Login</button>
            </form>
        </div>
  )
}

export default Login
