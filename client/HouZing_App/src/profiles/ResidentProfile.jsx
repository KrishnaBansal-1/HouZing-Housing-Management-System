import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react';
import api from '../utils/api';
import { getMyProfile } from '../services/residentService';
import '../styles/profilestyle.css'

const ResidentProfile = (props) => {

    const [residentData, setResidentData] = React.useState(null);

    const fetchResidentData = async () => {
        try {
            const data = await getMyProfile();
            setResidentData(data);
        } catch (error) {
            console.error('Error fetching resident data:', error);
        }
    };

    useEffect(() => {
        if (props.resident == null)
            fetchResidentData();
        else
            setResidentData(props.resident);
    }, []);

    return (
        <div className="profilebox">
            <h4>Username : {residentData?.username}</h4>
            <h4>Phone Number : {residentData?.phoneNo}</h4>
            <h4>House Number : {residentData?.houseNo}</h4>
            <h4>Active status : {residentData?.isActive ? 'Active' : 'Inactive'}</h4>
        </div>
    )
}

export default ResidentProfile
