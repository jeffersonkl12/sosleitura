import { TextField, createTheme } from "@mui/material";
import "./inputIcon.css";
import styled from "@emotion/styled";

const InputIconField = styled("div", {
  shouldForwardProp: (prop) => prop !== "primary" && prop !== "secondary" && prop !== "border",
})(({ primary, secondary, theme, padding, border }) => ({
  "& .MuiInputBase-root": {
    backgroundColor: "white",
    borderRadius: "0"
  },
  "& .css-1t8l2tu-MuiInputBase-input-MuiOutlinedInput-input": {
    padding: padding ? padding+"px": null,
  },
  "& .MuiOutlinedInput-root": {
    "& fieldset": {
      border: border ? "1px solid black": "none",
      borderColor:  secondary ? theme.palette.primary.cinza: "none",
      borderRadius: "0"
    },
    "&:hover fieldset": {
      borderColor: secondary ? theme.palette.primary.cinza: "none",
    },
    "&.Mui-focused fieldset": {
      borderColor: secondary ? theme.palette.primary.cinza: "none",
    },
  },
}));

const iconSx = {
    color: "var(--cinza-escuro)",
    transition: "ease",
    transitionDuration: "0.2s",
    ":hover": {
        color: "white",
    },
}

export default function InputIcon({ placeholder, primary , secondary, icon, type,padding, border }) {
  const Icon = icon;
  return (
    <InputIconField className={`inputIcon__container ${secondary ? " secondary": ""}`} 
    border={border}
    padding={padding}
    primary={primary}
    secondary={secondary}
    >
      <TextField type={type} placeholder={placeholder} fullWidth={true}/>
      <div className="pesquisa__icon flex f-jc-c f-ai-c">
        <Icon sx={iconSx}/>
      </div>
    </InputIconField>
  );
}
