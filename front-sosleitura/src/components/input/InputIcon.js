import { TextField, createTheme } from "@mui/material";
import "./inputIcon.css";
import styled from "@emotion/styled";

const InputIconField = styled("div", {
  shouldForwardProp: (prop) => prop !== "primary" && prop !== "secondary",
})(({ primary, secondary, theme }) => ({
  "& .MuiInputBase-root": {
    borderRadius: "0"
  },
  "& .css-1t8l2tu-MuiInputBase-input-MuiOutlinedInput-input": {
    padding: "9px",
  },
  "& .MuiOutlinedInput-root": {
    "& fieldset": {
      border: secondary ? "none": "1px solid black",
      borderColor:  theme.palette.primary.cinza,
      borderRadius: "0"
    },
    "&:hover fieldset": {
      borderColor: theme.palette.primary.cinza,
    },
    "&.Mui-focused fieldset": {
      borderColor: theme.palette.primary.cinza,
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

export default function InputIcon({ placeholder, secondary, icon, type }) {
  const Icon = icon;
  return (
    <InputIconField className={`inputIcon__container ${secondary ? " secondary": ""}`}>
      <TextField type={type} placeholder={placeholder} fullWidth={true}/>
      <div className="pesquisa__icon flex f-jc-c f-ai-c">
        <Icon sx={iconSx}/>
      </div>
    </InputIconField>
  );
}
