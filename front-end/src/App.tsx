import "react-toastify/dist/ReactToastify.min.css";
import { ToastContainer } from "react-toastify";

import Routes from "./routes";

import "./styles/global.css";

export default function App() {
  return (
    <>
      <ToastContainer />

      <main id="mainBody">
        {/* Fazer uma opção de seleção:
          - Sou aluno
          - Sou instrutor
          - Sou administrador (deverá ser "oculto")

          Cada uma dessas opções deve encaminhar a pessoa para uma página específica
      */}
        <Routes />
      </main>
    </>
  );
}
