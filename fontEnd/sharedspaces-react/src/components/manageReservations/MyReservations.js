import React from "react";
import styles from "../../styles/manageReservations/MyReservations.module.scss";
import ReservationTable from "./ReservationsTable";
import { reservations } from "../../data";
const MyReservations = () => {
  return (
    <div className={styles.container}>
      <h2>Upcoming Reservations</h2>
      <ReservationTable reservations={reservations} isActionable={true} />
      <h2 className={styles.pastReservations}>Past Reservations</h2>
      <ReservationTable reservations={reservations} />
    </div>
  );
};

export default MyReservations;
