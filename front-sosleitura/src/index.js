import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider,createBrowserRouter } from 'react-router-dom';
import './index.css';
import App from './App';
import {handleLoginAction as loginAction} from './components/LoginField';
import LoginErrorHandler from './components/error/LoginErrorHandler';
import {handleCadastroAction as cadastroAction} from './components/CadastroField';
import CadastroErrorHandler from './components/error/CadastroErrorHandle';
import FormContainer from './components/FormContainer';
import LoginField from './components/LoginField';
import CadastroField from './components/CadastroField';



const router = createBrowserRouter([
  {
    path: "/",
    element: <App/>,
    children: [
      {
        path: "/",
        element: <FormContainer/>,
        children: [
          {
            path: "/login",
            element: <LoginField/>,
            action: loginAction,
            errorElement: <LoginErrorHandler/>,
          },
          {
            path: "/cadastro",
            element: <CadastroField/>,
            action: cadastroAction,
            errorElement: <CadastroErrorHandler/>
          }
        ]
      }
    ]
  }


]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);


