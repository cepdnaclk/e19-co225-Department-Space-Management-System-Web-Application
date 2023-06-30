import axios from "axios";

const endPointReservation = "http://localhost:8080/reservation";

async function getAllReservation(setReservations) {
  await axios
    .get(endPointReservation)
    .then((res) => {
      setReservations(res.data);
    })
    .catch((error) => {
      console.log(error.message);
    });
}

async function createReservation(
  token,
  title,
  startTime,
  endTime,
  spaceID,
  reservationDateTime,
  date,
  reservedBy,
  responsiblePerson,
  waitingId
) {
  console.log(
    token,
    title,
    startTime,
    endTime,
    spaceID,
    reservationDateTime,
    date,
    reservedBy,
    responsiblePerson,
    waitingId
  );
  await axios
    .post(
      endPointReservation,
      {
        spaceID: spaceID,
        title: title,
        reservationDateTime: reservationDateTime,
        startTime: startTime,
        endTime: endTime,
        date: date,
        reservedBy: reservedBy,
        responsiblePerson: responsiblePerson,
        waitingId: waitingId,
      },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    )
    .then((res) => {
      console.log(res);
    })
    .catch((error) => {
      console.log(error.message);
      if (error.response.status === 401) {
        throw new Error("reserved");
      }
    });
}

export { getAllReservation, createReservation };
