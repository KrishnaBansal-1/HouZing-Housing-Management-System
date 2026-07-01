import React from 'react'
import '../styles/Login.css'
import { approveVisitor } from '../services/visitorService'

const ApproveVisitor = () => {
    const [id, setId] = React.useState('')
    const [approve, setApprove] = React.useState('')

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            const resp = await approveVisitor(id, (approve.toLowerCase() === 'approve'));
            if (approve.toLowerCase() === 'approve')
                alert('Visitor Approved Successfully : ' + resp.name)
            else
                alert('Visitor Rejected Successfully : ' + resp.name);
        }
        catch(err){
            alert('Error : ' + err.data.message);
        }
        setApprove('')
        setId('')
    }

    return (
        <div className="loginBox">
                <h2>Approve Visitor</h2>
                <form onSubmit={(e) => {
                    handleSubmit(e)
                }}>
                    <div>
                    <label htmlFor="vid">Enter Visitor Id:</label>
                    <input
                        type="number"
                        id="vid"
                        value={id}
                        onChange={(e) => setId(e.target.value)}
                    />
                    </div>
                    <div>
                    <label htmlFor="approve">Type 'approve' to approve visitor':</label>
                    <input
                        type="text"
                        id="approve"
                        value={approve}
                        onChange={(e) => setApprove(e.target.value)}
                    />
                    </div>
                    
                    <button type="submit">Submit Status</button>
                </form>
            </div>
    )
}

export default ApproveVisitor
