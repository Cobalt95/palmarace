import { useState } from "react"
import Home from "./components/home/Home"
import LoginForm from "./components/login-form/LoginForm"
import RegisterForm from "./components/register-form/RegisterForm"

function App() {

  // state & data

  const REGISTER_FORM:string = "RegisterForm";
  const LOGIN_FORM:string = "LoginForm";

  const [publicComponentDisplayed, setPublicComponentDisplayed] = useState("RegisterForm");
  const [privateContentDisplayed, setPrivateContentDisplayed] = useState(false);
  // handlers
  const toggleRegisterLogin = (target:string) => {
    setPublicComponentDisplayed(target);
  }
  // render
  return (
    <div>
      <h1>
        Welcome to Palmarace !
      </h1>
      { privateContentDisplayed ?
        <>
          <Home hidePrivateContent={() => setPrivateContentDisplayed(false)}/>
        </> 
      :
        <>
          { publicComponentDisplayed === REGISTER_FORM && 
            <RegisterForm 
              title="Create an account" 
              toggleLogin={() => toggleRegisterLogin(LOGIN_FORM)} 
              displayPrivateContent={() => setPrivateContentDisplayed(true)}
            /> 
          }
          { publicComponentDisplayed === LOGIN_FORM && 
            <LoginForm 
              title="Log in" 
              toggleRegister={() => toggleRegisterLogin(REGISTER_FORM)} 
              displayPrivateContent={() => setPrivateContentDisplayed(true)}
            /> 
          }
        </>
      }
    </div>
  )
}

export default App
