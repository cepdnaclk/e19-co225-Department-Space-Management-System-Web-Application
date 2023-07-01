import React from "react";
import { spaces } from "../../data";
import styles from "../../styles/manageReservations/ReservationsTable.module.scss";
import { getTimeString, getDateInFormat } from "../../utils";
import classNames from "classnames";
import { MdClose, MdCheck } from "react-icons/md";
import {
  createReservation,
  deleteUserReservatin,
} from "../../services/reservationService";
import { deleteUserWaiting } from "../../services/waitingService";
import { getAuthentincate } from "../../services/authService";
const ReservationTable = ({
  reservations,
  isActionable,
  isAcceptable,
  user,
  waitingList,
  updateReservation,
}) => {
  const handleDelete = async (reservation) => {
    if (waitingList === false) {
      await getAuthentincate(deleteUserReservatin, reservation.id)
        .then((res) => {
          //no error proceed to rerender tables with updated reservations
          updateReservation();
        })
        .catch((error) => {
          if (error.message === "email") {
            //error but an email issue, so proceed to rerender table with updated reservations
            updateReservation();
          } else {
            // other error
            console.log(error);
          }
        });
    } else {
      getAuthentincate(deleteUserWaiting, reservation.id)
        .then((res) => {})
        .catch((error) => {
          if (error.message === "email") {
          } else {
            // other error
            console.log(error);
          }
        });
    }
  };

  const handleReservation = async (reservation) => {
    await getAuthentincate(
      createReservation,
      reservation.title,
      reservation.startTime,
      reservation.endTime,
      reservation.spaceId,
      Date.now(),
      reservation.date,
      user.id,
      reservation.responsibePersonId,
      reservation.id
    )
      .then((res) => {
        // if reservation sucess
        console.log(res);
        updateReservation();
      })
      .catch((error) => {
        // if reserved
        if (error.message === "reserved") {
          console.log("reserved");
          updateReservation();
        } else if (error.message === "email") {
          updateReservation();
        } else {
          // other error
          console.log(error);
        }
      });
  };

  return (
    <div
      className={classNames(
        styles.container,
        reservations.length === 0 && styles.hide
      )}
    >
      <table className={styles.resTable}>
        <thead>
          <tr>
            <th className={styles.firstCol}>Title</th>
            <th>Date</th>
            <th>Space</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Responsible Person</th>
            {isActionable && <th className={styles.actionCol}>Actions</th>}{" "}
            {/* add a new column for actions */}
          </tr>
        </thead>
        <tbody>
          {reservations.map((reservation) => {
            const space = spaces.find((s) => s.id === reservation.spaceId);
            return (
              <tr key={reservation.id}>
                <td className={styles.firstCol}>{reservation.title}</td>
                <td>{getDateInFormat(new Date(reservation.date))}</td>
                <td>{space ? space.name : ""}</td>
                <td>{getTimeString(reservation.startTime)}</td>
                <td>{getTimeString(reservation.endTime)}</td>
                <td>{reservation.responsiblePerson}</td>
                {isActionable && (
                  <td className={styles.actionColCell}>
                    {isAcceptable && (
                      <button
                        className={classNames(styles.btn, styles.acceptBtn)}
                        onClick={() => handleReservation(reservation)}
                      >
                        <MdCheck />
                        Confirm
                      </button>
                    )}

                    <button
                      className={classNames(styles.btn, styles.cancelBtn)}
                      onClick={() => handleDelete(reservation)}
                    >
                      <MdClose />
                      Cancel
                    </button>
                  </td>
                )}
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default ReservationTable;
