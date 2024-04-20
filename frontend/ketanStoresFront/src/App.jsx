/** @format */

import React from "react";
import LoginFinal from "./pages/Login";
import { StyledEngineProvider, CssVarsProvider } from "@mui/joy/styles";
import Home from "./pages/Home";
import { Route, Routes } from "react-router-dom";
import KetanStores from "./pages/Page-Filters/KetanStores";
import Men from "./pages/Page-Filters/Men";
import Kurta from "./pages/Page-Filters/kurta";
import Chudidar from "./pages/Page-Filters/chudidar";
import Silk from "./pages/Page-Filters/silk";
import Cotton from "./pages/Page-Filters/cotton";

const App = () => {
  return (
    <>
      <StyledEngineProvider injectFirst>
        <CssVarsProvider>
          <Routes>
            <Route path='/login' element={<LoginFinal />} />
            <Route path='/' element={<Home />} />
            <Route exact path='/ketan-stores' element={<KetanStores />} />
            <Route exact path='/ketan-stores/men' element={<Men />} />
            <Route exact path="/ketan-stores/men/kurta" element={<Kurta/>} />
            <Route exact path="/ketan-stores/men/chudidar" element={<Chudidar/>} />
            <Route exact path="/ketan-stores/men/kurta/silk" element={<Silk/>} />
            <Route exact path="/ketan-stores/men/kurta/cotton" element={<Cotton/>} />
          </Routes>
        </CssVarsProvider>
      </StyledEngineProvider>
    </>
  );
};

export default App;
