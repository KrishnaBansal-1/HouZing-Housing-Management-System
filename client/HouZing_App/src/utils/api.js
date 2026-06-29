import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_BACKEND_BASE_URL
});

api.interceptors.request.use(
    (config) => {

        const auth = JSON.parse(localStorage.getItem("auth"));

        if (auth?.jwt) {
            config.headers.Authorization = `Bearer ${auth.jwt}`;
        }

        console.log("Request Config:", config); // Log the request configuration

        return config;
    },
    (error) => Promise.reject(error)
);

api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            localStorage.removeItem("auth");
            window.location.href = "/login";
        }

        return Promise.reject(error);
    }
);

export default api;