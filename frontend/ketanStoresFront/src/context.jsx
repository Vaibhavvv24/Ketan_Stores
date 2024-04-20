import React, { useContext, useEffect, useState } from "react";

const AppContext = React.createContext();

const AppProvider = ({ children }) => {
    const [mode, setMode] = useState("light");
    
    useEffect(() => {
        const savedMode = localStorage.getItem("mode");
        if (savedMode) {
        setMode(savedMode);
        }
    }, []);
    
    useEffect(() => {
        localStorage.setItem("mode", mode);
    }, [mode]);
    
    return (
        <AppContext.Provider value={{ mode, setMode }}>
        {children}
        </AppContext.Provider>
    );
}
    
export const useGlobalContext = () => {
    return useContext(AppContext);
}

export { AppContext, AppProvider };