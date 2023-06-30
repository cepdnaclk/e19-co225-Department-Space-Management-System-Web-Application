import * as React from "react";
import "./styles/App.scss";
import Navbar from "./components/Navbar";
import AppRouter from "./components/AppRouter";

const App = () => {
  const [user, setUser] = React.useState("");
  const [valid, setValid] = React.useState(false);

  return (
    <>
      <Navbar user={user} setUser={setUser} valid={valid} setValid={setValid} />
      <AppRouter />
    </>
  );
};

export default App;
