import { useState } from "react";
import Hero from "./components/Hero";
import styles from "./styles/ManageReservations.module.scss";
const Manage = () => {
  const [isWaiting, setIsWaiting] = useState(false);

  return (
    <div>
      <Hero
        title="Manage Reservations"
        description="Edit, Delete Your Reservations and Check Waiting List"
      >
        <div
          className={`${styles.toggleSwitch}  ${
            isWaiting ? styles.waiting : null
          }`}
          onClick={() => setIsWaiting(!isWaiting)}
        >
          <p className={styles.toggleItem}>Confirmed Reservations</p>
          <p className={styles.toggleItem}>Waiting List</p>
        </div>
      </Hero>
    </div>
  );
};

export default Manage;
