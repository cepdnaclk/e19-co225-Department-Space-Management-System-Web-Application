import axios from "axios";
import jwt_decode from "jwt-decode";

const endPointAuth = "http://localhost:8080/auth/authenticate";

// await getAuthentincate(getAllReservation, setReservations);

async function getAuthentincate(result, ...args) {
  const token = localStorage.getItem("token");
  await axios
    .post(
      endPointAuth,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    )
    .then((response) => {
      return result(response.data.access_token, args).catch((error) => {
        throw error;
      });
    })
    .catch((error) => {
      if (error.message === "reserved") {
        throw new Error("reserved");
      }
      throw new Error("");
    });
}

async function getRefreshToken(setRefreshToken) {
  const token = localStorage.getItem("token");
  axios
    .post(
      endPointAuth,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    )
    .then((response) => {
      setRefreshToken(response.data.refresh_token);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

export { getAuthentincate, getRefreshToken };
