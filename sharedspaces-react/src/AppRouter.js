import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import Manage from "./Manage";
import HomePage from "./HomePage";

const AppRouter=() =>{
    return(
        <BrowserRouter>
            <Routes>
                <Route path= "/" element={ <HomePage />}/>
                <Route path= "/ManageReservations" element={ <Manage />}/>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRouter;