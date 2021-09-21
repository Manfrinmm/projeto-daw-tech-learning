import { BrowserRouter, Redirect, Switch } from "react-router-dom";

import Courses from "../pages/Courses";
import Course from "../pages/Courses/Course";
import Instructors from "../pages/Instructors";
import Instructor from "../pages/Instructors/Instructor";
import Students from "../pages/Students";
import Student from "../pages/Students/Student";
import Route from "./Route";

export default function Routes(): JSX.Element {
  return (
    <BrowserRouter>
      <Switch>
        {/* <Route exact path="/" component={Dashboard} /> */}
        <Route exact path="/students" component={Students} />
        <Route path="/students/student" component={Student} />

        <Route exact path="/instructors" component={Instructors} />
        <Route path="/instructors/instructor" component={Instructor} />

        <Route exact path="/courses" component={Courses} />
        <Route path="/courses/course" component={Course} />

        <Redirect to="/students" />
      </Switch>
    </BrowserRouter>
  );
}
