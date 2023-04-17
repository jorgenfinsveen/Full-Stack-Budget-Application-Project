import React from 'react';
import { NavBar } from "./Components/Routing/NavBar";
import { BrowserRouter as Router } from "react-router-dom";
import './App.css';
import { MainSection } from './Components/MainSection/MainSection';

function App() {
  return (
    <Router>
      <section id="app-section">
        <NavBar id="navbar"/>
        <MainSection/>
      </section>
    </Router>
  );
}
    
export default App;