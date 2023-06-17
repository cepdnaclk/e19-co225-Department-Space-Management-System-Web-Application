import React from "react";
import styles from "../styles/ReservationInfo.module.scss";
import { FiMapPin } from "react-icons/fi";
import { LuCalendarDays } from "react-icons/lu";
import { FaRegClock } from "react-icons/fa";
import { getDateInFormat, getTimeString, generateColorCode } from "../utils";
import { spaces } from "../data";
const ReservationInfo = ({ reservation }) => {
  const spaceName = spaces.find((s) => s.id === reservation.spaceId).name;
  return (
    <div className={styles.container}>
      <div
        className={styles.infoTop}
        style={{
          backgroundColor: `${generateColorCode(reservation.reservedBy[0])}`,
        }}
      >
        <h4 className={styles.title}>{reservation.title}</h4>

        <div className={styles.info}>
          <p className={styles.infoItem}>
            <FiMapPin />
            {spaceName}
          </p>
          <p className={styles.infoItem}>
            <LuCalendarDays />
            {getDateInFormat(new Date(reservation.date))}
          </p>
          <p className={styles.infoItem}>
            <FaRegClock />
            {`${getTimeString(reservation.startTime)} - ${getTimeString(
              reservation.endTime
            )}`}
          </p>
        </div>
      </div>
    </div>
  );
};

export default ReservationInfo;
