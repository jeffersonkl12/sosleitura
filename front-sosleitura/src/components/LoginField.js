import {
    Link,
    redirect,
  } from "react-router-dom";
  import "./formDefault.css";
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

export default function LoginField({statusError}){
    return (
        <>
        <div className="form__sp flex f-jc-c jc-ai-c">
                {statusError ? (
                  <div className="form__error">
                    <p>Login invalido!</p>
                  </div>
                ) : null}
              </div>
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
                <Link className="check-field__link">Esqueceu a senha ?</Link>
              </div>

              <div className="form___button-login flex f-jc-c">
                <ButtonLogin variant="outlined" type="submit" size="large">
                  LOGIN
                </ButtonLogin>
              </div>
        </>
    )
}

export async function handleLoginAction({ request }) {
  const data = await request.formData();

  const login = data.get("login");
  const password = data.get("password");

  var response = await fetch(
    `http://localhost:8080/sosleitura/home/login?&login=${login}&password=${password}`,
    { method: "POST" }
  );

  if (response.status === 401) {
    throw new Response("login invalido", { status: 401 });
  }

  return redirect("/");
}
