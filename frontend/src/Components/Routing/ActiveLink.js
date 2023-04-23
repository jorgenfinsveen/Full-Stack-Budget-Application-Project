import { NavLink } from "react-router-dom";

/**
 * React component which returns a NavLink component which is
 * used for routing purposes in the navbar as well as in a couple
 * of buttons in the login- and register-page. The code is inspired
 * from an example provided by Girts Strazdins at a lecture at NTNU
 * Ålesund for the course IDATA2301 - Web Technologies.
 * 
 * @see https://github.com/ntnu-datakomm/web-examples/tree/main/public_html/examples/react/12-routing
 */
export function ActiveLink(props) {
  return (
    <NavLink
      to={props.to}
      className={({ isActive }) => (isActive ? "selected" : "")}
      style={{ textDecoration: 'none' }}
    >
      {props.children}
    </NavLink>
  );
}