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

async function getUserReservations(setReservations, email) {
  await axios
    .get(endPointReservation + "/user", {
      params: {
        email: email,
      },
    })
    .then((res) => {
      setReservations(res.data);
    })
    .catch((error) => {
      console.log(error.message);
    });
}

async function getResponsibleReservations(setReservations, email) {
  await axios
    .get(endPointReservation + "/responsible", {
      params: {
        email: email,
      },
    })
    .then((res) => {
      setReservations(res.data);
    })
    .catch((error) => {
      console.log(error.message);
    });
}

async function deleteUserReservatin(token, id) {
  await axios
    .delete(endPointReservation + "/id", {
      params: {
        id: id,
      },
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then((res) => {
      console.log(res);
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

export {
  getAllReservation,
  createReservation,
  getUserReservations,
  deleteUserReservatin,
  getResponsibleReservations,
};
