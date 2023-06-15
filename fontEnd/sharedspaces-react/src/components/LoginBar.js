import * as React from "react";
import Login from "./Login";
import Profile from "./Profile";

const LoginBar = ({
  token,
  handleLogout,
  handleFailure,
  handleLogin,
  valid,
}) => {
  return (
    <>
      {valid ? (
        <Profile token={token} handleLogout={handleLogout} />
      ) : (
        <Login handleLogin={handleLogin} handleFailure={handleFailure} />
      )}
    </>
  );
};

export default LoginBar;
