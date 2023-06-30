import * as React from "react";
import styles from "../styles/Navbar.module.scss";
import { Link } from "react-router-dom";
import LoginBar from "./LoginBar";
import { GoogleLogin } from "@react-oauth/google";
import { getLogging } from "../services/loggingService";
import { getAuthentincate } from "../services/authService";

const Navbar = ({ user, setUser, valid, setValid }) => {
  // TODO: Differentiate the current page in the NavBar

  // Initial State will be fetched from the backend
  const [LoggedIn, setLoggedIn] = React.useState(false);
  const [googleToken, setGoogleToken] = React.useState("");

  const secretKey =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

  const handleLogin = (response) => {
    setGoogleToken(response);
    setLoggedIn(true);
  };

  const handleFailure = (error) => {
    console.log("Sign in failed", error);
    setLoggedIn(false);
    setValid(false);
    setUser("");
    setGoogleToken("");
  };

  const handleLogout = () => {
    setLoggedIn(false);
    setValid(false);
    setUser("");
    setGoogleToken("");
    localStorage.removeItem("token");
  };

  React.useEffect(() => {
    if (LoggedIn && googleToken) {
      getLogging(setValid, setUser, googleToken).catch((error) => {
        // if logged with wrong email
        console.log(error);
      });
    }
  }, [googleToken]);

  return (
    <>
      <div className={styles.Navbar}>
        <ul className={styles.NavLinks}>
          <li className={styles.NavLink} title="Home">
            <Link to="/">Home</Link>
          </li>

          {/* Manage Reservations Should Only be Visible if the user is logged in */}
          {user && (
            <li className={styles.NavLink} title="Manage Reservations">
              <Link to="/ManageReservations">Manage Reservations</Link>
            </li>
          )}
        </ul>

        <div className={styles.User}>
          <LoginBar
            valid={valid}
            token={user}
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
