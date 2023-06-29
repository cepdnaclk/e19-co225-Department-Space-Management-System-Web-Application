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

export { getAllReservation };
