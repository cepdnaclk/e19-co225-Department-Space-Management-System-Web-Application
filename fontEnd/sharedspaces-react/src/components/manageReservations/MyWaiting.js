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
  function getReservation() {
    getUserWaiting(setWaiting, user.email);
  }
  useEffect(() => {
    checkUser(setUser, setValid);
  }, []);

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
      {availableWaiting.length === 0 ? (
        <p className={styles.NoReservations}>
          You are not in a waiting list for any reservation
        </p>
      ) : (
        <ReservationTable
          reservations={availableWaiting}
          isActionable={true}
          isAcceptable={true}
          waitingList={true}
          user={user}
        />
      )}
      {unavailableWaiting.length !== 0 && (
        <>
          <h2 className={styles.pastReservations}>Currently Unavailable</h2>
          <ReservationTable
            reservations={unavailableWaiting}
            isActionable={true}
            waitingList={true}
            user={user}
          />
        </>
      )}
    </div>
  );
};

export default MyWaiting;
