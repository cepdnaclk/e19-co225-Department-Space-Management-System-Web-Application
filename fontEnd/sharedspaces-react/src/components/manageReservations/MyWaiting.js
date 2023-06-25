import React from "react";
import ReservationTable from "./ReservationsTable";
import styles from "../../styles/manageReservations/MyReservations.module.scss";
import { reservations } from "../../data";
const MyWaiting = () => {
  return (
    <div className={styles.container}>
      <h2>Available for Reservation</h2>
      <ReservationTable
        reservations={reservations}
        isActionable={true}
        isAcceptable={true}
      />
      <h2 className={styles.pastReservations}>Currently Unavailable</h2>
      <ReservationTable reservations={reservations} isActionable={true} />
    </div>
  );
};

export default MyWaiting;
