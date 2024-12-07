import axios from 'axios';

type HttpMethods = "GET" | "POST" | "PUT" | "DELETE";

axios.defaults.baseURL = "http://localhost:8080";
axios.defaults.headers.post["Content-Type"] = "application/json";

export const getJwtToken = () => {
  return window.localStorage.getItem("jwt_token")
}

export const setAuthToken = (jwtToken:string) => {
  window.localStorage.setItem("jwt_token", jwtToken)
}

export const request = (url:string, method:HttpMethods, data?:any) => {
  let headers = {};
  if(getJwtToken() !== null && getJwtToken() !== "null") {
    headers = {"Authorization": getJwtToken()};
  }

  return axios({
    url,
    method,
    data,
    headers
  });
}