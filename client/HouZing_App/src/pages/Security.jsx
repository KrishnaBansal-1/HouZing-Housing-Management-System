import React from 'react'
import '../styles/Users.css'
import { Link, Outlet } from 'react-router-dom'

const Security = () => {
  return (
    <div className="userbox">
        <div className="sidebar">
            <Link to="/security">My Profile</Link>
            <Link to="/security/register">New Visitor</Link>
            <Link to="/security/update">Update Visitor</Link>
            <Link to="/security/visitors">All Visitors</Link>
        </div>
        <div className="outlet">
            <Outlet />
        </div>
    </div>
  )
}

export default Security
