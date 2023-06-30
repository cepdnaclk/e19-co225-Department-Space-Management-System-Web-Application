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
      result(response.data.access_token, args).catch((error) => {
        if (error.message === "reserved") {
          console.log(1000000000);
          throw new Error("reserved");
        }
      });
    })
    .catch((error) => {
      console.log(99999);
      console.error("Error fetching data:", error);
      throw new Error("auth");
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
