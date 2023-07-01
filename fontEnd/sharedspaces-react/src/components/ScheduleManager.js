import styles from "../styles/ScheduleManager.module.scss";
import Calendar from "./Calendar";
import AvailableSpaces from "./AvailableSpaces";

import React, { useEffect, useState } from "react";
import { getAllSpaces } from "../services/spaceService";
import { getAllResponsible } from "../services/responsibleService";
import { getAllReservation } from "../services/reservationService";
import { getAuthentincate } from "../services/authService";

const SechduleManager = ({
  selectedDays,
  startTime,
  endTime,
  capacity,
  selectedFacilities,
}) => {
  //filter reservations according to the space selected - default - 1
  const [reservations, setReservations] = useState([]);
  const [spaceReservations, setSpaceReservations] = useState([]);
  const [allSpaces, setAllSpaces] = useState([]);
  const [filteredSpaces, setFilteredSpaces] = useState([]);

  async function getSpaces() {
    await getAllSpaces(setAllSpaces);
  }

  async function getReservations() {
    await getAllReservation(setReservations);
  }

  useEffect(() => {
    if (reservations.length !== 0) {
      setSpaceReservations(
        reservations.filter((reservation) => reservation.spaceId === 1)
      );
    }
  }, [reservations]);

  //whenever the capacity change, change the displayed spaces using the filteredSpaces state
  useEffect(() => {
    if (allSpaces.length !== 0) {
      setFilteredSpaces(
        allSpaces.filter(
          (space) =>
            space.capacity <= capacity[1] && space.capacity >= capacity[0]
        )
      );
    }
  }, [allSpaces, capacity]);

  //This is for special cases, if the already selected space if filtered out
  //or if there are no matching spaces available
  useEffect(() => {
    if (filteredSpaces.length !== 0) {
      setSelectSpace(filteredSpaces[0].id);
      setSelectSpaceName(filteredSpaces[0].name);
      setSpaceReservations(
        reservations.filter(
          (reservation) => reservation.spaceId === selectSpace
        )
      );
    } else if (filteredSpaces.length === 0) {
      setSpaceReservations([]);
    }
  }, [filteredSpaces]);

  //initially fetching the data
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

  //pass down the space name to the addEvent prop
  const [selectSpaceName, setSelectSpaceName] = useState(" ");
  useEffect(() => {
    try {
      setSelectSpaceName(allSpaces.find((s) => s.id === selectSpace).name);
      setSpaceReservations(
        reservations.filter(
          (reservation) => reservation.spaceId === selectSpace
        )
      );
    } catch (error) {
      //pass
      //When it first renders, there won't be any spaces.
      //so spaces.find will return nothing.
    }
  }, [selectSpace]);

  return (
    <div className={styles.container}>
      <AvailableSpaces
        availableSpaces={filteredSpaces}
        handleClick={handleSpaceClick}
        select={selectSpace}
      />
      <Calendar
        getReservations={getReservations}
        selectSpace={selectSpace}
        selectSpaceName={selectSpaceName}
        spaceReservations={spaceReservations}
        selectedDays={selectedDays}
        startTime={startTime}
        endTime={endTime}
        updateReservations={getReservations}
      />
    </div>
  );
};

export default SechduleManager;
