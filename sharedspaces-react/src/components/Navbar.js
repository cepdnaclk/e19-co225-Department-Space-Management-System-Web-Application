import * as React from "react";
import styles from "../styles/Navbar.module.scss";
import {Link} from "react-router-dom";
import { GoogleLogin } from "@react-oauth/google";

const Navbar = () => {
  // TODO: Differentiate the current page in the NavBar
  const isLoggedIn = false;
  const clientID = "461418541066-5c9p2cf0d6d8qthhgh7n2tjrd28pf3t9.apps.googleusercontent.com";
  
  const onSuccess = (response) =>{
    console.log("Logged in",response.Profile);

  };

  const  onFailure = (error) =>{
    console.log("Sign in failed",error);
  };

  return (
    <div className={styles.Navbar}>
      <ul className={styles.NavLinks}>
        <li className={styles.NavLink} title="Home">
          <Link to="/">Home</Link>
        </li>

        {/* Manage Reservations Should Only be Visible if the user is logged in */}
        <li className={styles.NavLink} title="Manage Reservations">
          <Link to="/ManageReservations">Manage Reservations</Link>
        </li>
      </ul>
      <div className={styles.User}>
        {isLoggedIn ? (
          <Profile />
        ) : (
          <GoogleLogin
            clientId = {clientID}
            onSuccess={onSuccess}
            onFailure = {onFailure}
          />         
        )}
      </div>
    </div>
  );
};

const Profile = () => {
  // If the user clicks on the profile icon then show the logout option
  // const [open, setOpen] = React.useState(false);
  return (
    <div className={styles.Profile}>
      <div className={styles.email}>e19372@eng.pdn.ac.lk</div>
      <div className={styles.ProfileIcon}>K</div>
    </div>
  );
};
export default Navbar;
