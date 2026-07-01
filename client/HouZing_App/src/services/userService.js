import api from "../utils/api";

export const getAllUsers = async () => {
    try {
        const response = await api.get("/admin/users");
        return response.data;
    } catch (error) {
        throw error.response?.data || error;
    }
};

export const getAllResidents = async () => {
    try {
        const response = await api.get("/admin/residents");
        return response.data;
    } catch (error) {
        throw error.response?.data || error;
    }
};

export const changePassword = async (username, newpassword) => {
    try {
        const response = await api.put("/auth/change-password", {
            username: username,
            newPassword : newpassword
        });
        return response.data;
    } catch (error) {
        throw error.response?.data || error;
    }
};