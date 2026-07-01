import { resolvePath } from "react-router-dom";
import api from "../utils/api";

export const login = async (username, password) => {
    try {
        const response = await api.post("/auth/login", {
            username : username,
            password : password,
        });

        return response.data;
    } catch (error) {
        throw error.response;
    }
};

export const register = async (username, pass, role, house, phone) => {
    console.log("Registering user with details:", { username, pass, role, house, phone });
    try {
        console.log("try")
        const response = await api.post('/auth/signup', {
            username : username,
            password : pass,
            roles : [role],
            houseNo : house,
            phoneNo : phone
        });
        console.log("hello\n")
        return response.data;
    } catch (error) {
        throw error.response?.data || error;
    }
};

