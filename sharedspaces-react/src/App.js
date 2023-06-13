import "./styles/App.scss";
import Navbar from "./components/Navbar";
import AppRouter from "./AppRouter";
import { Router } from "react-router-dom";

const App = () => {
  return (
    <>
      <Navbar />
      <Router>
        <AppRouter/>
      </Router>
    </>
  );
};

export default App;
