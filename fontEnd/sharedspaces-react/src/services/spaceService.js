import axios from "axios";

const endPointSpace = "http://localhost:8080/space";

async function getAllSpaces(setSpaces) {
  console.log(100);
  await axios
    .get(endPointSpace)
    .then((res) => {
      setSpaces(res.data);
    })
    .catch((error) => {
      console.log(error.message);
    });
}

export { getAllSpaces };
