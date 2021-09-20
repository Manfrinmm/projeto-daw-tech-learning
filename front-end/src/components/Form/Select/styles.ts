import styled from "styled-components";

interface IContainerProps {
  isErrored: boolean;
}

export const Container = styled.div<IContainerProps>`
  display: flex;
  flex-direction: column;

  font-size: 1.6rem;

  label {
    color: #ff4c29;
  }

  select {
    margin-top: 4px;
    padding: 4px 8px;

    border: 1px solid ${props => (props.isErrored ? "#ed4337" : "#fff")};
    border-radius: 4px;

    width: 100%;

    font-size: 1.8rem;

    option {
      font-size: 80%;
    }

    svg {
      margin-right: 8px;
      opacity: 0.7;
    }

    &::placeholder {
      opacity: 0.6;
    }
  }
`;

export const Error = styled.span`
  color: #ed4337;
  margin-top: 4px;
  font-weight: 500;
`;
