import React, { useState } from "react";
import styles from "../styles/Calendar.module.scss";
import { FaChevronLeft, FaChevronRight } from "react-icons/fa";

const generateColorCode = (letter) => {
  const letters = "abcdefghijklmnopqrstuvwxyz";
  const index = letters.indexOf(letter.toLowerCase());
  if (index < 0) {
    throw new Error("Invalid letter");
  }
  const hue = (index * 13.846) % 360; // Generate hues based on the position of the letter in the alphabet
  return `hsl(${hue}, 35%, 60%)`; // Use HSL colors to generate vibrant and unique colors
};

const Calendar = ({ selectSpace, spaceReservations }) => {
  //calculate the upcoming dates and pass it into the Day component
  const [firstDate, setFirstDate] = useState(new Date());
  let dateList = [];
  const selectedDays = [1, 2, 3, 4, 5];

  const firstMonday = new Date(
    new Date(firstDate).setDate(firstDate.getDate() - firstDate.getDay() + 1)
  );
  for (let i = 0; dateList.length < 5; i++) {
    const date = new Date(firstDate);

    if (selectedDays.length > 5) date.setDate(firstDate.getDate() + i);
    else date.setDate(firstMonday.getDate() + i);

    if (selectedDays.includes(date.getDay())) dateList.push(date);
  }

  const handleRightClick = () => {
    const newDate = new Date(firstDate);
    if (selectedDays.length > 5) newDate.setDate(newDate.getDate() + 5);
    else newDate.setDate(newDate.getDate() + 7);

    if (Math.round((newDate - new Date()) / 86400000) < 30)
      setFirstDate(newDate);
  };

  const handleLeftClick = () => {
    const newDate = new Date(firstDate);
    newDate.setDate(newDate.getDate() - 5);
    if (Math.round((new Date() - newDate) / 86400000) < 30)
      setFirstDate(newDate);
  };
  //get the start time and end time and populate an array for each hour interval
  const startTime = 8;
  const endTime = 18;
  const hourIntervals = Array.from(
    { length: endTime - startTime },
    (_, index) => index + startTime
  );

  return (
    <div className={styles.container}>
      <div className={styles.controller}>
        <button className={styles.icon} onClick={handleLeftClick}>
          <FaChevronLeft />
        </button>
        <button className={styles.icon} onClick={handleRightClick}>
          <FaChevronRight />
        </button>
      </div>
      <div className={styles.calendar}>
        {dateList.map((date) => (
          <Day
            key={date}
            dateObj={date}
            hourIntervals={hourIntervals}
            dayReservations={spaceReservations.filter(
              (reservation) =>
                reservation.date ===
                date.toLocaleDateString("sv-SE", { timeZone: "Asia/Colombo" })
            )}
          />
        ))}
        <TimeColumn hours={hourIntervals} />
      </div>
    </div>
  );
};

export default Calendar;

const Day = ({ dateObj, hourIntervals, dayReservations }) => {
  const dayName = new Intl.DateTimeFormat("en-US", { weekday: "short" }).format(
    dateObj
  );

  return (
    <div className={styles.day}>
      <div className={styles.date}>
        <h4>{dateObj.getDate()}</h4>
        <p>{dayName.toUpperCase()}</p>
      </div>
      {hourIntervals.map((hour) => (
        <Slot
          key={`${dateObj.getDate()}-${hour}`}
          slotReservations={dayReservations.filter(
            (reservation) => Math.floor(reservation.startTime / 100) === hour
          )}
        />
      ))}
    </div>
  );
};

const Slot = ({ slotReservations }) => {
  return (
    //TODO: Add Tab Navigation -- Conflict of erronous clicks

    <div className={styles.slot}>
      {slotReservations.map((reservation) => {
        const minutes =
          (Math.floor(reservation.endTime / 100) -
            Math.floor(reservation.startTime / 100)) *
            60 +
          ((reservation.endTime % 100) - (reservation.startTime % 100));
        return (
          <button
            key={reservation.startTime}
            className={styles.reservation}
            style={{
              height: `${(minutes / 60) * 100}%`,
              top: `${((reservation.startTime % 100) / 60) * 100}%`,
              backgroundColor: `${generateColorCode(
                reservation.reservedBy[0]
              )}`,
            }}
            onClick={(e) => console.log(reservation, e)}
          >
            {reservation.title}
          </button>
        );
      })}
    </div>
  );
};

const TimeColumn = ({ hours }) => {
  const hourStrings = hours.map((hour) => {
    return new Intl.DateTimeFormat("en-US", {
      hour: "numeric",
      minute: "numeric",
    }).format(new Date(`2000-01-01T${hour.toString().padStart(2, "0")}:00`));
  });

  return (
    <div className={styles.timeCol}>
      {hourStrings.map((hour) => (
        <div className={styles.hour} key={hour}>
          {hour}
        </div>
      ))}
    </div>
  );
};
