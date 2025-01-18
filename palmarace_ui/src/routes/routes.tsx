import Home from "../components/private-content/home/Home";
import Profile from "../components/private-content/home/profile/Profile";
import RegisterForm from "../components/public-content/register-form/RegisterForm";
import LoginForm from "../components/public-content/login-form/LoginForm";
import PrivateRoutesContainer from "../components/private-content/private-routes-container/PrivateRoutesContainer";

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