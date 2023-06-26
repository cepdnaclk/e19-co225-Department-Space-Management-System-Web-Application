import axios from "axios";
import { useState } from "react";
import { getAuthentincate } from "./authService";

const endPointReservation = "http://localhost:8080/reservation";

async function getAllReservation(...args) {
  const [setReservations] = args;

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
