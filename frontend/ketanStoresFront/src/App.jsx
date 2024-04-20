import React from 'react'
import LoginFinal from './pages/Login'
import { StyledEngineProvider, CssVarsProvider } from "@mui/joy/styles";
  
const App = () => {
  return (
    <>
      <StyledEngineProvider injectFirst>
        <CssVarsProvider>
          <LoginFinal />
        </CssVarsProvider>
      </StyledEngineProvider>
    </>
  );
}

export default App
