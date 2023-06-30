import * as React from "react";
import styles from "../styles/Navbar.module.scss";
import { Link } from "react-router-dom";
import LoginBar from "./LoginBar";
import { getLogging } from "../services/loggingService";
import { checkUser } from "../utils";

const Navbar = ({ user, setUser, valid, setValid }) => {
  // TODO: Differentiate the current page in the NavBar

  // Initial State will be fetched from the backend
  const [LoggedIn, setLoggedIn] = React.useState(false);
  const [googleToken, setGoogleToken] = React.useState("");


  const handleLogin = (response) => {
    setGoogleToken(response);
    setLoggedIn(true);
    localStorage.setItem('isloggedin','1');
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
    localStorage.setItem('isloggedin','0');
  };

  React.useEffect(() => {
    if (LoggedIn && googleToken) {
      getLogging(googleToken)
        .then((res) => {
          checkUser(setUser, setValid);
        })
        .catch((error) => {
          // if logged with wrong email
          console.log(error);
        });
    }
  }, [googleToken]);

  React.useEffect(() =>{
    const loggedinStatus = localStorage.getItem('isloggedin');
    if(loggedinStatus==='1'){
      setLoggedIn(true);
      checkUser(setUser, setValid, handleLogout);
    }
  }, [])

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
