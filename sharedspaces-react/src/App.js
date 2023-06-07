import "./styles/App.scss";
import Navbar from "./components/Navbar";
import Hero from "./components/Hero";
import SechduleManager from "./components/ScheduleManager";

const spaces = [
  {
    id: 0,
    name: "Computer Lab 02",
    description: "First Floor Computer Lab",
    capacity: 50,
    facilities: ["projector", "ac"],
    imgLink: "",
  },
  {
    id: 1,
    name: "Computer Lab 01",
    description: "Fourth Floor Computer Lab",
  },
  {
    id: 2,
    name: "Discussion Room",
    description: "Second Floor",
  },
  {
    id: 3,
    name: "Network Engineering Lab",
    description: "First Floor Lab",
  },
];
const App = () => {
  return (
    <>
      <Navbar />
      <Hero />
      <SechduleManager availableSpaces={spaces} />
    </>
  );
};

export default App;
