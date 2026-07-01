import React from 'react'
import '../styles/profilestyle.css'

const UserProfile = (props) => {

    return (
        <div className="profilebox">
            <h2>User Id : {props.user?.id}</h2>
            <h2>Name : {props.user?.username}</h2>
            <h2>Role : {props.user?.userRole}</h2>
        </div>
    )
}

export default UserProfile
