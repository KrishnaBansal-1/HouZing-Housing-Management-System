import React from 'react'
import { LoggedInContext } from '../context/AuthContext'
import ResidentProfile from '../profiles/ResidentProfile';
import UserProfile from '../profiles/UserProfile';
import { useState } from 'react';
import { changePassword } from '../services/userService';

const Profile = () => {
        const { auth } = React.useContext(LoggedInContext);
        const [changepass, setChangepass] = useState(false);
        const [newpass, setNewpass] = useState('');
    
        const handlePasswordChange = async () => {
            try{
                const resp = await changePassword(auth?.username, newpass);
                alert('Password updated successfully for userid : ' + resp.id);
            }
            catch(err){
                alert(err.data.message);
            }
        };
        return (
        <div >
            <h2>My Profile ({auth?.userRole})</h2>
            
            {
                auth?.userRole === 'RESIDENT' ? 
                <> 
                <h2>User Id : {auth?.id}</h2> 
                <ResidentProfile /> 
                
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
                    
                </>
                
                
                
                : <UserProfile user = {auth}/>
            }
        </div>
        )
}

export default Profile
