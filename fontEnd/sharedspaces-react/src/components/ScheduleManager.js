import styles from "../styles/ScheduleManager.module.scss";
import Calendar from "./Calendar";
import AvailableSpaces from "./AvailableSpaces";
import React, { useState } from "react";
import { spaces, reservations } from "../data";
const SechduleManager = () => {
  //filter reservations according to the space selected - default - 1
  const [spaceReservations, setSpaceReservations] = useState(
    reservations.filter((reservation) => reservation.spaceId === 1)
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
