import React, { useContext, useEffect, useState } from "react";

const AppContext = React.createContext();

const AppProvider = ({ children }) => {

    // const [filterArray, setFilterArray] = useState([]);

    // const [filterObject, setFilterObject] = useState({
    //   categories: [
    //     {
    //       lastName: "men",
    //       url: "/ketan-stores/men",
    //       name: "Mens",
    //       filter: [
    //         [
    //           {
    //             lastName: "kurta",
    //             Name: "Kurta",
    //           },
    //           {
    //             lastName: "chudidar",
    //             Name: "Chudidar",
    //           },
    //           {
    //             lastName: "jacket-suit",
    //             Name: "Jacket Suit",
    //           },
    //         ],
    //         [
    //           {
    //             lastName: "indo-western",
    //             Name: "Indo Western",
    //           },
    //           {
    //             lastName: "short-kurta",
    //             Name: "Short Kurta",
    //           },
    //           {
    //             lastName: "plus-size",
    //             Name: "Plus Size",
    //           },
    //         ],
    //       ],
    //     },
    //     {
    //       lastName: "kids",
    //       url: "/ketan-stores/kids",
    //       name: "Kids",
    //       filter: [],
    //     },
    //     {
    //       lastName: "kurta",
    //       url: "/ketan-stores/men/kurta",
    //       name: "Kurta",
    //       filter: [
    //         [
    //           {
    //             lastName: "silk",
    //             Name: "Silk",
    //           },
    //           {
    //             lastName: "cotton",
    //             Name: "Cotton",
    //           }
    //         ],
    //       ],
    //     },
    //     {
    //       lastName: "chudidar",
    //       url: "/ketan-stores/men/chudidar",
    //       name: "Chudidar",
    //       filter: [
    //         [
    //           {
    //             lastName: "white",
    //             Name: "White",
    //           },
    //           {
    //             lastName: "cream",
    //             Name: "Cream",
    //           },
    //           {
    //             lastName: "colour",
    //             Name: "Colour",
    //           },
    //         ],
    //       ],
    //     },
    //     {
    //       lastName: "ketan-stores",
    //       url: "/ketan-stores/",
    //       name: "Ketan Stores",
    //       filter: [
    //         [
    //           {
    //             lastName: "men",
    //             Name: "Men",
    //           },
    //           {
    //             lastName: "kids",
    //             Name: "Kids",
    //           },
    //         ],
    //       ],
    //     },
    //   ],
    // });


    const [filterObject, setFilterObject] = useState({
        categories: [
            {
                url: "/ketan-stores/",
                name: "Ketan Stores",
                width : 320
            },
            {
                url: "/ketan-stores/men",
                name: "Mens",
                width : 450
            },
            {
                url: "/ketan-stores/kids",
                name: "Kids",
                width : 450
            },
            {
                url: "/ketan-stores/men/kurta/",
                name: "Kurta",
                width : 320
            },
            {
                url: "/ketan-stores/men/chudidar/",
                name: "Chudidar",
                width : 320
            },
            {
                url: "/ketan-stores/men/jacket-suit",
                name: "Jacket Suit",
                width : 320
            },
            {
                url: "/ketan-stores/men/indo-western",
                name: "Indo Western",
                width : 320
            },
            {
                url: "/ketan-stores/men/short-kurta",
                name: "Short Kurta",
                width : 320
            },
            {
                url: "/ketan-stores/men/plus-size",
                name: "Plus Size",
                width : 320
            },
            {
                url: "/ketan-stores/men/kurta/silk",
                name: "Silk",
                width : 450
            },
            {
                url: "/ketan-stores/men/kurta/cotton",
                name: "Cotton",
                width : 450
            }
        ],
    });

    const filterMens = filterObject.categories.filter(
       (item) =>
         item.name === "Kurta" ||
         item.name === "Chudidar" ||
         item.name === "Jacket Suit" ||
         item.name === "Indo Western" ||
         item.name === "Short Kurta" ||
         item.name === "Plus Size"
    );

    const filterKurta = filterObject.categories.filter(
        (item) =>
            item.name === "Silk" ||
            item.name === "Cotton"
    );

    const filterKetanStores = filterObject.categories.filter(
        (item) =>
            item.name === "Mens" ||
            item.name === "Kids"
    );
    // const [click, setClick] = useState(false);
    // const [url, setUrl] = useState(window.location.pathname);

    // useEffect(() => {
    //   if (click === true) {
    //     setUrl(window.location.pathname);
    //     setClick(false);
    //   }
    // }, [click]);

    // const [category, setCategories] = useState([]);

    // useEffect(() => {
    //   const urlArray = url.split("/");
    //   const { categories } = filterObject;
    //   setCategories(
    //     categories.filter(({ lastName, url, name, filter }, index) => {
    //       return (
    //         urlArray[urlArray.length - 1] === lastName && url.includes(url)
    //       );
    //     })
    //   );
    // }, [url]);

    // useEffect(() => {
    //   if (category.length > 0) {
    //     const { filter } = category[0];
    //     setFilterArray(filter);
    //   }
    // }, [category]);

    return (
      <AppContext.Provider value={{ filterObject, filterMens, filterKurta, filterKetanStores }}>
        {children}
      </AppContext.Provider>
    );
}
    
export const useGlobalContext = () => {
    return useContext(AppContext);
}

export { AppContext, AppProvider };