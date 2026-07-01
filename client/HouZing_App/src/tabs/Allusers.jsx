import React from 'react'
import '../styles/Allentity.css'
import UserProfile from '../profiles/UserProfile';
import { getAllUsers } from '../services/userService';

const Allusers = (props) => {
    const [users, setUsers] = React.useState([]);

    const fetchAllUsers = async () => {
        try {     
            const response = await getAllUsers();
            setUsers(response);
        } catch (error) {
            console.error('Error fetching users:', error.data.message);
        }
    };
    
    React.useEffect(() => {
        fetchAllUsers();
    }, []);

    return (
        <div className='allbox'>
        {
            users.map(
                (u) => <UserProfile user={u} key={u.id} />
            )
        }
        </div>
    )
}

export default Allusers;
