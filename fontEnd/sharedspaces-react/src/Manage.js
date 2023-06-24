import { useState } from "react";
import Hero from "./components/Hero";
import styles from "./styles/ManageReservations.module.scss";
import MyReservations from "./components/manageReservations/MyReservations";
import MyWaiting from "./components/manageReservations/MyWaiting";
const Manage = () => {
  //State to store whether to render Confirmed Reservations or The Waiting List
  const [isWaiting, setIsWaiting] = useState(false);

  return (
    <div>
      <Hero
        title="Manage Reservations"
        description="Edit, Delete Your Reservations and Check Waiting List"
      >
        {/* Toggle Between the state */}
        <button
          className={`${styles.toggleSwitch}  ${
            isWaiting ? styles.waiting : null
          }`}
          onClick={() => setIsWaiting(!isWaiting)}
        >
          <p className={styles.toggleItem}>Confirmed Reservations</p>
          <p className={styles.toggleItem}>Waiting List</p>
        </button>
      </Hero>

      <div className={styles.tableContainer}>
        {/* Conditionally Render the Confirmed Reservations and Waiting List according to state */}
        {isWaiting ? <MyWaiting /> : <MyReservations />}
      </div>
    </div>
  );
};

export default Manage;
