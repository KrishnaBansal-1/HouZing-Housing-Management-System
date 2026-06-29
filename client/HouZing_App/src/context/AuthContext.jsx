import React from 'react'
import { createContext } from 'react'
import { useState } from 'react'

export const LoggedInContext = createContext();

const AuthContext = (props) => {
    const [isLoggedIn, setIsLoggedIn] = React.useState(false);
    
    const [auth, setAuth] = useState(() => {
        const stored = localStorage.getItem("auth");
        return stored ? JSON.parse(stored) : null;
    });

    const userlogin = (data) => {
        localStorage.setItem("auth", JSON.stringify(data));
        setAuth(data);
        console.log('in context Logged in successfully');
    };

    const userlogout = () => {
        localStorage.removeItem("auth");
        setAuth(null);
        console.log('in context Logged out successfully');
    };
    return (
        <LoggedInContext.Provider value={{isLoggedIn, setIsLoggedIn, auth, userlogin, userlogout}}>
            {props.children}
        </LoggedInContext.Provider> 
    )
}

export default AuthContext
