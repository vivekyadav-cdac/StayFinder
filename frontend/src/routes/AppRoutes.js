import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import FindPG from "../pages/FindPG";
import PGDetails from "../pages/PGDetails";


export default function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                 {/* <Route path="/" element={<Login/>}/> */}
                <Route path="/" element={<Login/>}/>
                <Route path="/register" element={<Register/>} />
                <Route path="/findpg" element={<FindPG/>} />
                <Route path="/pgdetails" element={<PGDetails/>} />
            </Routes>
        </BrowserRouter>
    );
}