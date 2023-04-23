import './App.css';
import React, { useEffect, useState } from 'react';
import { NavBar } from "./Components/Routing/NavBar";
import { BrowserRouter as Router } from "react-router-dom";
import { MainSection } from './Components/MainSection/MainSection';
import { SESSION } from './Session/Session';

/**
 * Main component of the React application. It is the ancestor of all
 * other components.
 */
function App() {

  /** React Hook for keeping track of the state of the NavBar component. */
  const [navbar, setNavbar] = useState(<NavBar id="navbar" enabled={false}/>);

  /* Executes on each render. */
  useEffect(() => {
    /* Fetches the auth attribute of SESSION until it is set to true. */
    let interval = setInterval(() => {
      if (SESSION.getAuth()) {
        /* Updates the state of the NavBar component to the enabled version. */
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