import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  width: 100%;

  header {
    display: flex;
    justify-content: start;

    margin-right: auto;

    margin-bottom: 32px;

    button {
      background: #082032;
    }
  }

  form {
    width: 100%;
    max-width: 640px;

    display: flex;
    flex-direction: column;

    button {
      margin-top: 16px;
      font-size: 2.4rem;
    }
    /* > div + div {
      margin-top: 8px;
    } */
  }
`;
