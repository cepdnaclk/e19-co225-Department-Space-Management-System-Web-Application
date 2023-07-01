import React, { useState } from "react";
import styles from "../styles/ReservationInfo.module.scss";
import { FiMapPin } from "react-icons/fi";
import { LuCalendarDays } from "react-icons/lu";
import { FaRegClock, FaPlus } from "react-icons/fa";
import { getDateInFormat, getTimeString, generateColorCode } from "../utils";
import { spaces } from "../data";
import { getWaitingList } from "../services/waitingService";
const ReservationInfo = ({ reservation, onClick }) => {
  const spaceName = spaces.find((s) => s.id === reservation.spaceId).name;

  const [waitingList, setWaitingList] = useState([]);

  useEffect(() => {
    if (reservation !== null)
      getWaitingList(
        setWaitingList,
        reservation.id,
        reservation.date,
        reservation.startTime,
        reservation.endTime
      )
        .then((res) => {})
        .catch((error) => {
          console.log(error);
        });
  }, [reservation, onClick]);

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

      <div className={styles.infoBottom}>
        <p className={styles.infoText}>Reserved By</p>
        <p className={styles.person}>{reservation.reservedBy}</p>
        <p className={styles.infoText + " " + styles.resPerson}>
          Responsible Person
        </p>
        <p className={styles.person}>{reservation.responsiblePerson}</p>
        <div className={styles.waitingList}>
          <button className={styles.waitingListBtn} onClick={onClick}>
            <FaPlus className={styles.plusIcon} />
            Add to Waiting List
          </button>
          <p className={styles.NumberWaiting}>{waitingList.length} Waiting</p>
        </div>
      </div>
    </div>
  );
};

export default ReservationInfo;
