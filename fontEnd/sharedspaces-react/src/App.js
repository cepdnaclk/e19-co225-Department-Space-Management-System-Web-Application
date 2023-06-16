import * as React from "react";
import "./styles/App.scss";
import Navbar from "./components/Navbar";
import AppRouter from "./components/AppRouter";

const App = () => {
  return (
    <>
      <Navbar />
      <AppRouter />
    </>
  );
};

export default App;
