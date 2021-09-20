import React, { TextareaHTMLAttributes, useEffect, useRef } from "react";

import { useField } from "@unform/core";

import { Container } from "./styles";

interface ITextareaProps extends TextareaHTMLAttributes<HTMLTextAreaElement> {
  name: string;
  label: string;
}
const Textarea: React.FC<ITextareaProps> = ({ name, label, ...rest }) => {
  const textareaRef = useRef(null);

  const { fieldName, defaultValue, registerField, error } = useField(name);

  useEffect(() => {
    registerField({
      name: fieldName,
      ref: textareaRef.current,
      path: "value",
    });
  }, [fieldName, registerField]);

  return (
    <Container>
      <label htmlFor={fieldName}>{label}</label>
      <textarea
        id={fieldName}
        ref={textareaRef}
        autoComplete="off"
        defaultValue={defaultValue}
        {...rest}
      />

      {/* {error && <span>{error}</span>} */}
      {error && <span className="invalid-feedback">{error}</span>}
    </Container>
  );
};

export default Textarea;
