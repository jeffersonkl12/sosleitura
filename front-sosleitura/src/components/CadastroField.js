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

export default function CadastroField({statusError}){
    return (
        <>
        <div className="form__sp flex f-jc-c jc-ai-c">
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
                <Link className="check-field__link">Esqueceu a senha ?</Link>
              </div>

              <div className="form___button-login flex f-jc-c">
                <ButtonLogin variant="outlined" type="submit" size="large">
                    Cadastrar
                </ButtonLogin>
              </div>
        </>
    )
}

export async function handleCadastroAction({ request }) {
    const data = await request.formData();
  
    const nome = data.get("nome");
    const login = data.get("login");
    const password = data.get("password");
  
    var response = await fetch(
      `http://localhost:8080/sosleitura/home/cadastro?login=${login}&password=${password}&nome=${nome}`,
      { method: "POST" }
    );
  
    if (response.status === 400) {
      throw new Response("cadastro invalido invalido", { status: 400 });
    }
  
    return redirect("/login");
  }