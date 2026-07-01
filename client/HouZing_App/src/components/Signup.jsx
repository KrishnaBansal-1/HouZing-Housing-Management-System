import '../styles/Login.css'
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { LoggedInContext } from '../context/AuthContext.jsx';
import { login, register } from '../services/authService.js';


const Login = () => {
    
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [selectedRole, setSelectedRole] = useState('');
    const [house, setHouse] = useState(0);
    const [phone, setPhone] = useState(0);

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            console.log("Attempting to register user with details:", { username, password, selectedRole, house, phone });
            const response = await register(username, password, selectedRole, house, phone);
            console.log("User created successfully:", response);
            alert("User with id : " + response.id + " has been created successfully.");
        }
        catch (error) {
            alert(error?.data?.message || 'Login failed. Please try again.');
        }
        setUsername('');
        setPassword('');
        setHouse('');
        setPhone('');
    };

    return (
        <div className="loginBox">
            <h2>Register New User</h2>
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
                <div>
                <label>Choose a role:</label>

                <select
                    value={selectedRole}
                    onChange={(e) => setSelectedRole(e.target.value)}
                >
                    <option value="">Select Role</option>
                    <option value="ADMIN">Admin</option>
                    <option value="RESIDENT">Resident</option>
                    <option value="GUARD">Security</option>
                </select>

                <p>Selected Role: {selectedRole}</p>
                </div>
                {
                    selectedRole === 'RESIDENT' ?
                    <>
                    <div>
                    <label htmlFor="house">House No:</label>
                    <input
                        type="number"
                        id="house"
                        value={house}
                        onChange={(e) => setHouse(e.target.value)}
                    />
                    </div>
                    <div>
                    <label htmlFor="phone">Phone No:</label>
                    <input
                        type="number"
                        id="phone"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                    />
                    </div>
                    </>
                    :
                    <></>

                }
                
                <button type="submit">Register User</button>
            </form>
        </div>
  )
}

export default Login
