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

// export const register = async (userData) => {
//     try {
//         const response = await api.post("/auth/register", userData);
//         return response.data;
//     } catch (error) {
//         throw error.response?.data || error;
//     }
// };

