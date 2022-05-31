import axios from "axios";


const BASE_URL = "http://localhost:8080/api/auth";
const TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0a3VwYWNAZ21haWwuY29tIiwiaWF0IjoxNjUzODM1Njc3LCJleHAiOjE2NTM5MjIwNzd9.hIibnpcRUDZfcp98_myWAAVT7Mwc8csn3U0ADpNWnHJ4BCLKym-twnlURVL5K22zqHDCnnGZHCmC2GedbqrpUA";

/*const user = JSON.parse(localStorage.getItem("persist:root"))?.user;
const currentUser = user && JSON.parse(user).currentUser;
const TOKEN = currentUser?.accessToken;*/

export const publicRequest = axios.create({
    baseURL: BASE_URL,
});

export const userRequest = axios.create({
    baseURL:BASE_URL,
    header:{token: `Bearer ${TOKEN}`}
});