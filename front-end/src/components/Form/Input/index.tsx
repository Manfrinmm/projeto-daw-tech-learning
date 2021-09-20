import React, { InputHTMLAttributes, useEffect, useRef } from "react";

import { useField } from "@unform/core";

import { Container } from "./styles";

interface IInputProps extends InputHTMLAttributes<HTMLInputElement> {
  name: string;
  label: string;
}
const Input: React.FC<IInputProps> = ({ name, label, ...rest }) => {
  const inputRef = useRef(null);

  const { fieldName, defaultValue, registerField, error } = useField(name);

  useEffect(() => {
    registerField({
      name: fieldName,
      ref: inputRef.current,
      path: "value",
    });
  }, [fieldName, registerField]);

  return (
    <Container>
      <label htmlFor={fieldName}>{label}</label>
      <input
        id={fieldName}
        ref={inputRef}
        autoComplete="off"
        defaultValue={defaultValue}
        {...rest}
      />

      {error && <span>{error}</span>}
      {/* {error && <span className="invalid-feedback">{error}</span>} */}
    </Container>
  );
};

export default Input;
