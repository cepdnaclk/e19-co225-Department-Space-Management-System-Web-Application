import styles from "../styles/AddEvent.module.scss";
import { FiMapPin } from "react-icons/fi";
import { LuCalendarDays } from "react-icons/lu";
import { FaRegClock } from "react-icons/fa";
import { spaces } from "../data";
import { getDateInFormat } from "../utils";

const AddEvent = ({ hour, spaceId, date }) => {
  const spaceName = spaces.find((s) => s.id === spaceId).name;
  return (
    <div className={styles.addEvent}>
      <form>
        <input
          type="text"
          placeholder="Add Title"
          className={styles.inputTitle}
        />
        <div className={styles.info}>
          <p className={styles.infoItem}>
            <FiMapPin />
            {spaceName}
          </p>
          <p className={styles.infoItem}>
            <LuCalendarDays />
            {getDateInFormat(date)}
          </p>
          <p className={styles.infoItem}>
            <FaRegClock />
            <input
              type="text"
              placeholder="10:00AM"
              className={styles.time}
            />{" "}
            -
            <input type="text" placeholder="10:40AM" className={styles.time} />
          </p>
        </div>
        <p className={styles.pResPerson}>Responsible Person</p>
        <input
          type="text"
          placeholder="Add a lecturer or an instructor"
          className={styles.inputResPerson}
        />
        <button type="submit" className={styles.confirmBtn}>
          Confirm Reservation
        </button>
      </form>
    </div>
  );
};

export default AddEvent;
