import { useCallback, useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

import Button from "../../components/Button";
import api from "../../services/api";
import { Container, Course } from "./styles";

interface IStudent {
  id: number;
  name: string;
  description: string;
  thumbnail: string;
  instructor: {
    name: string;
  };
}

export default function Courses() {
  const [courses, setCourses] = useState<IStudent[]>([]);
  const history = useHistory();

  useEffect(() => {
    api.get("/courses").then(response => {
      setCourses(response.data.content);
    });
  }, []);

  const handleEditCourse = useCallback(
    (course_id: number) => {
      history.push(`/courses/course?course_id=${course_id}`);
    },
    [history],
  );

  const handleCreateCourse = useCallback(() => {
    history.push("/courses/course");
  }, [history]);

  return (
    <Container>
      <header>
        <h1>Cursos</h1>

        <Button onClick={handleCreateCourse}>Criar curso</Button>
      </header>

      <section>
        <ul>
          {courses.map(course => (
            <Course key={course.id}>
              <img src={course.thumbnail} alt={course.name} />
              <strong>{course.name}</strong>

              <p>{course.description}</p>

              <hr />

              <p>{course.instructor.name}</p>

              <Button
                onClick={() => {
                  handleEditCourse(course.id);
                }}
              >
                Editar
              </Button>
            </Course>
          ))}
        </ul>
      </section>
    </Container>
  );
}
