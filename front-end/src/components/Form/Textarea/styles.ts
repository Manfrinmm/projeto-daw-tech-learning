import styled from "styled-components";

interface IContainerProps {
  isErrored: boolean;
}

export const Container = styled.div<IContainerProps>`
  width: 100%;

  label {
    color: #ff4c29;
  }

  textarea {
    margin-top: 4px;
    padding: 4px 8px;

    border-radius: 4px;
    border: 1px solid ${props => (props.isErrored ? "#ed4337" : "#fff")};

    font-size: 1.6rem;

    width: 100%;
  }

  span {
    display: inline-block;
    margin-top: 4px;
    margin-bottom: 16px;

    color: red;
    font-size: 1.6rem;
  }
`;
