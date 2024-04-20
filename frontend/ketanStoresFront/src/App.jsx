import React from 'react'
import LoginFinal from './pages/Login'
import { StyledEngineProvider, CssVarsProvider } from "@mui/joy/styles";
import Home from './pages/Home';
  
const App = () => {
  return (
    <>
      <StyledEngineProvider injectFirst>
        <CssVarsProvider>
          <LoginFinal />
          <Home />
        </CssVarsProvider>
      </StyledEngineProvider>
    </>
  );
}

export default App
