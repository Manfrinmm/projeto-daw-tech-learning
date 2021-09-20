import { Link } from "react-router-dom";

import { Container, ListItem } from "./styles";

export default function Sidebar() {
  return (
    <Container>
      <img
        src="https://cdn.dribbble.com/users/2564256/screenshots/14624974/media/8dd73df6f6d68be5e8fc974136d337fa.png?compress=1&resize=1200x900"
        alt="Tech Learning"
      />
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
