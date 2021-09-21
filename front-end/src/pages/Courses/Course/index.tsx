import { useCallback, useEffect, useRef, useState } from "react";
import { useHistory, useLocation } from "react-router-dom";
import { toast } from "react-toastify";

import { Player } from "@lottiefiles/react-lottie-player";
import { FormHandles } from "@unform/core";
import { Form } from "@unform/web";
import * as Yup from "yup";

import Button from "../../../components/Button";
import Input from "../../../components/Form/Input";
import Select from "../../../components/Form/Select";
import Textarea from "../../../components/Form/Textarea";
import api from "../../../services/api";
import getValidationErrors from "../../../utils/getValidationErrors";
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
        formRef.current?.setData({
          ...response.data,
          instructor_id: response.data.instructor.id,
        });
      });
    }
  }, [course_id]);

  const handleGoBack = useCallback(() => {
    history.goBack();
  }, [history]);

  const handleSubmit = useCallback(
    async data => {
      const schema = Yup.object().shape({
        name: Yup.string().required("Campo nome é obrigatório"),
        description: Yup.string().required("Campo descrição é obrigatório"),
        thumbnail: Yup.string().required("Campo thumbnail é obrigatório"),
        instructor_id: Yup.number().min(1, "Campo de instrutor é obrigatório"),
      });

      formRef.current?.setErrors({});

      try {
        await schema.validate(
          { ...data, instructor_id: Number(data.instructor_id) || 0 },
          { abortEarly: false },
        );

        if (course_id) {
          await api.put(`/courses/${course_id}`, data);

          toast.success("Curso atualizado com sucesso!");
        } else {
          await api.post("/courses", data);

          toast.success("Curso criado com sucesso!");

          formRef.current?.reset();
        }
      } catch (error) {
        if (error instanceof Yup.ValidationError) {
          const errors = getValidationErrors(error);

          formRef.current?.setErrors(errors);

          return;
        }

        toast.error(
          `Falha ao ${
            course_id ? "atualizar" : "criar"
          } curso. Favor, tente novamente!`,
        );
      }
    },
    [course_id],
  );

  const handleDeleteCourse = useCallback(async () => {
    try {
      await api.delete(`/courses/${course_id}`);

      toast.warning("Curso deletado!");

      history.goBack();
    } catch (error) {
      toast.error("Falha ao deletar curso. Favor, tente novamente!");
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
        speed={1}
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

        <Button type="submit"> {course_id ? "Atualizar" : "Cadastrar"}</Button>

        {course_id && (
          <Button
            type="button"
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
