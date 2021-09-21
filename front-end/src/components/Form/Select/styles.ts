import styled from "styled-components";

interface IContainerProps {
  isErrored: boolean;
}

export const Container = styled.div<IContainerProps>`
  display: flex;
  flex-direction: column;

  label {
    color: #ff4c29;
  }

  select {
    margin-top: 4px;
    padding: 4px 8px;

    border: 1px solid ${props => (props.isErrored ? "#ed4337" : "#fff")};
    border-radius: 4px;

    width: 100%;

    font-size: 1.6rem;
  }
`;

export const Error = styled.span`
  display: inline-block;
  margin-top: 4px;
  margin-bottom: 16px;

  color: red;
  font-size: 1.6rem;
`;
