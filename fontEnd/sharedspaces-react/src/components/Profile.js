import * as React from "react";
import styles from "../styles/Navbar.module.scss";
import { googleLogout } from "@react-oauth/google";

const Profile = ({ token, handleLogout }) => {
  const firstname = token?.user?.firstName;
  const profileIcon = firstname ? firstname.charAt(0) : "";

  const logOut = () => {
    googleLogout();
    handleLogout();
  };

  // If the user clicks on the profile icon then show the logout option
  // const [open, setOpen] = React.useState(false);
  return (
    <>
      <div className={styles.Profile}>
        <div className={styles.email}>{token.sub}</div>
        <div className={styles.ProfileIcon}>{profileIcon}</div>
        <div>
          <button className={styles.logoutBtn} onClick={logOut}>
            Log out
          </button>
        </div>
      </div>
    </>
  );
};

export default Profile;
