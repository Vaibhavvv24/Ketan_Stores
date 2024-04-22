import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const AppContext = React.createContext();

const AppProvider = ({ children }) => {

    const [authToken, setAuthToken] = useState(
      localStorage.getItem("authToken")
        ? JSON.parse(localStorage.getItem("authToken"))
        : null
    );
    const [jwt, setjwt] = useState(
      localStorage.getItem("authToken")
        ? JSON.parse(localStorage.getItem("authToken")).jwt
        : ""
    );
    const [email, setemail] = useState("");
    const [password, setpassword] = useState("");

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

    const [optionsKetanStores, setOptionsKetanStores] = useState('null');
    const [optionsMens, setOptionsMens] = useState('null');
    const [optionsKurta, setOptionsKurta] = useState('null');

    const displayMensOptions = (options) => {
      setOptionsKurta('null');
      setOptionsMens('null');
      setOptionsKetanStores(options);
    };

    const displayKurtaOptions = (options) => {
      setOptionsKurta('null')
      setOptionsMens(options);
    }

    const displayClothKurtaOptions = (options) => {
      setOptionsKurta(options);
    }

    const [othersName, setOthersName] = useState('');
    const [othersFile, setOthersFile] = useState('');
    const [othersPrice, setOthersPrice] = useState('');
    const [othersColor, setOthersColor] = useState('');
    const [othersQuantity, setOthersQuantity] = useState('');
    const [othersType, setOthersType] = useState('');
    const [othersSize, setOthersSize] = useState(42);

    const settingOthersFormProp = (parameter, value) => {
      if (parameter === 'name') {
        setOthersName(value);
      } else if (parameter === 'file') {
        setOthersFile(value);
      } else if (parameter === 'price') {
        if (!isNaN(value)) {
          setOthersPrice(value);
        }
        else {
          setOthersPrice('');
        }
      } 
      else if (parameter === 'color' ) {
        setOthersColor(value);
      } 
      else if (parameter === 'quantity') {
        if (!isNaN(value)) {
          setOthersQuantity(value);
        }
        else {
          setOthersQuantity('');
        }
      } else if (parameter === 'type') {
        setOthersType(value);
      } else if (parameter === 'size') {
        setOthersSize(value);
      }
    }

    return (
      <AppContext.Provider value={{ filterObject, filterMens, filterKurta, filterKetanStores, setemail , setpassword , email ,password , setAuthToken , optionsKetanStores, displayMensOptions, displayKurtaOptions, optionsMens, displayClothKurtaOptions, optionsKurta, jwt , setjwt,  settingOthersFormProp}}>
        {children}
      </AppContext.Provider>
    );
}
    
export const useGlobalContext = () => {
    return useContext(AppContext);
}

export { AppContext, AppProvider };