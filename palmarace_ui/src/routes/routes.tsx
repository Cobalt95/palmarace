import Home from "../components/private-content/outlet/home/Home";
import Profile from "../components/private-content/outlet/profile/Profile";
import RegisterForm from "../components/public-content/register-form/RegisterForm";
import LoginForm from "../components/public-content/login-form/LoginForm";
import PrivateRoutesContainer from "../components/private-content/private-routes-container/PrivateRoutesContainer";
import EventCreateForm from "../components/private-content/outlet/event/event-create-form/EventCreateForm";

const publicRoutes = [
    {
        path: "/register",
        element: <RegisterForm />
    },
    {
        path: "/login",
        element: <LoginForm />
    }
];

const privateRoutes = [
    {
        index: true,
        element: <Home />
    },
    {
        path: "/profile",
        element: <Profile />
    },
    {
        path: "/event-create",
        element: <EventCreateForm />
    }
];

const routes = [
    ...publicRoutes,
    {
        path: "/",
        element: <PrivateRoutesContainer />,
        children: privateRoutes
    }
]

export default routes;