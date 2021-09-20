import styled from "styled-components";

export const Container = styled.button`
  cursor: pointer;

  border: 0;
  border-radius: 8px;
  padding: 4px 8px;

  color: #fff;
  font-size: 1.8rem;

  background: #ff4c29;

  transition: 0.2s opacity;

  &:hover {
    opacity: 0.8;
  }
`;
