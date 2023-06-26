import * as React from "react";
import styles from "../styles/Navbar.module.scss";
import { Link } from "react-router-dom";
import { GoogleLogin, googleLogout } from "@react-oauth/google";
import axios from "axios";
import jwt_decode from "jwt-decode";

const Login = ({ handleLogin, handleFailure }) => {
  const clientID =
    "461418541066-5c9p2cf0d6d8qthhgh7n2tjrd28pf3t9.apps.googleusercontent.com";

  const onSuccess = (response) => {
    handleLogin(response);
  };

  const onFailure = (error) => {
    handleFailure(error);
  };

  return (
    <GoogleLogin
      clientId={clientID}
      onSuccess={onSuccess}
      // shape="pill"
      // theme="outline"
      // type="icon"
      // text="sign in with"
      theme="filled_black" // or  "outline"
      text="signin_with"
      shape="circle"
      onFailure={onFailure}
    />
  );
};

export default Login;
