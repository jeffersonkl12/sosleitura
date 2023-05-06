import {
  Form,
  Link,
  Outlet,
  useLocation,
} from "react-router-dom";
import "./formDefault.css";
import {
  Button,
  TextField,
} from "@mui/material";
import { useState } from "react";
import styled from "@emotion/styled";

const InputField = styled(TextField)(() => ({
  "& input": {
    fontSize: "20px",
    padding: "5px 15px",
  },
}));

const ButtonLogin = styled(Button)(({ theme }) => ({
  fontSize: "20px",
  borderColor: theme.palette.primary.cinza,
  color: theme.palette.secondary.cinzaEscuro,

  ":hover": {
    backgroundColor: theme.palette.secondary.main,
    color: "white",
    borderColor: theme.palette.secondary.main,
  },
}));

export default function FormContainer({ statusError }) {
    const {state} = useLocation();
    const selectRoute = state ? state.selectStatu: true;

  return (
    <div className="form__container flex f-jc-c f-ai-c">
      <div className="form__content">
        <Form method="POST" action="/login">
          <div>
            <div className="form__titulo f-jc-se flex f-ai-c">
              <h2 className={`${selectRoute ? "ativado": null}`}>
                <Link to="/login" className="link-reset" state={{selectStatu: true}}>Login</Link>
              </h2>
              <h2 className={`${!selectRoute ? "ativado": null}`}>
                <Link to="/cadastro" className="link-reset" state={{selectStatu: false}}>
                  Registrar
                </Link>
              </h2>
            </div>
            <div className="form__login">
              <Outlet />
            </div>
          </div>
        </Form>
      </div>
    </div>
  );
}

