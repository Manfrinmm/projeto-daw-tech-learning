import { BrowserRouter, Redirect, Switch } from "react-router-dom";

import Courses from "../pages/Courses";
import Course from "../pages/Courses/Course";
import Instructors from "../pages/Instructors";
import Students from "../pages/Students";
import Route from "./Route";

export default function Routes(): JSX.Element {
  return (
    <BrowserRouter>
      <Switch>
        {/* <Route exact path="/" component={Dashboard} /> */}
        <Route path="/students" component={Students} />
        <Route path="/instructors" component={Instructors} />

        <Route exact path="/courses" component={Courses} />
        <Route path="/courses/course" component={Course} />

        <Redirect to="/students" />
      </Switch>
    </BrowserRouter>
  );
}
