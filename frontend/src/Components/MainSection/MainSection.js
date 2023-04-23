import { Route, Routes } from "react-router-dom";
import { DashBoard } from "../../Pages/DashBoard/DashBoard";
import { Budget } from "../../Pages/Budget/Budget";
import { Analytics } from "../../Pages/Analytics/Analytics";
import "./MainSection.css";
import { Login } from "../../Pages/Login/Login";
import { Register } from "../../Pages/Login/Register";
import { Navigate } from "react-router-dom";


/**
 * MainSection component responsible for providing the correct 
 * page-components at the given route paths. The code is inspired
 * from an example provided by Girts Strazdins at a lecture at NTNU
 * Ålesund for the course IDATA2301 - Web Technologies.
 * 
 * @see https://github.com/ntnu-datakomm/web-examples/tree/main/public_html/examples/react/12-routing
 */
export function MainSection() {
  return (
    <main>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/dashboard" element={<DashBoard />} />
        <Route path="/budget" element={<Budget />} />
        <Route path="/analytics" element={<Analytics />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </main>
  );
}
