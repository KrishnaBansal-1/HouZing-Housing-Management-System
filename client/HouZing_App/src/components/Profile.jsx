import React from 'react'
import { LoggedInContext } from '../context/AuthContext'
import ResidentProfile from '../profiles/ResidentProfile';
import UserProfile from '../profiles/UserProfile';

const Profile = () => {
    const { auth } = React.useContext(LoggedInContext);
    return (
      <div >
          <h2>My Profile ({auth?.userRole})</h2>
          
          {
              auth?.userRole === 'RESIDENT' ? 
              <> <h2>User Id : {auth?.id}</h2> <ResidentProfile /> </>: <UserProfile user = {auth}/>
          }
      </div>
    )
}

export default Profile
