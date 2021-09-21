import { useCallback, useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

import Button from "../../components/Button";
import api from "../../services/api";
import { Container, Student } from "./styles";

interface IInstructors {
  id: number;
  name: string;
  cpf: string;
  email: string;
}

export default function Instructors() {
  const [instructors, setInstructors] = useState<IInstructors[]>([]);

  const history = useHistory();

  useEffect(() => {
    api.get("/instructors").then(response => {
      setInstructors(response.data.content);
    });
  }, []);

  const handleEditInstructor = useCallback(
    (instructor_id: number) => {
      history.push(`/instructors/instructor?instructor_id=${instructor_id}`);
    },
    [history],
  );

  const handleCreateInstructor = useCallback(() => {
    history.push("/instructors/instructor");
  }, [history]);

  return (
    <Container>
      <header>
        <h1>Instrutores</h1>

        <Button onClick={handleCreateInstructor}>Criar instrutor</Button>
      </header>

      <section>
        <ul>
          {instructors.map(instructor => (
            <Student key={instructor.id}>
              <img
                src="https://i1.wp.com/www.cssscript.com/wp-content/uploads/2019/03/Generate-Random-Profile-Image-Name-Animal-Avatars.jpg?fit=590%2C545&ssl=1"
                alt={instructor.name}
              />
              <strong>{instructor.name}</strong>
              <p>{instructor.email}</p>
              <p>{instructor.cpf}</p>

              <Button
                onClick={() => {
                  handleEditInstructor(instructor.id);
                }}
              >
                Editar
              </Button>
            </Student>
          ))}
        </ul>
      </section>
    </Container>
  );
}
