import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;

  width: 100%;

  /* padding: 32px; */

  header {
    display: flex;

    justify-content: space-between;
  }

  section {
    margin-top: 32px;

    ul {
      display: grid;

      grid-template-columns: repeat(auto-fit, minmax(192px, 1fr));
      gap: 16px;
    }
  }
`;

export const Student = styled.li`
  display: flex;
  flex-direction: column;

  background: #334756;
  border: 1px solid #fff;
  border-radius: 8px;

  padding: 8px;

  max-width: 192px;

  img {
    height: 80px;
  }

  & + li {
    margin-top: 16px;
  }
`;
