import Hero from "./components/Hero";
import SechduleManager from "./components/ScheduleManager";

const HomePage = () => {
  return (
    <div>
      <Hero
        title="SharedSpaces"
        description="Check Availability and Reserve Spaces"
      />
      <SechduleManager />
    </div>
  );
};

export default HomePage;
