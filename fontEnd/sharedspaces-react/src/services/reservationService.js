import axios from "axios";

const endPointReservation = "http://localhost:8080/reservation";

async function getAllReservation() {
  let data = [];

  await axios
    .get(endPointReservation)
    .then((res) => {
      data = res.data;
    })
    .catch((error) => {
      console.log(error.message);
    });

  return data;
}

export { getAllReservation };
