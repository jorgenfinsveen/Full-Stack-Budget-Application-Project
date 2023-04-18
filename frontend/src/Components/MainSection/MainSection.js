import { Route, Routes } from "react-router-dom";
import { DashBoard } from "../../Pages/DashBoard/DashBoard";
import { Budget } from "../../Pages/Budget/Budget";
import { Analytics } from "../../Pages/Analytics/Analytics";
import "./MainSection.css";
import { Login } from "../../Pages/Login/Login";
import { Register } from "../../Pages/Login/Register";
import { Navigate } from "react-router-dom";

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