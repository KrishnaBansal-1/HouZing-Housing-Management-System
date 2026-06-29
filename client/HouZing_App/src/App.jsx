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
import Allvisitors from './pages/Allvisitors'

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
                <Route path="signup" element={<div>Signup Page</div>} />
                <Route path="users" element={<div>Users Page</div>} />
                <Route path="resident" element={<div>Resident Page</div>} />
                <Route path="visitors" element={<Allvisitors/>} />
            </Route>

            <Route path="/resident" element={<Residents />}>
                <Route path="" element={<Profile />} />
                <Route path="approve" element={<div>Approve Visitors</div>} />
                <Route path="visitors" element={<div>Visitors Page</div>} />
                <Route path="preregister" element={<div>Pre-Register Visitors</div>} />            
            </Route>

            <Route path="/security" element={<Security />} >
                <Route path="" element={<Profile />} />
                <Route path="register" element={<div>New Visitor</div>} />
                <Route path="update" element={<div>Update Visitor</div>} />
                <Route path="visitors" element={<Allvisitors/>} />
            </Route>
          </Routes>
      </div>
      

    </>
  )
}

export default App
