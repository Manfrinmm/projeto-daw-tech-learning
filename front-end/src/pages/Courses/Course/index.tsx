/* eslint-disable react/style-prop-object */
/* eslint-disable jsx-a11y/label-has-associated-control */

import { useCallback, useEffect, useRef, useState } from "react";
import { useHistory, useLocation } from "react-router-dom";

import { Player, Controls } from "@lottiefiles/react-lottie-player";
import { FormHandles } from "@unform/core";
import { Form } from "@unform/web";

import Button from "../../../components/Button";
import Input from "../../../components/Form/Input";
import Select from "../../../components/Form/Select";
import Textarea from "../../../components/Form/Textarea";
import api from "../../../services/api";
import { Container } from "./styles";

interface IInstructors {
  id: number;
  name: string;
  cpf: string;
  email: string;
}

export default function Course() {
  const [instructors, setInstructors] = useState<IInstructors[]>([]);

  const formRef = useRef<FormHandles>(null);

  const history = useHistory();
  const { search } = useLocation();

  const query = new URLSearchParams(search);
  const course_id = query.get("course_id");

  useEffect(() => {
    api.get("/instructors").then(response => {
      setInstructors(response.data.content);
    });

    if (course_id) {
      api.get(`/courses/${course_id}`).then(response => {
        // setInstructors(response.data.content);

        console.log(response.data);

        formRef.current?.setData({
          ...response.data,
          instructor_id: response.data.instructor.id,
        });
      });
    }
  }, [course_id]);

  const handleSubmit = useCallback(
    async data => {
      console.log(data);

      if (course_id) {
        const response = await api.put(`/courses/${course_id}`, data);

        console.log(response.data);
      } else {
        const response = await api.post("/courses", data);
        console.log(response.data);
      }
    },
    [course_id],
  );

  const handleGoBack = useCallback(() => {
    history.goBack();
  }, [history]);

  const handleDeleteCourse = useCallback(async () => {
    try {
      await api.delete(`/courses/${course_id}`);

      console.log("curso deletado");
      history.goBack();
    } catch (error) {
      console.log("Erro ao deletar");
    }
  }, [history, course_id]);

  return (
    <Container>
      <header>
        <Button onClick={handleGoBack}>voltar</Button>
      </header>

      <Player
        src="https://assets3.lottiefiles.com/packages/lf20_dNPIoR.json"
        background="transparent"
        // speed="1"
        style={{ height: "200px", width: "400px" }}
        loop
        controls
        autoplay
      />

      <Form onSubmit={handleSubmit} ref={formRef}>
        <Input label="Nome do curso" name="name" placeholder="Nome do curso" />

        <Textarea
          label="Descrição do curso"
          name="description"
          placeholder="Digite a descrição do curso"
          rows={4}
        />

        <Input label="Thumbnail" name="thumbnail" placeholder="Thumbnail" />

        <Select
          label="Instrutor"
          name="instructor_id"
          optionValues={instructors.map(instructor => ({
            value: instructor.id,
            label: instructor.name,
          }))}
        />
        {/* <div className="form-floating mb-3">
          <select
            className="form-select"
            // size={3}
            aria-label="size 3 select example"
          >
            <option selected hidden>
              Selecione um instrutor
            </option>
            {instructors.map(instructor => (
              <option key={instructor.id} value={instructor.id}>
                {instructor.name}
              </option>
            ))}
          </select>
        </div> */}

        <Button type="submit"> {course_id ? "Atualizar" : "Cadastrar"}</Button>

        {course_id && (
          <Button
            style={{ background: "#082032" }}
            onClick={handleDeleteCourse}
          >
            Excluir
          </Button>
        )}
      </Form>
    </Container>
  );
}
