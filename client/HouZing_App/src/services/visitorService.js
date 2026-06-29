import api from "../utils/api";

export const getAllVisitors = async () => {
    try {
        const response = await api.get("/visitor/all");
        return response.data;
    } catch (error) {
        throw error.response;
    }
};