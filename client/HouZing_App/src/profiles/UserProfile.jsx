import React from 'react'

const UserProfile = (props) => {

    return (
        <div>
            <h2>User Id : {props.user?.id}</h2>
            <h2>Name : {props.user?.username}</h2>
            <h2>Role : {props.user?.userRole}</h2>
        </div>
    )
}

export default UserProfile
