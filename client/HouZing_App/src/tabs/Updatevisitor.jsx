import React from 'react'
import { updateVisitor } from '../services/visitorService'
import '../styles/Login.css'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const Updatevisitor = () => {
    const [id, setId] = React.useState('')
    const navigate = useNavigate();
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await updateVisitor(id);
            alert( 'Visitor Id : ' + response.visitorTokenNo + " Exited Successfully.");
            navigate('/security/visitors')
        }
        catch (error) {
            alert('Error : ' + error?.data?.message);
        }
        setId('');
    };

    return (
        <div className="loginBox">
            <h2>Visitor Exit Portal</h2>
            <form onSubmit={(e) => {
                handleSubmit(e)
            }}>
                <div>
                <label htmlFor="vid">Enter Id:</label>
                <input
                    type="number"
                    id="vidd"
                    value={id}
                    onChange={(e) => setId(e.target.value)}
                />
                </div>
                
                <button type="submit">Exit Visitor</button>
            </form>
        </div>
  )
}

export default Updatevisitor
