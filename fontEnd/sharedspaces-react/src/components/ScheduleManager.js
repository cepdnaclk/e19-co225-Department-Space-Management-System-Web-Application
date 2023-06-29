import styles from "../styles/ScheduleManager.module.scss";
import Calendar from "./Calendar";
import AvailableSpaces from "./AvailableSpaces";

import React, { useEffect, useState } from "react";
import { getAllSpaces } from "../services/spaceService";
import { getAllResponsible } from "../services/responsibleService";
import { getAllReservation } from "../services/reservationService";
import { getAuthentincate } from "../services/authService";

const SechduleManager = () => {
  //filter reservations according to the space selected - default - 1
  const [reservations, setReservations] = useState([]);
  const [spaceReservations, setSpaceReservations] = useState([]);
  const [spaces, setSpaces] = useState([]);

  async function getSpaces() {
    await getAllSpaces(setSpaces);
  }

  async function getReservations() {
    await getAllReservation(setReservations);
  }

  useEffect(() => {
    if (reservations !== []) {
      setSpaceReservations(
        reservations.filter((reservation) => reservation.spaceId === 1)
      );
    }
  }, [reservations]);

  useEffect(() => {
    getSpaces();
    getReservations();
  }, []);

  //Available Spaces Selection
  const [selectSpace, setSelectSpace] = useState(1);
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
        selectSpaceName={"DUMMY"}
        spaceReservations={spaceReservations}
      />
    </div>
  );
};

export default SechduleManager;
