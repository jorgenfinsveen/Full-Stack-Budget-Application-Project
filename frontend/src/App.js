import React from 'react';
import { NavBar } from "./Components/NavBar";
import { BrowserRouter as Router } from "react-router-dom";
import './App.css';
import { MainSection } from './MainSection';

function App() {
  return (
    <Router>
      <section>
        <NavBar id="navbar"/>
        <MainSection/>
      </section>
    </Router>
  );
}
    
export default App;
