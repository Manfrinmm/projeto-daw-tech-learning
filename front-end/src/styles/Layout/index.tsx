import React from "react";

import { Container } from "./styles";

interface ILayoutProps {
  children: React.ReactNode;
}

export default function Layout({ children }: ILayoutProps) {
  return <Container>{children}</Container>;
}
