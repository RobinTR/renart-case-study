import axios from 'axios';

const api = axios.create({
    baseURL: 'http://13.60.241.103/api/v1/'
});

export const fetchProducts = async () => {
    const response = await api.get('products');

    return response.data;
};