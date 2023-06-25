import axios from "axios";
import jwt_decode from "jwt-decode";

const endPointlog = "http://localhost:8080/log";

async function getLogging(setValid, setUser, user, setToken, googleToken) {
  axios
    .post(endPointlog, {
      clientId: googleToken.clientId,
      credential: googleToken.credential,
      select_by: googleToken.select_by,
    })
    .then((response) => {
      setValid(response.data.valid);
      setUser(response.data.refreshToken);
      setToken(jwt_decode(response.data.refreshToken));
      localStorage.setItem("token", response.data.refreshToken);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

export { getLogging };
