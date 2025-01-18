import { useState } from "react"
import Home from "./components/private-content/home/Home"
import LoginForm from "./components/public-content/login-form/LoginForm"
import RegisterForm from "./components/public-content/register-form/RegisterForm"
import Header from "./components/private-content/header/Header";
import "./App.scss";
import Footer from "./components/private-content/footer/Footer";

function App() {

  // state & data

  const REGISTER_FORM = "RegisterForm";
  const LOGIN_FORM = "LoginForm";

  const [publicComponentDisplayed, setPublicComponentDisplayed] = useState(REGISTER_FORM);
  const [privateContentDisplayed, setPrivateContentDisplayed] = useState(false);

  // handlers
  const toggleRegisterLogin = (target:typeof REGISTER_FORM | typeof LOGIN_FORM) => {
    setPublicComponentDisplayed(target);
  }
  // render
  return (
    <div>
      { privateContentDisplayed ?
        <div className="private-content-container">
          <Header hidePrivateContent={() => setPrivateContentDisplayed(false)} />
          <Home />
          <Footer />
        </div> 
      :
        <>
          <h1 className="welcome-message">Welcome to Palmarace !</h1>
          <div className="public-content-container">
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
          </div>
        </>
      }
    </div>
  )
}

export default App;
