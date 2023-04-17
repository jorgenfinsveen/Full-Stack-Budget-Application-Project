import { NavLink } from "react-router-dom";


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