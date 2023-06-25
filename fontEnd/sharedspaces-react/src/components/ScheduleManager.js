import styles from "../styles/ScheduleManager.module.scss";
import Calendar from "./Calendar";
import AvailableSpaces from "./AvailableSpaces";
import React, { useEffect, useState } from "react";
import { getAllSpaces } from "../services/spaceService";
import { getAllResponsible } from "../services/responsibleService";
import { getAllReservation } from "../services/reservationService";

// const reservations = [
//   {
//     spaceId: 0,
//     title: "CO224 Labs",
//     date: "2023-06-16",
//     startTime: 830,
//     endTime: 1000,
//     reservedBy: "Silva A.K.M. - E/19/372",
//     responsibePerson: "Dr. Isuru Nawinne",
//     waitingList: [],
//   },

//   {
//     spaceId: 0,
//     title: "CO221 Labs",
//     date: "2023-06-15",
//     startTime: 1400,
//     endTime: 1430,
//     reservedBy: "Ubayasiri S.J. - E/19/408",
//     responsibePerson: "Dr. Isuru Nawinne",
//     waitingList: [],
//   },

//   {
//     spaceId: 0,
//     title: "CO224 Labs",
//     date: "2023-06-13",
//     startTime: 830,
//     endTime: 1000,
//     reservedBy: "Gunawardana K.H. - E/19/129A",
//     responsibePerson: "Dr. Isuru Nawinne",
//     waitingList: [],
//   },

//   {
//     spaceId: 3,
//     title: "CO225 Labs",
//     date: "2023-06-15",
//     startTime: 1640,
//     endTime: 1820,
//     reservedBy: "Silva A.K.M. - E/19/372",
//     responsibePerson: "Dr. Isuru Nawinne",
//     waitingList: [],
//   },
//   {
//     spaceId: 1,
//     title: "CO225 Labs",
//     date: "2023-06-14",
//     startTime: 900,
//     endTime: 1000,
//     reservedBy: "Silva A.K.M. - E/19/372",
//     responsibePerson: "Dr. Isuru Nawinne",
//     waitingList: [],
//   },
// ];

// const spaces = [
//   {
//     id: 0,
//     name: "Computer Lab 02",
//     description: "First Floor Computer Lab",
//   },
//   {
//     id: 1,
//     name: "Computer Lab 01",
//     description: "Fourth Floor Computer Lab",
//   },
//   {
//     id: 2,
//     name: "Discussion Room",
//     description: "Second Floor",
//   },
//   {
//     id: 3,
//     name: "Network Engineering Lab",
//     description: "First Floor Lab",
//   },
//   {
//     id: 4,
//     name: "Escal MakerSpace",
//     description: "Second Floor Lab",
//   },
// ];

const SechduleManager = () => {
  //filter reservations according to the space selected - default - 0
  const [reservations, setReservations] = useState([]);

  const [spaceReservations, setSpaceReservations] = useState([]);

  const [spaces, setSpaces] = useState([]);

  async function getSpaces() {
    const space = await getAllSpaces();
    setSpaces(space);
  }

  async function getReservation() {
    const reservation = await getAllReservation();
    setReservations(reservation);
  }

  useEffect(() => {
    console.log(reservations);
    console.log(reservations[0]);
    console.log(reservations[0].spaceID);
    console.log(
      reservations.filter((reservation) => reservation.spaceId === 1)
    );
    setSpaceReservations(
      reservations.filter((reservation) => reservation.spaceId === 1)
    );
  }, [reservations]);

  useEffect(() => {
    getSpaces();
    getReservation();
  }, []);

  //Available Spaces Selection
  const [selectSpace, setSelectSpace] = useState(0);
  const handleSpaceClick = (id) => {
    //if already selected, then show more details on the space
    if (selectSpace === id) {
      console.log("TODO: Open modal");
    } else {
      setSelectSpace(id);
      setSpaceReservations(
        reservations.filter((reservation) => reservation.spaceId === id)
      );
    }
  };

  return (
    <div className={styles.container}>
      <AvailableSpaces
        availableSpaces={spaces}
        handleClick={handleSpaceClick}
        select={selectSpace}
      />
      <Calendar
        selectSpace={selectSpace}
        spaceReservations={spaceReservations}
      />
    </div>
  );
};

export default SechduleManager;
