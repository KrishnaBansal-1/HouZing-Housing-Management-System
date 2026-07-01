import React from 'react'
import '../styles/Allentity.css'
import {getAllResidents} from '../services/userService';
import ResidentProfile from '../profiles/ResidentProfile';

const Allresidents = (props) => {
    const [residents, setResidents] = React.useState([]);

    const fetchAllResidents = async () => {
        try {     
            const data = await getAllResidents();
            setResidents(data);
        } catch (error) {
            console.error('Error fetching residents:', error.data.message);
        }
    };
    
    React.useEffect(() => {
        fetchAllResidents ();
    }, []);

    return (
        <div className='allbox'>
        {
            residents.map(
                (r) => <ResidentProfile resident={r} key={r.username} />
            )
        }
        </div>
    )
}

export default Allresidents;
