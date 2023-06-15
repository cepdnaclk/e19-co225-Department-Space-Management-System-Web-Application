import * as React from "react";
import styles from "../styles/Navbar.module.scss";
import { Link } from "react-router-dom";
import axios from "axios";
import jwt_decode from "jwt-decode";
import Login from "./Login";
import Profile from "./Profile";
import LoginBar from "./LoginBar";

const Navbar = () => {
  // TODO: Differentiate the current page in the NavBar

  // Initial State will be fetched from the backend
  const [LoggedIn, setLoggedIn] = React.useState(false);
  const [googleToken, setGoogleToken] = React.useState("");
  const [user, setUser] = React.useState("");
  const [token, setToken] = React.useState("");
  const [valid, setValid] = React.useState(false);

  const secretKey =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

  const handleLogin = (response) => {
    setGoogleToken(response);
    console.log("Logged in", response);
    setLoggedIn(true);
  };

  const handleFailure = (error) => {
    console.log("Sign in failed", error);
    setLoggedIn(false);
    setValid(false);
    setUser("");
    setGoogleToken("");
    setToken("");
  };

  const handleLogout = () => {
    setLoggedIn(false);
    setValid(false);
    setUser("");
    setGoogleToken("");
    setToken("");
    localStorage.removeItem("token");
  };

  React.useEffect(() => {
    if (LoggedIn && googleToken) {
      axios
        .post("http://localhost:8080/log", {
          clientId: googleToken.clientId,
          credential: googleToken.credential,
          select_by: googleToken.select_by,
        })
        .then((response) => {
          console.log(response);
          setValid(response.data.valid);
          setUser(response.data.refreshToken);
          localStorage.setItem("token", user);
          setToken(jwt_decode(response.data.refreshToken));
          console.log(token);
        })
        .catch((error) => {
          // Handle error
          console.error("Error fetching data:", error);
        });
    }
  }, [LoggedIn, googleToken, valid]);

  return (
    <>
      <div className={styles.Navbar}>
        <ul className={styles.NavLinks}>
          <li className={styles.NavLink} title="Home">
            <Link to="/">Home</Link>
          </li>

          {/* Manage Reservations Should Only be Visible if the user is logged in */}
          {LoggedIn && (
            <li className={styles.NavLink} title="Manage Reservations">
              <Link to="/ManageReservations">Manage Reservations</Link>
            </li>
          )}
        </ul>

        <div className={styles.User}>
          <LoginBar
            valid={valid}
            token={token}
            handleLogout={handleLogout}
            handleLogin={handleLogin}
            handleFailure={handleFailure}
          />
        </div>
      </div>
    </>
  );
};

export default Navbar;
