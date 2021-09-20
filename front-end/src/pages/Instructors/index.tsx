import { useCallback, useEffect, useState } from "react";

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

  useEffect(() => {
    api.get("/instructors").then(response => {
      setInstructors(response.data.content);
    });
  }, []);

  const handleOpenModal = useCallback(() => {
    console.log("Abrir modal de edição");
  }, []);

  return (
    <Container>
      <header>
        <h1>Instrutores</h1>

        <Button>Criar instrutor</Button>
      </header>

      <section>
        <ul>
          {instructors.map(student => (
            <Student key={student.id}>
              <img
                src="https://i1.wp.com/www.cssscript.com/wp-content/uploads/2019/03/Generate-Random-Profile-Image-Name-Animal-Avatars.jpg?fit=590%2C545&ssl=1"
                alt={student.name}
              />
              <strong>{student.name}</strong>
              <p>{student.email}</p>
              <p>{student.cpf}</p>

              <Button onClick={handleOpenModal}>Editar</Button>
            </Student>
          ))}
        </ul>
      </section>
    </Container>
  );
}
