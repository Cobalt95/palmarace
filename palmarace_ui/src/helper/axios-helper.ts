import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

type HttpMethods = "GET" | "POST" | "PUT" | "DELETE";

axios.defaults.baseURL = "http://localhost:8080";
axios.defaults.headers.post["Content-Type"] = "application/json";

export const getJwtToken = () => {
  return window.localStorage.getItem("jwt_token")
}

export const setAuthToken = (jwtToken:string) => {
  window.localStorage.setItem("jwt_token", jwtToken)
}

export const logout = () => {
  window.localStorage.removeItem("jwt_token")
}

const isTokenExpired = (jwtToken:string) => {
  try {
    const decodedJwtToken = jwtDecode(jwtToken);
    // exp field present
    if (decodedJwtToken.exp) {
      // exp value in the past
      if (decodedJwtToken.exp * 1000 < Date.now()) {
        return true;
      }
    } 
  } catch (error) {
    console.log("Could not decode jwtToken");
    return true;
  }

  return false;
}

export const isAuthenticated = () => {
  const jwtToken = getJwtToken();
  if (!jwtToken) {
    return false;
  }
  return !isTokenExpired(jwtToken);
}

export const request = (url:string, method:HttpMethods, data?:any) => {
  let headers = {};
  if(isAuthenticated()) {
    headers = {"Authorization": getJwtToken()};
  }

  return axios({
    url,
    method,
    data,
    headers
  });
}