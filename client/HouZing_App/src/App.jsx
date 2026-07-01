import { useState } from 'react'
import { Routes, Route } from 'react-router-dom'
import './App.css'
import Navbar from './components/Navbar'
import Home from './pages/Home'
import Login from './pages/Login'
import Admin from './pages/Admin'
import Residents from './pages/Residents'
import Security from './pages/Security'
import Profile from './components/Profile'
import Allvisitors from './tabs/Allvisitors'
import Newvisitor from './tabs/Newvisitor'
import ApproveVisitor from './tabs/ApproveVisitor'
import Updatevisitor from './tabs/Updatevisitor'
import Signup from './components/Signup.jsx'
import Allusers from './tabs/Allusers.jsx'
import Allresidents from './tabs/Allresidents.jsx'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Navbar />
      <div className="box">
          <Routes>
            <Route path="/" element={<Home />} />

            <Route path="/login" element={<Login />} />

            <Route path="/admin" element={<Admin />}>
                <Route path="" element={<Profile />} />
                <Route path="signup" element={<Signup />} />
                <Route path="users" element={<Allusers />} />
                <Route path="resident" element={<Allresidents />} />
                <Route path="visitors" element={<Allvisitors isResident = {false}/>} />
            </Route>

            <Route path="/resident" element={<Residents />}>
                <Route path="" element={<Profile />} />
                <Route path="approve" element={<ApproveVisitor  />} />            
                <Route path="visitors" element={<Allvisitors isResident = {true}/>} />
                <Route path="preregister" element={<Newvisitor isResident = {true}/>} />            
            </Route>

            <Route path="/security" element={<Security />} >
                <Route path="" element={<Profile />} />
                <Route path="register" element={<Newvisitor isResident = {false}/>} />
                <Route path="update" element={<Updatevisitor />} />
                <Route path="visitors" element={<Allvisitors isResident = {false}/>} />
            </Route>
          </Routes>
      </div>
      

    </>
  )
}

export default App
