import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../pages/Login";


export default function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                 <Route path="/" element={<Login/>}/>
                {/* <Route path="/" element={<Login/>}/>
                <Route path="/register" element={<Register/>} />
                <Route path="/findpg" element={<FindPG/>} /> */}
            </Routes>
        </BrowserRouter>
    );
}