import "./App.scss";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import routes from "./routes/routes";

function App() {

  // state & data
  const router = createBrowserRouter(routes);
  // handlers
  // render
  return <RouterProvider router={router} />
}

export default App;
