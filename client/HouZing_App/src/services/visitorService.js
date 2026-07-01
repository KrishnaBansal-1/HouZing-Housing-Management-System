import api from "../utils/api";

export const getAllVisitors = async () => {
    try {
        const response = await api.get("/visitor/all");
        return response.data;
    } catch (error) {
        throw error.response;
    }
};

export const registerNewVisitor = async(name, phone, house, purpose) => {
    try{
        const response = await api.post("/visitor/register", {
            name : name,
            phoneNo : phone, 
            houseNo : house, 
            purposeOfVisit : purpose
        })
        return response.data;
    }
    catch (err){
        throw err.response;
    }
}

export const preApproveVisitor = async(name, phone) => {
    try{
        const response = await api.post("/visitor/preregister", {
            name : name,
            phoneNo : phone
        })
        return response.data;
    }
    catch(err){
        throw err.response;
    }
}

export const getMyVisitor = async() => {
    try{
        const response = await api.get("/visitor/my");
        return response.data;
    }
    catch(e){
        throw e.response;
    }
}

export const approveVisitor = async(id, isApproved) => {
    try{
        const response = await api.put("/visitor/approve", {
            visitorTokenNo : id,
            isApproved: isApproved
        });
        return response.data;
    }
    catch(er){
        throw er.response;
    }
}

export const updateVisitor = async(id) => {
    try{
        const response = await api.put(`/visitor/visitor-exit/${id}`);
        return response.data;
    }
    catch(err){
        throw err.response;
    }
} 