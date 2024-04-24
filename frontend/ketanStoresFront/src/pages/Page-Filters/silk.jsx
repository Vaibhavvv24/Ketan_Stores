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
import ItemsPalette from "../../components/ItemsPalette";
import Base64decode from "../../components/Base64decode";

export default function Silk() {
  const [silkdata, setSilkData] = useState([]);
  const [loading, setLoading] = useState(true);
  const { jwt } = useGlobalContext();
  const [size, setSize] = useState("");
  const [color, setColor] = useState("");
  const [type, setType] = useState("ALL");

  useEffect(() => {
    fetch("http://localhost:8080/kurta_silk/all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${jwt}`,
      },
    })
    .then(response => response.json())
    .then(data => {
      setSilkData(data);
      setLoading(false);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, []);

  const display = (e) => {
    console.log(type, !isNaN(size), color === "");
    if (type === "ALL" && size === "" && color === "") {
      fetch("http://localhost:8080/kurta_silk/all", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${jwt}`,
        },
      })
      .then(response => response.json())
      .then(data => {
        console.log("Thala",data);
        setSilkData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
    }
    else if (type === "ALL" && !isNaN(size) && color === "") {
      console.log("ThalaSize",size);
      fetch(`http://localhost:8080/kurta_silk/size/${size}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${jwt}`,
        },
      })
      .then(response => response.json())
      .then(data => {
        console.log("ThalaNew",data);
        setSilkData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
    }
    else if (type === "ALL" && size === "" && color !== "") {
      fetch(`http://localhost:8080/kurta_silk/silk/colour_filter/${color}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${jwt}`,
        },
      })
      .then(response => response.json())
      .then(data => {
        setSilkData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
    }
    else if (type === "ALL" && !isNaN(size) && color !== "") {
      fetch(`http://localhost:8080/kurta_silk/size/${size}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${jwt}`,
        },
      })
      .then(response => response.json())
      .then(data => {
        setSilkData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
    }
    else if (type !== "ALL" && size === "" && color === "") {
      fetch(`http://localhost:8080/kurta_silk/type/${type}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${jwt}`,
        },
      })
      .then(response => response.json())
      .then(data => {
        setSilkData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
    }
    else if (type !== "ALL" && !isNaN(size) && color === "") {
      fetch(`http://localhost:8080/kurta_silk/silk_tc/${type}/size/${size}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${jwt}`,
        },
      })
      .then(response => response.json())
      .then(data => {
        setSilkData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      })
    }
    else if (type !== "ALL" && size === "" && color !== "") {
      fetch(`http://localhost:8080/kurta_silk/silk/${type}/colour/${color}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${jwt}`,
        },
      })
      .then(response => response.json())
      .then(data => {
        setSilkData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      })
    }
    else if (type !== "ALL" && size !== "" && color !== "") {
      fetch(`http://localhost:8080/kurta_silk/silk_tcs/${type}/colour/${color}/size/${size}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${jwt}`,
        },
      })
      .then(response => response.json())
      .then(data => {
        setSilkData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      })
    }
  }

  return (
    <div>
      <main>
        <CssBaseline />
        <Sheet
          sx={{
            width: "50%",
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
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
          <div className='flex justify-evenly h-full w-full mt-2 flex-wrap'>
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
                    <Select defaultValue='All' style={{ width: 170 }}>
                      <Option
                        value='All'
                        onClick={(e) => {
                          setType("ALL");
                        }}
                      >
                        All
                      </Option>
                      <Option
                        value='Plain'
                        onClick={(e) => {
                          setType("PLAIN");
                        }}
                      >
                        Plain
                      </Option>
                      <Option
                        value='Print & Design'
                        onClick={(e) => {
                          setType("PRINT_AND_DESIGN");
                        }}
                      >
                        Print & Design
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
                    {
                      type != "ALL" ?
                        <Input
                          type='text'
                          placeholder='Enter Size'
                          style={{ width: 170 }}
                          onChange={(e) =>
                          {
                            if (!isNaN(e.target.value) && e.target.value.trim() !== "") {
                              setSize(Number(e.target.value.trim()));
                            }
                            else {
                              setSize(e.target.value);
                            }
                          }
                        }
                        />
                        :
                          (type === "ALL" && color === "") || (type === "ALL" && color !== "" && size != "")?
                          <Input
                            type='text'
                            placeholder='Enter Size'
                            style={{ width: 170 }}
                            onChange={(e) =>
                            {
                              if (!isNaN(e.target.value) && e.target.value.trim() !== "") {
                                setSize(Number(e.target.value.trim()));
                              }
                              else {
                                setSize(e.target.value);
                              }
                            }
                          }
                          />
                        :
                        <Input
                          disabled
                          type='text'
                          placeholder='Enter Size'
                          style={{ width: 170 }}
                        />
                    }

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
                    <span className='text-xs'>3.</span>
                    <FormLabel className='pl-2'>Colour:</FormLabel>
                  </Typography>
                  <div className='flex justify-center items-center w-full mt-2'>
                    {(type === "ALL" && size === "") || (type !== "ALL")
                      ? 
                    <Input
                      type='text'
                      placeholder='Enter Colour'
                      style={{ width: 170 }}
                      onChange={(e) => setColor(e.target.value.trim())}
                    /> : 
                    <Input
                      disabled
                      type='text'
                      placeholder='Enter Colour'
                      style={{ width: 170 }}
                      onChange={(e) => setColor(e.target.value.trim())}
                    />
                    }
                  </div>
                </div>
              </div>
            </FormControl>
          </div>
          {
            isNaN(size) ? <Button className="bg-gray-500 hover:bg-gray-700" >Go</Button> : <Button className="bg-blue-500 hover:bg-blue-700" onClick={display}>Go</Button>
          }
        </Sheet>
      </main>
      <div className='grid grid-cols-3 w-full gap-3 px-10 h-full'>
        {!loading &&
          silkdata &&
          silkdata.map((item, index) => {
            return (
              <div key={index}>
                <ItemsPalette filterItems={[item]} />
              </div>
            );
          })}
      </div>
    </div>
  );
}

//Size, colour, type