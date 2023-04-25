import './NavBar.css';
import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGauge, faCoins, faChartLine, faRightFromBracket } from '@fortawesome/free-solid-svg-icons';
import { ActiveLink } from "./ActiveLink";


/**
 * React component which represents the navbar in the website. 
 * It has icons which routes to different pages, and comes in
 * two different modes.
 * 
 * The first mode is a disabled variant which is displayed when
 * a user is not yet authenticated. The second mode is an enabled
 * variant which lets an authenticated user maneouver throughout
 * the website.
 * 
 * When it comes to code related to routing, the code is inspired
 * from an example provided by Girts Strazdins at a lecture at NTNU
 * Ålesund for the course IDATA2301 - Web Technologies.
 * 
 * @see https://github.com/ntnu-datakomm/web-examples/tree/main/public_html/examples/react/12-routing
 */
export function NavBar(props) {

    const handleLogout = () => {
        window.location.replace("/");
    }

    /** 
     * Disabled NavBar which only shows the icons in a black color.
     * The icons are not clickable and does not route to different
     * pages.
     */
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


    /**
     * Enabled NavBar which shows the icons in a blue color.
     * The icons are clickable and routes to different pages.
     */
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
                    <ActiveLink to="/">
                        <FontAwesomeIcon icon={faRightFromBracket}/>
                    </ActiveLink>
                </li>
            </ul>    
    );


    /*
     * Returns either the enabled or the disabled variant of the
     * navbar depending on whether the props.enabled boolean are
     * set to true or false.
     */
    return (
        <nav>
            {props.enabled ? enabledUl : disabledUl}
        </nav>
    );
};
  