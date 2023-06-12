import styles from "../styles/ScheduleManager.module.scss";
import Calendar from "./Calendar";
import AvailableSpaces from "./AvailableSpaces";
import React, { useState } from "react";

const reservations = [
  {
    spaceId: 0,
    title: "CO224 Labs",
    date: "2023-06-11",
    startTime: 830,
    endTime: 1000,
    reservedBy: "Silva A.K.M. - E/19/372",
    responsibePerson: "Dr. Isuru Nawinne",
    waitingList: [],
  },

  {
    spaceId: 0,
    title: "CO221 Labs",
    date: "2023-06-16",
    startTime: 1400,
    endTime: 1430,
    reservedBy: "Silva A.K.M. - E/19/372",
    responsibePerson: "Dr. Isuru Nawinne",
    waitingList: [],
  },

  {
    spaceId: 0,
    title: "CO224 Labs",
    date: "2023-06-13",
    startTime: 830,
    endTime: 1000,
    reservedBy: "Silva A.K.M. - E/19/372",
    responsibePerson: "Dr. Isuru Nawinne",
    waitingList: [],
  },

  {
    spaceId: 3,
    title: "CO225 Labs",
    date: "2023-06-12",
    startTime: 940,
    endTime: 1020,
    reservedBy: "Silva A.K.M. - E/19/372",
    responsibePerson: "Dr. Isuru Nawinne",
    waitingList: [],
  },
  {
    spaceId: 1,
    title: "CO225 Labs",
    date: "2023-06-12",
    startTime: 900,
    endTime: 1000,
    reservedBy: "Silva A.K.M. - E/19/372",
    responsibePerson: "Dr. Isuru Nawinne",
    waitingList: [],
  },
];

const spaces = [
  {
    id: 0,
    name: "Computer Lab 02",
    description: "First Floor Computer Lab",
  },
  {
    id: 1,
    name: "Computer Lab 01",
    description: "Fourth Floor Computer Lab",
  },
  {
    id: 2,
    name: "Discussion Room",
    description: "Second Floor",
  },
  {
    id: 3,
    name: "Network Engineering Lab",
    description: "First Floor Lab",
  },
];

const SechduleManager = () => {
  //filter reservations according to the space selected - default - 0
  const [spaceReservations, setSpaceReservations] = useState(
    reservations.filter((reservation) => reservation.spaceId === 0)
  );

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
