import React from 'react'
import '../styles/VisitorProfile.css'

const VisitorProfile = (props) => {
  const { visitor } = props;
  return (
    <div className="visitorbox">
        <h5>Visitor Id : {visitor?.id}</h5>
        <h5>Name : {visitor?.name}</h5>
        <h5>Contact : {visitor?.phoneNo}</h5>
        <h5>Purpose : {visitor?.purposeOfVisit}</h5>
        <h5>House No : {visitor?.houseNo}</h5>
        <h5>Entry Time : {visitor?.entryTime}</h5>
        <h5>Exit Time : {visitor?.exitTime}</h5>
        <h5>Status : {visitor?.status}</h5>
    </div>
  )
}

export default VisitorProfile
