import styles from "../styles/AddEvent.module.scss";
import { FiMapPin, FiCheck } from "react-icons/fi";
import { LuCalendarDays } from "react-icons/lu";
import { FaRegClock } from "react-icons/fa";
import { spaces } from "../data";
import { getDateInFormat, getTimeString } from "../utils";
import Select from "react-select";
import classNames from "classnames";
import { useEffect, useState } from "react";

const groupedOptions = [
  {
    label: "Lecturers",
    options: [
      { value: "john", label: "John" },
      { value: "sarah", label: "Sarah" },
      { value: "peter", label: "Peter" },
    ],
  },
  {
    label: "Instructors",
    options: [
      { value: "bob", label: "Bob" },
      { value: "alice", label: "Alice" },
      { value: "jane", label: "Jane" },
    ],
  },
];

const AddEvent = ({ hour, spaceId, date }) => {
  const [startTime, setStartTime] = useState(getTimeString(hour * 100));
  const [endTime, setEndTime] = useState(getTimeString(hour * 100 + 40));

  useEffect(() => {
    setStartTime(getTimeString(hour * 100));
    setEndTime(getTimeString(hour * 100 + 40));
  }, [hour]);

  const handleStartTimeChange = (event) => {
    setStartTime(event.target.value);
  };

  const handleEndTimeChange = (event) => {
    setEndTime(event.target.value);
  };

  const spaceName = spaces.find((s) => s.id === spaceId).name;
  return (
    <div className={styles.addEvent}>
      <form>
        <input
          type="text"
          placeholder="Add Title"
          className={styles.inputTitle}
          maxLength={25}
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
              value={startTime}
              onChange={handleStartTimeChange}
              className={styles.time}
            />{" "}
            -
            <input
              type="text"
              value={endTime}
              onChange={handleEndTimeChange}
              className={styles.time}
            />
          </p>
        </div>
        <p className={styles.pResPerson}>Responsible Person</p>

        <ResponsibleSelect />
        <button type="submit" className={styles.confirmBtn}>
          <FiCheck className={styles.checkIcon} />
          Confirm Reservation
        </button>
      </form>
    </div>
  );
};

export default AddEvent;

const ResponsibleSelect = () => (
  <Select
    placeholder="Select a reponsible person"
    options={groupedOptions}
    classNames={{
      container: () => styles.selectContainer,
      control: (state) =>
        classNames(
          styles.selectControl,
          state.isFocused && styles.selectControlFocused
        ),
      option: (state) => classNames(styles.selectOption),
      placeholder: (state) => classNames(styles.selectPlaceholder),
      input: (state) =>
        classNames(
          styles.selectInput,
          state.isFocused && styles.selectInputFocused
        ),
      menu: (state) => classNames(styles.selectMenu),
      valueContainer: (state) => styles.selectValueContainer,
    }}
  />
);
