import axios from "axios";

const endPointResponsible = "http://localhost:8080/responsible";

async function getAllResponsible() {
  let data = [];

  await axios
    .get(endPointResponsible)
    .then((res) => {
      data = res.data;
    })
    .catch((error) => {
      console.log(error.message);
    });

  return data;
}

export { getAllResponsible };
