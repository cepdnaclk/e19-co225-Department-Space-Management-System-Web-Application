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

async function deleteUserReservatin(token, args) {
  await axios
    .delete(endPointReservation, {
      params: {
        id: args[0],
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
      if (error.response.status === 503) {
        throw new Error("email");
      }
      throw new Error("");
    });
}

async function createReservation(token, arrgs) {
  console.log(arrgs);
  await axios
    .post(
      endPointReservation,
      {
        spaceID: arrgs[3],
        title: arrgs[0],
        reservationDateTime: arrgs[4],
        startTime: arrgs[1],
        endTime: arrgs[2],
        date: arrgs[5],
        reservedBy: arrgs[6],
        responsiblePerson: arrgs[7],
        waitingId: arrgs[8],
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
      } else if (error.response.status === 503) {
        throw new Error("email");
      }
      throw new Error("");
    });
}

export {
  getAllReservation,
  createReservation,
  getUserReservations,
  deleteUserReservatin,
  getResponsibleReservations,
};
