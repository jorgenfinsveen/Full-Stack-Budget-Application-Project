import './NavBar.css';
import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGauge, faCoins, faChartLine, faRightFromBracket } from '@fortawesome/free-solid-svg-icons';
import { SESSION } from '../../Session/Session';
import { ActiveLink } from "./ActiveLink";

export function NavBar(props) {

    const handleLogout = () => {
        window.location.replace("/login");
    }

    const disabledUl = (
        <ul>
            <li id="li-dashboard">
                <FontAwesomeIcon icon={faGauge}/>
            </li>
            <li id="li-budget">
                <FontAwesomeIcon icon={faCoins}/>
            </li>
            <li id="li-analytics">
                <FontAwesomeIcon icon={faChartLine}/>
            </li>
            <li id="li-login">
                <FontAwesomeIcon icon={faRightFromBracket}/>
            </li>
        </ul>    
    );

    const enabledUl = (
        <ul>
                <li id="li-dashboard">
                    <ActiveLink to="/dashboard">
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
                <li id="li-login" onClick={handleLogout}>
                    <ActiveLink to="/login">
                        <FontAwesomeIcon icon={faRightFromBracket}/>
                    </ActiveLink>
                </li>
            </ul>    
    );


    return (
        <nav>
            {props.enabled ? enabledUl : disabledUl}
        </nav>
    );
};
  