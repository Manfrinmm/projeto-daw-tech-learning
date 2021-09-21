import { Link } from "react-router-dom";

import logo from "../../assets/logo.svg";
import { Container, ListItem } from "./styles";

export default function Sidebar() {
  return (
    <Container>
      <img src={logo} alt="Tech Learning" />
      <ul>
        <ListItem>
          <Link to="/students">Estudantes</Link>
        </ListItem>
        <ListItem>
          <Link to="/instructors">Instrutores</Link>
        </ListItem>
        <ListItem>
          <Link to="/courses">Cursos</Link>
        </ListItem>
      </ul>
    </Container>
  );
}
