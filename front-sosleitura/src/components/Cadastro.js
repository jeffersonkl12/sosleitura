import {
  Form,
  Link,
  redirect,
  useLoaderData,
  useNavigate,
  useRouteError,
} from "react-router-dom";
import "./formDefault.css";
import {
  Button,
  Checkbox,
  FormControlLabel,
  FormGroup,
  Modal,
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

export default function Cadastro({ statusError }) {
  const [modalStatu,setModalStatu] = useState(false);

  function handleModalStatu(status){
    
    setModalStatu(status);
    
  }

  return (
      <div className="form__container flex f-jc-c f-ai-c">
        <div className="form__content">
          <Form method="POST" action="/cadastro">
            <div>
              <div className="form__titulo f-jc-se flex f-ai-c">
                <h2>
                  <Link to={"../login"}>Login</Link>
                </h2>
                <h2 className="ativado">Registrar</h2>
              </div>
              <div className="form__login">
                <div className="form__sp flex f-jc-c jc-ai-c">
                  {statusError ? (
                    <div className="form__error">
                      <p>Cadastro invalido!</p>
                    </div>
                  ) : null}
                </div>
                <div className="form__field">
                  <InputField
                    type="text"
                    placeholder="digite nome..."
                    fullWidth={true}
                    variant="standard"
                    name="nome"
                  />
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
                  <Link className="check-field__link" onClick={()=>handleModalStatu(true)}>Esqueceu a senha ?</Link>
                </div>
                <div className="form___button-login flex f-jc-c">
                  <ButtonLogin variant="outlined" type="submit" size="large">
                    Cadastrar
                  </ButtonLogin>
                </div>
              </div>
            </div>
          </Form>
        </div>
      </div>
  );
}


