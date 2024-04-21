import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const AppContext = React.createContext();

const AppProvider = ({ children }) => {

    const [authToken, setAuthToken] = useState(
      localStorage.getItem("authToken")
        ? JSON.parse(localStorage.getItem("authToken"))
        : null
    );
    const Navigate = useNavigate();

    const loginUser = async (e) => {
      e.preventDefault();
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: e.target.email.value,
          password: e.target.password.value,
        }),
      });
      const data = await response.json();
      if (data.jwt) {
        setAuthToken(data);
        localStorage.setItem("authToken", JSON.stringify(data));
        Navigate("/ketan-stores/");
      }
    };

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
          width: 320,
        },
        {
          url: "/ketan-stores/men",
          name: "Mens",
          width: 400,
        },
        {
          url: "/ketan-stores/kids",
          name: "Kids",
          width: 400,
        },
        {
          url: "/ketan-stores/men/kurta/",
          name: "Kurta",
          width: 320,
        },
        {
          url: "/ketan-stores/men/chudidar/",
          name: "Chudidar",
          width: 320,
        },
        {
          url: "/ketan-stores/men/jacket-suit",
          name: "Jacket Suit",
          width: 320,
        },
        {
          url: "/ketan-stores/men/indo-western",
          name: "Indo Western",
          width: 320,
        },
        {
          url: "/ketan-stores/men/short-kurta",
          name: "Short Kurta",
          width: 320,
        },
        {
          url: "/ketan-stores/men/plus-size",
          name: "Plus Size",
          width: 320,
        },
        {
          url: "/ketan-stores/men/kurta/silk",
          name: "Silk",
          width: 400,
        },
        {
          url: "/ketan-stores/men/kurta/cotton",
          name: "Cotton",
          width: 400,
        },
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
      (item) => item.name === "Silk" || item.name === "Cotton"
    );

    const filterKetanStores = filterObject.categories.filter(
      (item) => item.name === "Mens" || item.name === "Kids"
    );

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