import React from 'react'
import '../styles/Users.css'
import { Link, Outlet } from 'react-router-dom'

const Residents = () => {
  return (
    <div className="userbox">
        
        <div className="sidebar">
            <Link to="/resident">My Profile</Link>
            <Link to="/resident/approve">Approve Visitors</Link>
            <Link to="/resident/visitors">Visitors</Link>
            <Link to="/resident/preregister">Pre-Register Visitors</Link>
        </div>

        <div className="outlet">
            <Outlet />
        </div>
    </div>
  )
}

export default Residents
