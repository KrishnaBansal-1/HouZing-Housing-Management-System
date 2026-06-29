import React from 'react'
import { Link, Outlet } from 'react-router-dom'
import '../styles/Users.css'

const Admin = () => {
  return (
    <div className="userbox">
        
        <div className="sidebar">
            <Link to="/admin">My Profile</Link>
            <Link to="/admin/signup">Add New User</Link>
            <Link to="/admin/users">Users</Link>
            <Link to="/admin/resident">Residents</Link>
            <Link to="/admin/visitors">Visitors</Link>
        </div>

        <div className="outlet">
            <Outlet />
        </div>
    </div>
  )
}

export default Admin
