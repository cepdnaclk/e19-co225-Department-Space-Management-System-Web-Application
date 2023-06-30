import styles from "../styles/AddEvent.module.scss";
import { FiMapPin, FiCheck, FiCheckCircle } from "react-icons/fi";
import { LuCalendarDays } from "react-icons/lu";
import { FaRegClock, FaPlus } from "react-icons/fa";
import { MdPlaylistAddCheckCircle } from "react-icons/md";
import {
  getDateInFormat,
  getTimeString,
  setTimeFormat,
  mapTimeStringToInteger,
  checkUser,
} from "../utils";
import Select from "react-select";
import classNames from "classnames";
import { useEffect, useState } from "react";
import { getAllResponsible } from "../services/responsibleService";
import { reservations } from "../data";
import { createReservation } from "../services/reservationService";
import { createWaiting } from "../services/waitingService";

const groupedOptions = [
  {
    label: "Lecturers",
  },
  {
    label: "Instructors",
  },
];

const AddEvent = ({
  startTimeProp,
  endTimeProp,
  spaceId,
  date,
  spaceReservations,
  spaceName,
}) => {
  const [startTime, setStartTime] = useState(getTimeString(startTimeProp));
  const [endTime, setEndTime] = useState(getTimeString(endTimeProp));
  const [responsible, setResponsible] = useState([]);
  const [isClash, setClash] = useState(true);
  const [user, setUser] = useState("");
  const [valid, setValid] = useState(false);
  const [title, setTitle] = useState("");
  const [responsibleId, setResponsibleId] = useState(0);

  useEffect(() => {
    getResponsible();
  }, []);

  useEffect(() => {
    if (responsible != []) mapResponsible();
  }, [responsible]);

  useEffect(() => {
    setStartTime(getTimeString(startTimeProp));
    setEndTime(getTimeString(endTimeProp));
  }, [startTimeProp, endTimeProp]);

  useEffect(() => {
    checkUser(setUser, setValid);
    setShowFeedbackSuccess(false);
    setShowFeedbackWaiting(false);
  }, [startTimeProp, endTimeProp, spaceId, date]);

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

  async function getResponsible() {
    await getAllResponsible(setResponsible);
  }

  const handleStartTimeChange = (event) => {
    setStartTime(event.target.value);
    if (mapTimeStringToInteger(event.target.value)) {
      validateReservation(spaceReservations);
    }
  };

  const handleEndTimeChange = (event) => {
    setEndTime(event.target.value);
    if (mapTimeStringToInteger(event.target.value)) {
      validateReservation(spaceReservations);
    }
  };

  const handleChangeTitle = (event) => {
    setTitle(event.target.value);
  };

  const validateReservation = (spaceReservations) => {
    const startTimeFormatted = setTimeFormat(startTime);
    const endTimeFormatted = setTimeFormat(endTime);

    if (startTimeFormatted > endTimeFormatted) {
      console.log("Please enter a valid End Time");
    } else {
      checkAvailablity(startTimeFormatted, endTimeFormatted, spaceReservations);
    }
  };

  const checkAvailablity = (
    startTimeFormatted,
    endTimeFormatted,
    spaceReservations
  ) => {
    const dayReservations = spaceReservations.filter(
      (reservation) => reservation.date === date
    );
    if (
      dayReservations.filter(
        (reservation) =>
          (reservation.startTime > startTimeFormatted &&
            reservation.startTime < endTimeFormatted) ||
          (reservation.endTime > startTimeFormatted &&
            reservation.endTime < endTimeFormatted)
      ).length === 0
    ) {
      console.log("Slot is available");
      setClash(false);
    } else {
      console.log("Slot is not available");
      setClash(true);
    }
  };

  //hadnling submit click, on submit click show feedback
  const [showFeedbackSuccess, setShowFeedbackSuccess] = useState(false);
  const handleSubmit = (e) => {
    e.preventDefault();

    var reservationDate = new Date(date);
    reservationDate.setDate(reservationDate.getDate() + 1);

    createReservation(
      "",
      title,
      setTimeFormat(startTime),
      setTimeFormat(endTime),
      spaceId,
      Date.now(),
      reservationDate,
      user.id,
      responsibleId,
      -1
    )
      .then((res) => {
        // if reservation sucess
        setShowFeedbackSuccess(true);
      })
      .catch((error) => {
        // if reserved
        if (error.message === "reserved") {
          console.log("reserved");
        }
      });
  };

  //handling submit waiting list click, on submit show feedback
  const [showFeedbackWaiting, setShowFeedbackWaiting] = useState(false);
  const handleWaiting = (e) => {
    e.preventDefault();

    console.log(date);
    var reservationDate = new Date(date);
    console.log(reservationDate);
    reservationDate.setDate(reservationDate.getDate() + 1);

    createWaiting(
      "",
      title,
      setTimeFormat(startTime),
      setTimeFormat(endTime),
      spaceId,
      Date.now(),
      reservationDate,
      user.id,
      responsibleId,
      -1
    )
      .then((res) => {
        // if waiting success
        setShowFeedbackWaiting(true);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className={styles.addEvent}>
      <form>
        <input
          type="text"
          placeholder="Add Title"
          className={styles.inputTitle}
          maxLength={25}
          onChange={handleChangeTitle}
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

        <ResponsibleSelect setResponsibleId={setResponsibleId} />
        {isClash ? (
          <button
            type="submit"
            className={classNames(styles.submitBtn, styles.addWaitingListBtn)}
            onClick={handleWaiting}
          >
            <FaPlus />
            Add to Waiting List
          </button>
        ) : (
          <button
            type="submit"
            className={classNames(styles.submitBtn, styles.confirmBtn)}
            onClick={handleSubmit}
          >
            <FiCheck className={styles.checkIcon} />
            Confirm Reservation
          </button>
        )}
      </form>

      <div
        className={classNames(
          styles.feedbackContainer,
          showFeedbackSuccess && styles.show
        )}
      >
        <FiCheckCircle className={styles.feedbackIcon} />
        <p>Reservation Added Successfully!</p>
      </div>

      <div
        className={classNames(
          styles.feedbackContainer,
          styles.feedbackWaiting,
          showFeedbackWaiting && styles.show
        )}
      >
        <MdPlaylistAddCheckCircle className={styles.feedbackIcon} />
        <p>Added to Waiting List!</p>
      </div>
    </div>
  );
};

export default AddEvent;

const ResponsibleSelect = ({ setResponsibleId }) => (
  <Select
    placeholder="Select a reponsible person"
    options={groupedOptions}
    onChange={(choice) => setResponsibleId(choice.value)}
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
