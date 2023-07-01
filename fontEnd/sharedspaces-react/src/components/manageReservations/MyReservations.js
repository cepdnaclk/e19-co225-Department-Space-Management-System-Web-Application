import React from "react";
import styles from "../../styles/manageReservations/MyReservations.module.scss";
import ReservationTable from "./ReservationsTable";
import { useEffect, useState } from "react";
import { checkUser } from "../../utils";
import {
  getResponsibleReservations,
  getUserReservations,
} from "../../services/reservationService";
import { Role } from "../../data";
const MyReservations = () => {
  const [user, setUser] = useState("");
  const [valid, setValid] = useState(false);
  const [reservations, setReservations] = useState([]);
  const [pastReservations, setPastReservations] = useState([]);
  const [currentReservations, setCurrentReservations] = useState([]);
  const [responsibleReservations, setResponsibleReservations] = useState([]);

  const getReservation = () => {
    getUserReservations(setReservations, user.email);
  };

  useEffect(() => {
    checkUser(setUser, setValid);
  }, []);

  useEffect(() => {
    if (user !== "") {
      getReservation();
      if ((user.role = Role.RESPONSIBLE))
        getResponsibleReservations(setResponsibleReservations, user.email);
    }
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
      {currentReservations.length === 0 ? (
        <p className={styles.NoReservations}>
          You have no upcoming reservations
        </p>
      ) : (
        <ReservationTable
          reservations={currentReservations}
          isActionable={true}
          user={user}
          waitingList={false}
          updateReservation={getReservation}
        />
      )}
      {pastReservations.length !== 0 && (
        <>
          <h2 className={styles.pastReservations}>Past Reservations</h2>
          <ReservationTable
            reservations={pastReservations}
            user={user}
            waitingList={false}
          />
        </>
      )}
      {user.role === Role.RESPONSIBLE &&
        responsibleReservations.length !== 0 && (
          <>
            <h2 className={styles.pastReservations}>Responsible</h2>
            <ReservationTable
              reservations={responsibleReservations}
              user={user}
              waitingList={false}
              isActionable={true}
            />
          </>
        )}
    </div>
  );
};

export default MyReservations;
