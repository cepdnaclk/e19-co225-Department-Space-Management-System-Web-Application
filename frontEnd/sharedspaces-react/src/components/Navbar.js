import * as React from "react";
import styles from "../styles/Navbar.module.scss";
import { Link } from "react-router-dom";
import { GoogleLogin, googleLogout } from "@react-oauth/google";

const Navbar = () => {
  // TODO: Differentiate the current page in the NavBar

  // Initial State will be fetched from the backend
  const [LoggedIn, setLoggedIn] = React.useState(true);
  const clientID =
    "461418541066-5c9p2cf0d6d8qthhgh7n2tjrd28pf3t9.apps.googleusercontent.com";

  const onSuccess = (response) => {
    console.log("Logged in", response);
    setLoggedIn(true);
  };

  const onFailure = (error) => {
    console.log("Sign in failed", error);
    setLoggedIn(false);
  };

  const logOut = () =>{
    googleLogout();
    setLoggedIn(false);
  }

  const Profile = () => {
    // If the user clicks on the profile icon then show the logout option
    // const [open, setOpen] = React.useState(false);
    return (
      <div className={styles.Profile}>
        <div className={styles.email}>e19372@eng.pdn.ac.lk</div>
        <div className={styles.ProfileIcon}>K</div>
        <div><button onClick={logOut}>Log out</button></div>
      </div>
    );
  };

  return (
    <div className={styles.Navbar}>
      <ul className={styles.NavLinks}>
        <li className={styles.NavLink} title="Home">
          <Link to="/">Home</Link>
        </li>

        {/* Manage Reservations Should Only be Visible if the user is logged in */}
        {LoggedIn ? (
          <li className={styles.NavLink} title="Manage Reservations">
            <Link to="/ManageReservations">Manage Reservations</Link>
          </li>
        ) : null}
      </ul>
      <div className={styles.User}>
        {LoggedIn ? (
          <Profile />
        ) : (
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
        )}
      </div>
    </div>
  );
};

export default Navbar;
