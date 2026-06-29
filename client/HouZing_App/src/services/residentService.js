import api from "../utils/api";

export const getMyProfile = async () => {
    try {
        const response = await api.get("/residents/profile");
        return response.data;
    } catch (error) {
        throw error.response;
    }
};