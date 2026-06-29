import React from 'react'
import { getAllVisitors } from '../services/visitorService';
import VisitorProfile from '../profiles/VisitorProfile';
import '../styles/Allvisitors.css'

const Allvisitors = () => {
    const [visitors, setVisitors] = React.useState([]);

    const fetchAllVisitors = async () => {
        try {
            const data = await getAllVisitors();
            setVisitors(data);
        } catch (error) {
            console.error('Error fetching visitors:', error.data.message);
        }
    };
    
    React.useEffect(() => {
        fetchAllVisitors();
    }, []);

    return (
        <div className='allvisitors'>
        {
            visitors.map(
                (v) => <VisitorProfile key={v.id} visitor={v} />
            )
        }
        </div>
    )
}

export default Allvisitors
