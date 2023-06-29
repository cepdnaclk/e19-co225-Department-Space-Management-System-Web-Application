import axios from "axios";

const endPointResponsible = "http://localhost:8080/responsible";

async function getAllResponsible(...args) {
  const [setResponsible] = args;

  await axios
    .get(endPointResponsible)
    .then((res) => {
      setResponsible(res.data);
    })
    .catch((error) => {
      console.log(error.message);
    });
}

export { getAllResponsible };
