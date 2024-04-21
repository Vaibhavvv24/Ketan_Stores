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

    const [filterArray, setFilterArray] = useState([]);

    const [filterObject, setFilterObject] = useState({
        categories:[{
            lastName : "men",
            url : "/ketan-stores/men",
            name: "Mens",
            filter: [
            [{
                lastName : "kurta",
                Name : "Kurta",
            },
            {
                lastName : "chudidar",
                Name : "Chudidar",
            },
            {
                lastName: "jacket-suit",
                Name : "Jacket Suit",
            }],
            [{
                lastName : "indo-western",
                Name : "Indo Western",
            },
            {
                lastName : "short-kurta",
                Name : "Short Kurta",
            },
            {
                lastName : "plus-size",
                Name : "Plus Size",
            }]]
        },
        {
            lastName : "kids",
            url : "/ketan-stores/kids",
            name: "Kids",
            filter: [],
        },
        {
            lastName : "kurta",
            url : "/ketan-stores/men/kurta",
            name: "Kurta",
            filter: [
            [{
                lastName : "silk",
                Name : "Silk",
            },
            {
                lastName : "cotton",
                Name : "Cotton",
            }]],
        },
        {
            lastName : "chudidar",
            url : "/ketan-stores/men/chudidar",
            name: "Chudidar",
            filter: [
            [{
                lastName : "white",
                Name : "White",
            },
            {
                lastName : "cream",
                Name : "Cream",
            },
            {
                lastName : "colour",
                Name : "Colour",
            }]],
        },
        {
            lastName : "ketan-stores",
            url : "/ketan-stores",
            name: "Ketan Stores",
            filter: [
                [{
                    lastName : "men",
                    Name : "Men",
                },
                {
                    lastName : "kids",
                    Name : "Kids",
                }]
            ]
        }],
    });

    const [click, setClick] = useState(false);
    const [url,setUrl] = useState(window.location.pathname);

    useEffect(() => {
        if (click === true) {
            setUrl(window.location.pathname);
            setClick(false);
        }
    },[click])

    const [category, setCategories] = useState([]);

    useEffect(() => {
        const urlArray = url.split("/");
        const { categories } = filterObject;
        setCategories(categories.filter(({lastName, url, name, filter}, index) => {
            return urlArray[urlArray.length - 1] === lastName && url.includes(url)
        }))
    },[url])
    
    useEffect(() => {
        if (category.length > 0) {
            const {filter} = category[0];
            setFilterArray(filter);
        }
    }, [category])

    return (
        <AppContext.Provider value={{ mode, setMode, filterArray, setFilterArray}}>
        {children}
        </AppContext.Provider>
    );
}
    
export const useGlobalContext = () => {
    return useContext(AppContext);
}

export { AppContext, AppProvider };