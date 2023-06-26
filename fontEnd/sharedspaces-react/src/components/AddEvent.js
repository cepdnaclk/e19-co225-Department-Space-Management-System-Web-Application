import styles from "../styles/AddEvent.module.scss";
import { FiMapPin, FiCheck } from "react-icons/fi";
import { LuCalendarDays } from "react-icons/lu";
import { FaRegClock, FaPlus } from "react-icons/fa";
import { spaces } from "../data";
import { getDateInFormat, getTimeString } from "../utils";
import Select from "react-select";
import classNames from "classnames";
import { useEffect, useState } from "react";
import { getAllResponsible } from "../services/responsibleService";

const groupedOptions = [
  {
    label: "Lecturers",
  },
  {
    label: "Instructors",
  },
];

const AddEvent = ({ startTimeProp, endTimeProp, spaceId, date }) => {
  const [startTime, setStartTime] = useState(getTimeString(startTimeProp));
  const [endTime, setEndTime] = useState(getTimeString(endTimeProp));
  const [responsible, setResponsible] = useState([]);

  function mapResponsible() {
    groupedOptions[0].options = responsible
      .filter((res) => res.type != "instructor")
      .map((res) => {
        const val = {};
        val.value = res.id;
        val.label = res.type + " " + res.fullName;
        return val;
      });
    groupedOptions[1].options = responsible
      .filter((res) => res.type == "instructor")
      .map((res) => {
        const val = {};
        val.value = res.id;
        val.label = res.type + " " + res.fullName;
        return val;
      });
  }

  useEffect(() => {
    if (responsible != []) mapResponsible();
  }, [responsible]);

  async function getResponsible() {
    await getAllResponsible(setResponsible);
  }

  useEffect(() => {
    getResponsible();
  }, []);

  useEffect(() => {
    setStartTime(getTimeString(startTimeProp));
    setEndTime(getTimeString(endTimeProp));
  }, [startTimeProp, endTimeProp]);

  const handleStartTimeChange = (event) => {
    setStartTime(event.target.value);
  };

  const handleEndTimeChange = (event) => {
    setEndTime(event.target.value);
  };

  const isClash = true;
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
        {isClash ? (
          <button
            type="submit"
            className={classNames(styles.submitBtn, styles.addWaitingListBtn)}
          >
            <FaPlus />
            Add to Waiting List
          </button>
        ) : (
          <button
            type="submit"
            className={classNames(styles.submitBtn, styles.confirmBtn)}
          >
            <FiCheck className={styles.checkIcon} />
            Confirm Reservation
          </button>
        )}
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
