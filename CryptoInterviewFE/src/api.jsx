import axios from 'axios';

const API_BASE = 'http://localhost:8080/api';

export const fetchPrices = () => axios.get(`${API_BASE}/crypto/prices`);
export const fetchAccount = () => axios.get(`${API_BASE}/account`);
export const resetAccount = () => axios.post(`${API_BASE}/account/reset`);
export const buyCrypto = (data) => axios.post(`${API_BASE}/trade/buy`, data);
export const sellCrypto = (data) => axios.post(`${API_BASE}/trade/sell`, data);
export const fetchTransactions = () => axios.get(`${API_BASE}/transactions`);