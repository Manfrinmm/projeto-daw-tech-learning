import React, {
  SelectHTMLAttributes,
  useEffect,
  useRef,
  CSSProperties,
} from "react";

import { useField } from "@unform/core";

import { Container, Error } from "./styles";

interface ISelectProps extends SelectHTMLAttributes<HTMLSelectElement> {
  name: string;
  label: string;
  optionValues: Array<{ value: number; label: string }>;
  containerStyle?: CSSProperties;
}

const Select: React.FC<ISelectProps> = ({
  label,
  name,
  containerStyle = {},
  optionValues,
  ...rest
}) => {
  const selectRef = useRef(null);

  const { defaultValue, error, fieldName, registerField } = useField(name);

  useEffect(() => {
    registerField({
      name: fieldName,
      ref: selectRef.current,
      path: "value",
    });
  }, [registerField, fieldName]);

  return (
    <Container isErrored={!!error} style={containerStyle}>
      <label htmlFor={fieldName}>{label}</label>
      <div>
        <select
          id={fieldName}
          name={fieldName}
          ref={selectRef}
          defaultValue={defaultValue}
          {...rest}
        >
          <option hidden>Selecione um instrutor</option>

          {optionValues.length < 1 && (
            <option disabled>
              Você não possui nenhum instrutor cadastrado
            </option>
          )}

          {optionValues.map(option => (
            // <optgroup label={option.name} key={option.value}>
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
            // </optgroup>
          ))}
        </select>
      </div>
      {error && <Error>{error}</Error>}
    </Container>
  );
};

export default Select;
