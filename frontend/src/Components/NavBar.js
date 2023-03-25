import './NavBar.css';
import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGauge, faCoins, faChartLine, faUser } from '@fortawesome/free-solid-svg-icons'
import { ActiveLink } from "./ActiveLink";

export function NavBar() {
    return (
        <nav>
            <ul>
                <li id="li-dashboard">
                    <ActiveLink to="/">
                        <FontAwesomeIcon icon={faGauge}/>
                    </ActiveLink>
                </li>
                <li id="li-budget">
                    <ActiveLink to="/budget">
                        <FontAwesomeIcon icon={faCoins}/>
                    </ActiveLink>
                </li>
                <li id="li-analytics">
                    <ActiveLink to="/analytics">
                        <FontAwesomeIcon icon={faChartLine}/>
                    </ActiveLink>
                </li>
                <li id="li-login">
                    <ActiveLink to="/login">
                        <FontAwesomeIcon icon={faUser}/>
                    </ActiveLink>
                </li>
            </ul>    
        </nav>
    );
};
  