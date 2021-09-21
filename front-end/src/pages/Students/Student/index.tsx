import { useCallback, useEffect, useRef } from "react";
import { useHistory, useLocation } from "react-router-dom";
import { toast } from "react-toastify";

import { Player } from "@lottiefiles/react-lottie-player";
import { FormHandles } from "@unform/core";
import { Form } from "@unform/web";
import * as Yup from "yup";

import Button from "../../../components/Button";
import Input from "../../../components/Form/Input";
import api from "../../../services/api";
import getValidationErrors from "../../../utils/getValidationErrors";
import { Container } from "./styles";

export default function Student() {
  const formRef = useRef<FormHandles>(null);

  const history = useHistory();
  const { search } = useLocation();

  const query = new URLSearchParams(search);
  const student_id = query.get("student_id");

  useEffect(() => {
    if (student_id) {
      api.get(`/students/${student_id}`).then(response => {
        formRef.current?.setData(response.data);
      });
    }
  }, [student_id]);

  const handleGoBack = useCallback(() => {
    history.goBack();
  }, [history]);

  const handleSubmit = useCallback(
    async data => {
      const schema = Yup.object().shape({
        name: Yup.string().required("Campo nome é obrigatório"),
        email: Yup.string().required("Campo e-mail é obrigatório"),
      });

      formRef.current?.setErrors({});

      try {
        await schema.validate(data, { abortEarly: false });

        if (student_id) {
          await api.put(`/students/${student_id}`, data);

          toast.success("Aluno atualizado com sucesso!");
        } else {
          await api.post("/students", data);

          toast.success("Aluno criado com sucesso!");

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
            student_id ? "atualizar" : "criar"
          } aluno. Favor, tente novamente!`,
        );
      }
    },
    [student_id],
  );

  const handleDeleteStudent = useCallback(async () => {
    try {
      await api.delete(`/students/${student_id}`);

      toast.warning("Aluno deletado!");

      history.goBack();
    } catch (error) {
      toast.error("Falha ao deletar aluno. Favor, tente novamente!");
    }
  }, [history, student_id]);

  return (
    <Container>
      <header>
        <Button onClick={handleGoBack}>voltar</Button>
      </header>

      <Player
        src="https://assets10.lottiefiles.com/packages/lf20_ei2gf306.json"
        background="transparent"
        speed={1}
        style={{ height: "300px", width: "350px" }}
        loop
        controls
        autoplay
      />

      <Form onSubmit={handleSubmit} ref={formRef}>
        <Input label="Nome do aluno" name="name" placeholder="Nome" />
        <Input
          label="E-mail do aluno"
          name="email"
          type="email"
          placeholder="E-mail"
        />

        <Button type="submit">{student_id ? "Atualizar" : "Cadastrar"}</Button>

        {student_id && (
          <Button
            type="button"
            style={{ background: "#082032" }}
            onClick={handleDeleteStudent}
          >
            Excluir
          </Button>
        )}
      </Form>
    </Container>
  );
}
