import api from "../utils/api";

export const getAllUsers = async () => {
    try {
        const response = await api.get("/admin/users");
        return response.data;
    } catch (error) {
        throw error.response?.data || error;
    }
};

export const getAllRessidents = async () => {
    try {
        const response = await api.get("/admin/residents");
        return response.data;
    } catch (error) {
        throw error.response?.data || error;
    }
};