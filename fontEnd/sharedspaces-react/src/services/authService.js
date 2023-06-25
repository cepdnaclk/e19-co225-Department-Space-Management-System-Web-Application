import axios from "axios";
import jwt_decode from "jwt-decode";

const endPointAuth = "http://localhost:8080/auth/authenticate";

async function getAuthentincate(setAccessToken) {
  const token = localStorage.getItem("token");
  console.log(token);
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
      setAccessToken(response.data.access_token);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

async function getRefreshToken(setRefreshToken) {
  const token = localStorage.getItem("token");
  console.log(token);
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
