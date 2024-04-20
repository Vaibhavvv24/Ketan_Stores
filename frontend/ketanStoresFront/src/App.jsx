/** @format */

import React from "react";
import LoginFinal from "./pages/Login";
import { StyledEngineProvider, CssVarsProvider } from "@mui/joy/styles";
import Home from "./pages/Home";
import { Route, Routes } from "react-router-dom";

const App = () => {
  return (
    <>
      <StyledEngineProvider injectFirst>
        <CssVarsProvider>
          <Routes>
            <Route path='/login' element={<LoginFinal />} />
            <Route path='/' element={<Home />} />
          </Routes>
        </CssVarsProvider>
      </StyledEngineProvider>
    </>
  );
};

export default App;
