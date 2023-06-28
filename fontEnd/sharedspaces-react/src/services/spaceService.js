import axios from "axios";

const endPointSpace = "http://localhost:8080/space";

async function getAllSpaces(...args) {
  const [setSpace] = args;
  let data = [];

  console.log(100);
  await axios
    .get(endPointSpace)
    .then((res) => {
      data = res.data;
      setSpace(res.data);

    })
    .catch((error) => {
      console.log(error.message);
    });

  return data;
}

export { getAllSpaces };
