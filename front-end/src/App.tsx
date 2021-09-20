import Routes from "./routes";

import "./styles/global.css";

export default function App() {
  return (
    <main id="mainBody">
      {/* Fazer uma opção de seleção:
          - Sou aluno
          - Sou instrutor
          - Sou administrador (deverá ser "oculto")

          Cada uma dessas opções deve encaminhar a pessoa para uma página específica
      */}
      <Routes />
    </main>
  );
}
