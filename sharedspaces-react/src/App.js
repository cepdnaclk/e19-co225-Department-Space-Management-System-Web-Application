import "./styles/App.scss";
import Navbar from "./components/Navbar";
import AppRouter from "./AppRouter";

const App = () => {
  return (
    <>
      <Navbar />
      <AppRouter />
    </>
  );
};

export default App;
