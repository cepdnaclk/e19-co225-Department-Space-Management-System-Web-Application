import React from "react";
import { spaces } from "../../data";
import styles from "../../styles/manageReservations/ReservationsTable.module.scss";
import { getTimeString, getDateInFormat } from "../../utils";
import classNames from "classnames";
import { MdClose, MdCheck } from "react-icons/md";
import { deleteUserReservatin } from "../../services/reservationService";
import { deleteUserWaiting } from "../../services/waitingService";
function ReservationTable({
  reservations,
  isActionable,
  isAcceptable,
  user,
  waitingList,
}) {
  function handleDelete(reservation) {
    console.log(user.email);
    if (waitingList === false) {
      deleteUserReservatin(localStorage.getItem("token"), reservation.id);
    } else {
      deleteUserWaiting(localStorage.getItem("token"), reservation.id);
    }
  }

  function handleReservation(reservation) {}

  return (
    <div className={styles.container}>
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
                <td>{reservation.responsibePerson}</td>
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
}

export default ReservationTable;
