import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react';
import api from '../utils/api';
import { getMyProfile } from '../services/residentService';

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
        fetchResidentData();
    }, []);

    return (
        <div>
            <h1>Username : {residentData?.username}</h1>
            <h1>Phone Number : {residentData?.phoneNo}</h1>
            <h1>House Number : {residentData?.houseNo}</h1>
            <h1>Active status : {residentData?.isActive ? 'Active' : 'Inactive'}</h1>
        </div>
    )
}

export default ResidentProfile
