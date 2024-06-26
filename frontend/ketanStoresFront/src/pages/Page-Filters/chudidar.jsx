/** @format */

import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import { useColorScheme } from "@mui/joy/styles";
import Sheet from "@mui/joy/Sheet";
import CssBaseline from "@mui/joy/CssBaseline";
import Typography from "@mui/joy/Typography";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import Input from "@mui/joy/Input";
import Button from "@mui/joy/Button";
import RadioGroup from "@mui/material/RadioGroup";
import Radio from "@mui/material/Radio";
import Select from "@mui/joy/Select";
import Option from "@mui/joy/Option";
import { useState } from "react";
import { useGlobalContext } from "../../context";
import Base64decode from "../../components/Base64decode";
import ItemsPalette from "../../components/ItemsPalette";
import SearchPalette from "../../components/SearchPalette";

export default function Chudidar() {
  const [search, setSearch] = useState("");
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [newQuantity, setNewQuantity] = useState("");
  const { jwt } = useGlobalContext();
  const [type, setType] = useState("ALL");
  const [size, setSize] = useState("");
  const size1 = size === "" ? "NULL" : size;
  const type1 = type === "ALL" ? "ALL" : type;
  const applied = `Applied Filters => Type : ${type1},  Size : ${size1}`;

  useEffect(() => {
    fetch("http://localhost:8080/churidars", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwt}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        const newData = data.map((item) => {
          return {
            id: item.id,
            name: item.name,
            price: item.price,
            quantity: item.quantity,
            size: item.size,
            image: item.image,
            type: item.type_name,
          };
        });
        // console.log(newData);
        setData(newData);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  useEffect(() => {
    if (search !== ""){
    //Implement Search Functionality
    fetch(`http://localhost:8080/chudidar/search/${search}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwt}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
    }
    else {
      fetch("http://localhost:8080/churidars", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${jwt}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          const newData = data.map((item) => {
            return {
              id: item.id,
              name: item.name,
              price: item.price,
              quantity: item.quantity,
              size: item.size,
              image: item.image,
              type: item.type_name,
            };
          });
          setData(newData);
          setLoading(false);
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    }
  }, [search]);

  const display = (e) => {
    if (type === "ALL") {
      fetch("http://localhost:8080/churidars", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${jwt}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          const newData = data.map((item) => {
            return {
              id: item.id,
              name: item.name,
              price: item.price,
              quantity: item.quantity,
              size: item.size,
              image: item.image,
              type: item.type_name,
            };
          });
          setData(newData);
          setLoading(false);
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    } else if (type !== "ALL" && size === "") {
      fetch(`http://localhost:8080/chudidar/${type}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${jwt}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          const newData = data.map((item) => {
            return {
              id: item.id,
              name: item.name,
              price: item.price,
              quantity: item.quantity,
              size: item.size,
              image: item.image,
              type: item.type_name,
            };
          });
          setData(newData);
          setLoading(false);
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    } else if (type !== "ALL" && size !== "") {
      fetch(`http://localhost:8080/chudidar/${type}/filter/${size}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${jwt}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          setData(data);
          setLoading(false);
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    }
  };

  // console.log(type, size);

  return (
    <div className="flex flex-wrap flex-col justify-center items-center" >
      <FormControl>
        <Input type='text' placeholder='Search' onChange={(e) => setSearch(e.target.value.trim())}/>
      </FormControl>
      <main>
        <CssBaseline />
        <Sheet
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-evenly",
            mx: "auto", // margin left & right
            my: 4, // margin top & bottom
            py: 3, // padding top & bottom
            px: 2, // padding left & right
            gap: 2,
            borderRadius: "sm",
            boxShadow: "md",
          }}
          variant='outlined'
        >
          <div>
            <Typography level='h4' component='h1'>
              <b>Filters:</b>
            </Typography>
            <Typography level='body-sm'>
              Choose appropriate items using available filters:
            </Typography>
          </div>
          <div className='flex justify-evenly h-full w-full mt-2'>
            <FormControl>
              <div className='flex justify-evenly h-full w-full'>
                <div className='flex-col justify-left pl-1 items-center gap-2 mt-2'>
                  <Typography
                    level='h6'
                    component='h1'
                    className='flex items-center h-[22px]'
                  >
                    <span className='text-xs'>1.</span>
                    <FormLabel className='pl-2'>Type:</FormLabel>
                  </Typography>
                  <div className='flex justify-center items-center w-full mt-2'>
                    <Select defaultValue='All' style={{ width: 200 }}>
                      <Option
                        value='All'
                        onClick={(e) => {
                          setType("ALL");
                        }}
                      >
                        All
                      </Option>
                      <Option
                        value='White'
                        onClick={(e) => {
                          setType("WHITE");
                        }}
                      >
                        White
                      </Option>
                      <Option
                        value='Cream'
                        onClick={(e) => {
                          setType("CREAM");
                        }}
                      >
                        Cream
                      </Option>
                      <Option
                        value='Colour'
                        onClick={(e) => {
                          setType("COLOUR");
                        }}
                      >
                        Colour
                      </Option>
                    </Select>
                  </div>
                </div>
              </div>
            </FormControl>
            <FormControl>
              <div className='flex justify-evenly h-full w-full'>
                <div className='flex-col justify-left pl-1 items-center gap-2 mt-2'>
                  <Typography
                    level='h6'
                    component='h1'
                    className='flex items-center h-[22px]'
                  >
                    <span className='text-xs'>2.</span>
                    <FormLabel className='pl-2'>Size:</FormLabel>
                  </Typography>
                  <div className='flex justify-center items-center w-full mt-2'>
                    {type.trim() === "" ? (
                      <Input
                        disabled
                        type='text'
                        placeholder='Enter Size'
                        style={{ width: 200 }}
                      />
                    ) : (
                      <Input
                        type='text'
                        placeholder='Enter Size'
                        style={{ width: 200 }}
                        onChange={(e) => {
                          if (!isNaN(e.target.value) && e.target.value !== "") {
                            setSize(Number(e.target.value.trim()));
                          } else {
                            setSize(e.target.value.trim());
                          }
                        }}
                      />
                    )}
                  </div>
                </div>
              </div>
            </FormControl>
          </div>
          {isNaN(size) && type !== "" ? (
            <Button className='bg-gray-500 hover:bg-gray-700'>Go</Button>
          ) : (
            <Button className='bg-grey-500 hover:bg-grey-700' onClick={display}>
              Go
            </Button>
          )}
        </Sheet>
      </main>
      <div className='text-center my-5'> { applied }</div>
      <div>
        <SearchPalette data={ data } />
      </div>
      {loading && (
        <div className='w-full font-semibold text-4xl text-center'>
          Loading...
        </div>
      )}
      <div className='grid lg:grid-cols-2 w-full gap-3 px-10 h-full sm:grid-cols-1'>
        {!loading &&
          data &&
          data.map((item, index)=> {
            return (
              ((search !== "" && item.name.toLowerCase().includes(search.toLowerCase())) || search === "") && <div key={index}>
                <ItemsPalette
                  filterItems={[item]}
                  Item={data[index]}
                  newQuantity={newQuantity}
                  setNewQuantity={setNewQuantity}
                  Type={"chudidar"}
                />
              </div>
            );
          })}
      </div>
    </div>
  );
}

//Size, colour, type
