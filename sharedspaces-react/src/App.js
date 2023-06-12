import "./styles/App.scss";
import Navbar from "./components/Navbar";
import Hero from "./components/Hero";
import SechduleManager from "./components/ScheduleManager";

const App = () => {
  return (
    <>
      <Navbar />
      <Hero
        title="SharedSpaces"
        description="Check Availability and Reserve Spaces"
      />
      <SechduleManager />
    </>
  );
};

export default App;
