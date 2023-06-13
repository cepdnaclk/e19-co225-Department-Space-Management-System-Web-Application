import { Routes, Route } from "react-router-dom";
import Manage from "../Manage";
import HomePage from "../HomePage";

const AppRouter = () => {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/ManageReservations" element={<Manage />} />
    </Routes>
  );
};

export default AppRouter;
