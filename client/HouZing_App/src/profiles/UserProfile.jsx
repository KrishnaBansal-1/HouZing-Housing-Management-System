import React from 'react'
import '../styles/profilestyle.css'
import { useState } from 'react';
import {changePassword} from '../services/userService';

const UserProfile = (props) => {

    const [changepass, setChangepass] = useState(false);
    const [newpass, setNewpass] = useState('');

    const handlePasswordChange = async () => {
        try{
            const resp = await changePassword(props.user.username, newpass);
            alert('Password updated successfully for userid : ' + resp.id);
        }
        catch(err){
            alert(err.data.message);
        }
    };

    return (
        <div className="profilebox">
            <h2>User Id : {props.user?.id}</h2>
            <h2>Name : {props.user?.username}</h2>
            <h2>Role : {props.user?.userRole}</h2>
            {changepass && (
                <div>
                    <input
                        type="password"
                        placeholder="Enter new password"
                        value={newpass}
                        onChange={(e) => setNewpass(e.target.value)}
                    />
                    <button onClick={handlePasswordChange}>Update Password</button>
                </div>
            )}
            <button onClick={() =>{
                setChangepass(!changepass);
            }}>{changepass ? 'Cancel' : 'Change password'}</button>
        </div>
    )
}

export default UserProfile
