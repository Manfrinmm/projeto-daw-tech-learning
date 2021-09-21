import { Link as LinkRouter } from "react-router-dom";

import styled from "styled-components";

export const Container = styled.aside`
  display: flex;
  flex-direction: column;
  align-items: center;

  padding: 32px;
  background: #082032;

  img {
    height: 88px;
    margin-bottom: 16px;
    background: #2c394b;
    border-radius: 8px;
    padding: 4px;
  }

  ul {
    display: flex;
    flex-direction: column;
    /* align-items: center; */
  }
`;

export const ListItem = styled.li`
  a {
    text-decoration: none;
    font-size: 3.2rem;
    color: #fff;

    transition: 0.2s color linear;

    &:hover {
      color: #334756;
    }
  }

  & + li {
    margin-top: 16px;
  }
`;
