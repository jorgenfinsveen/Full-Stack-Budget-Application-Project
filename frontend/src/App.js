import React, { useEffect, useState } from 'react';
import { NavBar } from "./Components/Routing/NavBar";
import { BrowserRouter as Router } from "react-router-dom";
import './App.css';
import { MainSection } from './Components/MainSection/MainSection';
import { SESSION } from './Session/Session';

function App() {

  const [navbar, setNavbar] = useState(<NavBar id="navbar" enabled={false}/>);

  useEffect(() => {
    let interval = setInterval(() => {
      if (SESSION.getAuth()) {
        setNavbar(<NavBar id="navbar" enabled={true}/>); 
        clearInterval(interval);
      }
    }, 10);
  });

  return (
    <Router>
      <section id="app-section">
        {navbar}
        <MainSection id="mainsection"/>
      </section>
    </Router>
  );
}
    
export default App;