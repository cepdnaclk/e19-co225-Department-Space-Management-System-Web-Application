import axios from "axios";

const endPointWaiting = "http://localhost:8080/waiting";

async function getUserWaiting(setReservations, email) {
  await axios
    .get(endPointWaiting + "/user", {
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

async function getWaitingList(
  setReservations,
  spaceID,
  date,
  startTime,
  endTime
) {
  await axios
    .get(endPointWaiting + "/slot", {
      params: {
        startTime: startTime,
        endTime: endTime,
        date: date,
        spaceID: spaceID,
      },
    })
    .then((res) => {
      setReservations(res.data);
    })
    .catch((error) => {
      console.log(error.message);
      if (error.response.status === 503) {
        throw new Error("email");
      }
      throw new Error("");
    });
}

async function deleteUserWaiting(token, args) {
  await axios
    .delete(endPointWaiting + "/id", {
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

async function createWaiting(token, arrgs) {
  await axios
    .post(
      endPointWaiting,
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
      if (error.response.status === 503) {
        throw new Error("email");
      }
      throw new Error("");
    });
}

export { createWaiting, getUserWaiting, deleteUserWaiting, getWaitingList };
