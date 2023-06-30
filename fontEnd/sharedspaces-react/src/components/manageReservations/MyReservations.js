import React from "react";
import styles from "../../styles/manageReservations/MyReservations.module.scss";
import ReservationTable from "./ReservationsTable";
import { useEffect, useState } from "react";
import { checkUser } from "../../utils";
import { getUserReservations } from "../../services/reservationService";
const MyReservations = () => {
  const [user, setUser] = useState("");
  const [valid, setValid] = useState(false);
  const [reservations, setReservations] = useState([]);
  const [pastReservations, setPastReservations] = useState([]);
  const [currentReservations, setCurrentReservations] = useState([]);

  useEffect(() => {
    checkUser(setUser, setValid);
  }, []);

  useEffect(() => {
    if (user !== "") getUserReservations(setReservations, user.email);
  }, [user]);

  useEffect(() => {
    if (reservations !== []) {
      setPastReservations(
        reservations.filter((res) => {
          const date = new Date(res.date);
          const currentDate = new Date();
          if (date <= currentDate) return true;
          return false;
        })
      );
      setCurrentReservations(
        reservations.filter((res) => {
          const date = new Date(res.date);
          const currentDate = new Date();
          if (date > currentDate) return true;
          return false;
        })
      );
    }
  }, [reservations]);

  return (
    <div className={styles.container}>
      <h2>Upcoming Reservations</h2>
      <ReservationTable
        reservations={currentReservations}
        isActionable={true}
        user={user}
        waitingList={false}
      />
      <h2 className={styles.pastReservations}>Past Reservations</h2>
      <ReservationTable
        reservations={pastReservations}
        user={user}
        waitingList={false}
      />
    </div>
  );
};

export default MyReservations;
