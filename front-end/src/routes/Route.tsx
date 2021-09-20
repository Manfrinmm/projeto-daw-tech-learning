import {
  Redirect,
  Route as ReactDOMRouter,
  RouteProps as ReactDOMRouteProps,
} from "react-router-dom";

import Sidebar from "../components/Sidebar";
import DefaultLayout from "../styles/Layout";
// import Header from "../components/Header";
// import { useAuth } from "../hooks/auth";

interface RouteProps extends ReactDOMRouteProps {
  isPrivate?: boolean;
}

export default function Route({ isPrivate, ...rest }: RouteProps) {
  // const { user } = useAuth();

  // if (user && !isPrivate) {
  //   return <Redirect to="/tools" />;
  // }

  // if (!user && isPrivate) {
  //   return <Redirect to="/" />;
  // }

  return (
    // {user && <Header />}
    <>
      <Sidebar />
      <DefaultLayout>
        <ReactDOMRouter {...rest} />
      </DefaultLayout>
    </>
  );
}
