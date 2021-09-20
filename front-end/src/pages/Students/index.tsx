import { useCallback, useEffect, useState } from "react";

import Button from "../../components/Button";
import api from "../../services/api";
import { Container, Student } from "./styles";

interface IStudent {
  id: number;
  name: string;
  email: string;
}

export default function Students() {
  const [students, setStudents] = useState<IStudent[]>([]);

  useEffect(() => {
    api.get("/students").then(response => {
      setStudents(response.data.content);
    });
  }, []);

  const handleOpenModal = useCallback(() => {
    console.log("Abrir modal de edição");
  }, []);

  return (
    <Container>
      <header>
        <h1>Estudantes</h1>

        <Button>Criar estudante</Button>
      </header>

      <section>
        <ul>
          {students.map(student => (
            <Student key={student.id}>
              <img
                src="https://i1.wp.com/www.cssscript.com/wp-content/uploads/2019/03/Generate-Random-Profile-Image-Name-Animal-Avatars.jpg?fit=590%2C545&ssl=1"
                alt={student.name}
              />
              <strong>{student.name}</strong>
              <p>{student.email}</p>

              <Button onClick={handleOpenModal}>Editar</Button>
            </Student>
          ))}
        </ul>
      </section>
    </Container>
  );
}
