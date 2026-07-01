import React from 'react'
import { getAllVisitors, getMyVisitor } from '../services/visitorService';
import VisitorProfile from '../profiles/VisitorProfile';
import '../styles/Allentity.css'

const Allvisitors = (props) => {
    const [visitors, setVisitors] = React.useState([]);

    const fetchAllVisitors = async () => {
        try {
            const data = await (props.isResident ? getMyVisitor() : getAllVisitors());
            setVisitors(data);
        } catch (error) {
            alert('Error fetching visitors:', error.data.message);
        }
    };
    
    React.useEffect(() => {
        fetchAllVisitors();
    }, []);

    return (
        <div className='allbox'>
        {
            visitors.map(
                (v) => <VisitorProfile key={v.id} visitor={v} />
            )
        }
        </div>
    )
}

export default Allvisitors
