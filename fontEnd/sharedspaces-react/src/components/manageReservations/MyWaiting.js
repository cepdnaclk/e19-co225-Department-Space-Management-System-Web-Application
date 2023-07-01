import React from "react";
import ReservationTable from "./ReservationsTable";
import styles from "../../styles/manageReservations/MyReservations.module.scss";
import { useEffect, useState } from "react";
import { checkUser } from "../../utils";
import { getUserWaiting } from "../../services/waitingService";

const MyWaiting = () => {
  const [user, setUser] = useState("");
  const [valid, setValid] = useState(false);
  const [waiting, setWaiting] = useState([]);
  const [availableWaiting, setAvailableWaiting] = useState([]);
  const [unavailableWaiting, setUnAvailableWaiting] = useState([]);

  useEffect(() => {
    checkUser(setUser, setValid);
  }, []);

  function getReservation() {
    getUserWaiting(setWaiting, user.email);
  }

  useEffect(() => {
    if (user !== "") {
      getReservation();
    }
  }, [user]);

  useEffect(() => {
    if (waiting !== []) {
      setAvailableWaiting(waiting.filter((wait) => wait.available));
      setUnAvailableWaiting(waiting.filter((wait) => !wait.available));
    }
  }, [waiting]);

  return (
    <div className={styles.container}>
      <h2>Available for Reservation</h2>
      <ReservationTable
        reservations={availableWaiting}
        isActionable={true}
        isAcceptable={true}
        waitingList={true}
        user={user}
        updateReservation={getReservation}
      />
      <h2 className={styles.pastReservations}>Currently Unavailable</h2>
      <ReservationTable
        reservations={unavailableWaiting}
        isActionable={true}
        waitingList={true}
        user={user}
        updateReservation={getReservation}
      />
    </div>
  );
};

export default MyWaiting;
