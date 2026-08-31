import Service from "@/api/config";

export async function get(url, params) {
    const response = await Service.get(url, { params });
    return response.data;
}

export async function post(url, data) {
    const response = await Service.post(url, data);
    return response.data;
}
