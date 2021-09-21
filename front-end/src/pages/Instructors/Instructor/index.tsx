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

export default function Instructor() {
  const formRef = useRef<FormHandles>(null);

  const history = useHistory();
  const { search } = useLocation();

  const query = new URLSearchParams(search);
  const instructor_id = query.get("instructor_id");

  useEffect(() => {
    if (instructor_id) {
      api.get(`/instructors/${instructor_id}`).then(response => {
        formRef.current?.setData(response.data);
      });
    }
  }, [instructor_id]);

  const handleGoBack = useCallback(() => {
    history.goBack();
  }, [history]);

  const handleSubmit = useCallback(
    async data => {
      const schema = Yup.object().shape({
        name: Yup.string().required("Campo nome é obrigatório"),
        cpf: Yup.string().required("Campo cpf é obrigatório"),
        email: Yup.string().required("Campo e-mail é obrigatório"),
      });

      formRef.current?.setErrors({});

      try {
        await schema.validate(data, { abortEarly: false });

        if (instructor_id) {
          await api.put(`/instructors/${instructor_id}`, data);

          toast.success("Instrutor atualizado com sucesso!");
        } else {
          await api.post("/instructors", data);

          toast.success("Instrutor criado com sucesso!");

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
            instructor_id ? "atualizar" : "criar"
          } instrutor. Favor, tente novamente!`,
        );
      }
    },
    [instructor_id],
  );

  const handleDeleteInstructor = useCallback(async () => {
    try {
      await api.delete(`/instructors/${instructor_id}`);

      toast.warning("Instrutor deletado!");

      history.goBack();
    } catch (error) {
      toast.error("Falha ao deletar instrutor. Favor, tente novamente!");
    }
  }, [history, instructor_id]);

  return (
    <Container>
      <header>
        <Button onClick={handleGoBack}>voltar</Button>
      </header>

      <Player
        src="https://assets9.lottiefiles.com/packages/lf20_dicn56pf.json"
        background="transparent"
        speed={1}
        style={{ height: "200px", width: "400px" }}
        loop
        controls
        autoplay
      />

      <Form onSubmit={handleSubmit} ref={formRef}>
        <Input label="Nome do instrutor" name="name" placeholder="Nome" />
        <Input label="CPF do instrutor" name="cpf" placeholder="CPF" />
        <Input
          label="E-mail do instrutor"
          name="email"
          type="email"
          placeholder="E-mail"
        />

        <Button type="submit">
          {instructor_id ? "Atualizar" : "Cadastrar"}
        </Button>

        {instructor_id && (
          <Button
            type="button"
            style={{ background: "#082032" }}
            onClick={handleDeleteInstructor}
          >
            Excluir
          </Button>
        )}
      </Form>
    </Container>
  );
}
