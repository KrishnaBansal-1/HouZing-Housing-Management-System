import React from 'react'
import {useState} from 'react'
import '../styles/Login.css'
import { registerNewVisitor, preApproveVisitor } from '../services/visitorService';


const Newvisitor = (props) => {

    const [name, setName] = useState('');
    const [purpose, setPurpose] = useState('');
    const [phone, setPhone] = useState('');
    const [house, setHouse] = useState('');
    

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const resp = await (!props.isResident ? registerNewVisitor(name, phone, house, purpose) : preApproveVisitor(name, phone));
            console.log(resp);
            alert("Visitor Registered Successfully with \nId : " + resp.visitorTokenNo + '\n Give the id to visitor');
            
        }
        catch (error) {
            alert("Problem Registering : " + error.data.message);
        }
        setHouse('');
        setName('');
        setPhone('');
        setPurpose('');
    };

    return (

        <div className="loginBox">
            <h2>Enter New Visitor Details</h2>
            <form onSubmit={(e) => {
                handleSubmit(e)
            }}>
                <div>
                <label htmlFor="username">Enter Name:</label>
                <input
                    type="text"
                    id="username"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                </div>
                <div>
                <label htmlFor="phoneno">Phone No:</label>
                <input
                    type="Number"
                    id="phoneno"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                />
                </div>
                {props.isResident ?
                    <></>
                    :
                    <>
                    <div>
                    <label htmlFor="House">House No:</label>
                    <input
                        type="number"
                        id="houseno"
                        value={house}
                        onChange={(e) => setHouse(e.target.value)}
                    />
                    </div>
                    <div>
                    <label htmlFor="purpose">Purpose :</label>
                    <input
                        type="text"
                        id="purpose"
                        value={purpose}
                        onChange={(e) => setPurpose(e.target.value)}
                    />
                    </div>
                    </>
                }
                
                
                <button type="submit">Register Visitor</button>
            </form>
        </div>
  )
}

export default Newvisitor
