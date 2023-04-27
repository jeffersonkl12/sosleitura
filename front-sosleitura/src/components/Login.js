import { Form} from "react-router-dom";
import "./Login.css";
import {
  Button,
  Checkbox,
  FormControlLabel,
  FormGroup,
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

export default function Login() {
  const [selectLogin, setSelectLogin] = useState(true);

  function clickChangeLogin(status){
    setSelectLogin(status);
  }

  return (
    <div className="form__container flex f-jc-c f-ai-c">
      <div className="form__content">
        <Form method="POST" action="/login">
          <div>
            <div className="form__titulo f-jc-se flex f-ai-c">
              <h2 className={`${selectLogin ? "ativado" : ""}`} onClick={()=>clickChangeLogin(true)}>Login</h2>
              <h2 className={`${!selectLogin ? "ativado" : ""}`} onClick={()=>clickChangeLogin(false)}>Registrar</h2>
            </div>
            {selectLogin ? (
              <div className="form__login">
                <div className="form__field">
                  <InputField
                    type="email"
                    placeholder="digite login..."
                    fullWidth={true}
                    variant="standard"
                    name="login"
                  />
                  <InputField
                    type="password"
                    placeholder="digite a senha..."
                    fullWidth={true}
                    variant="standard"
                    name="password"
                  />
                </div>
                <div className="form__check-field flex f-jc-sb f-ai-c">
                  <FormGroup>
                    <FormControlLabel
                      control={<Checkbox />}
                      label="Lembrar senha"
                    />
                  </FormGroup>

                  <a className="check-field__link">Esqueceu a senha ?</a>
                </div>

                <div className="form___button-login flex f-jc-c">
                  <ButtonLogin variant="outlined" type="submit" size="large">
                    LOGIN
                  </ButtonLogin>
                </div>
              </div>
            ) : (
              <div className="form__cadastro">
                <div className="form__field">
                  <InputField
                    type="text"
                    placeholder="Nome..."
                    fullWidth={true}
                    variant="standard"
                  />
                  <InputField
                    type="email"
                    placeholder="Email..."
                    fullWidth={true}
                    variant="standard"
                  />
                  <InputField
                    type="password"
                    placeholder="Senha..."
                    fullWidth={true}
                    variant="standard"
                  />
                </div>
                <div className="form__check-field flex f-jc-sb f-ai-c">
                  <FormGroup>
                    <FormControlLabel
                      control={<Checkbox />}
                      label="Lembrar senha"
                    />
                  </FormGroup>
                </div>
                <div className="form___button-login flex f-jc-c">
                  <ButtonLogin variant="outlined" size="large">
                    CADASTRAR
                  </ButtonLogin>
                </div>
              </div>
            )}
          </div>
        </Form>
      </div>
    </div>
  );
}

export async function postCadastroAction({request}){
  var data = await request.formData();

  const login = data.get("login");
  const password = data.get("password");

  const campos = JSON.stringify({
    login: login,
    password: password
  });
  
  var header = new Headers();
  header.set("Content-Type","application/json");

  var response = (await fetch("http://localhost:8080/sosleitura/cadastro",{method: "POST",
  headers: header,body:campos}));
  
  return null;
}
