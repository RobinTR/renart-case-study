import axios from 'axios';

const api = axios.create({
    baseURL: 'https://renart-case-study-qls3.onrender.com/api/v1/'
});

export const fetchProducts = async () => {
    const response = await api.get('products');

    return response.data;
};