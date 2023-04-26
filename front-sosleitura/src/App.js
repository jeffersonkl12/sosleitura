import { createTheme } from '@mui/material';
import './App.css';
import Footer from './components/Footer';
import Header from './components/Header';
import { ThemeProvider } from '@emotion/react';


const theme = createTheme({
  palette:{
    primary:{
      main: "#e32636",
      white: "white",
      cinza: "#C0C0C0"
    },
    secondary:{
      main: "#f28500",
      cinzaEscuro: "#778899"
    }
  }
})
function App() {
  return (
    <div className="App">
      <ThemeProvider theme={theme}>
        <Header/>
        <Footer/>
      </ThemeProvider>
    </div>
  );
}

export default App;
