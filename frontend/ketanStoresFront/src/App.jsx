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
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Cotton from "./pages/Page-Filters/cotton";
import AddItems from "./pages/AddItems";


const App = () => {
  return (
    <>
      <StyledEngineProvider injectFirst>
        <CssVarsProvider>
          <Routes>
            <Route path='/' element={<LoginFinal />} />
            <Route
              path='/ketan-stores/'
              element={
                <>
                  <Navbar />
                  <KetanStores />
                  <Footer />
                </>
              }
            />
            <Route
              path='/ketan-stores/men/'
              element={
                <>
                  <Navbar />
                  <Men />
                  <Footer />
                </>
              }
            />
            <Route
              path='/ketan-stores/men/kurta/'
              element={
                <>
                  <Navbar />
                  <Kurta />
                  <Footer />
                </>
              }
            />
            <Route
              path='/ketan-stores/men/chudidar/'
              element={
                <>
                  <Navbar />
                  <Chudidar />
                  <Footer />
                </>
              }
            />
            <Route
              path='/ketan-stores/men/kurta/silk/'
              element={
                <>
                  <Navbar />
                  <Silk />
                  <Footer />
                </>
              }
            />
            <Route
              path='/ketan-stores/men/kurta/cotton/'
              element={
                <>
                  <Navbar />
                  <Cotton />
                  <Footer />
                </>
              }
            />
            <Route
              path='/add-items/'
              element={
                <>
                  <Navbar />
                  <AddItems />
                  <Footer />
                </>
              }
            />
          </Routes>
        </CssVarsProvider>
      </StyledEngineProvider>
    </>
  );
};

export default App;
