import React, { useEffect } from "react";
import styles from "../styles/Calendar.module.scss";

const Calendar = ({ selectSpace, spaceReservations }) => {
  //calculate the upcoming dates and pass it into the Day component
  const currentDate = new Date();

  const dateList = [];
  for (let i = 0; i < 5; i++) {
    const date = new Date();
    date.setDate(currentDate.getDate() + i);
    dateList.push(date);
  }

  //get the start time and end time and populate an array for each hour interval
  const startTime = 8;
  const endTime = 18;
  const hourIntervals = Array.from(
    { length: endTime - startTime },
    (_, index) => index + startTime
  );

  return (
    <div className={styles.container}>
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
  );
};

export default Calendar;

const Day = ({ dateObj, hourIntervals, dayReservations }) => {
  const dayName = new Intl.DateTimeFormat("en-US", { weekday: "short" }).format(
    dateObj
  );

  const bgcolor = "hsl(0, 77%, 76%)";
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
        console.log(reservation.startTime);
        return (
          <button
            key={reservation.startTime}
            className={styles.reservation}
            style={{
              height: `${(minutes / 60) * 100}%`,
              top: `${((reservation.startTime % 100) / 60) * 100}%`,
            }}
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
      <div className={styles.controller}></div>
      {hourStrings.map((hour) => (
        <div className={styles.hour} key={hour}>
          {hour}
        </div>
      ))}
    </div>
  );
};
// const getHourlyIncrements = (start, end) => {
//   const startDate = new Date(`2000-01-01T${start}:00`);
//   const endDate = new Date(`2000-01-01T${end}:00`);
//   const hourIncrements = [];

//   for (let d = startDate; d <= endDate; d.setHours(d.getHours() + 1)) {
//     let timeString = new Intl.DateTimeFormat("en-US", {
//       hour: "numeric",
//       minute: "numeric",
//     }).format(new Date(d));
//     hourIncrements.push(timeString);
//   }

//   return hourIncrements;
// };
