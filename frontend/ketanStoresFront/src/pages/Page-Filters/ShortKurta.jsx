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

export default function ShortKurta() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const { jwt } = useGlobalContext();
  const [size, setSize] = useState("");
  const [colour, setColour] = useState("");
  const [newQuantity, setNewQuantity] = useState(0);
    const size1 = size === "" ? "NULL" : size;
    const colour1 = colour === "" ? "NULL" : colour;
    const applied = `Applied Filters => Color : ${colour1},  Size : ${size1}`;

  useEffect(() => {
    fetch("http://localhost:8080/other/filter/SHORT_KURTA", {
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
  }, []);

  const handleJacketsAndSuits = (e) => {
    if (colour === "" && size === "") {
      fetch("http://localhost:8080/other/filter/SHORT_KURTA", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${jwt}`,
        },
      })
        .then ((response) => response.json())
        .then ((data) => {
          setData(data);
          setLoading(false);
        })
        .catch ((error) => {
          console.error("Error fetching data:", error);
        });
    }
    if (colour === "" && !isNaN(size)) {
      fetch(`http://localhost:8080/other/SHORT_KURTA/filter/${size}`, {
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
    else if (colour !== "" && size === "") {
      fetch(`http://localhost:8080/other/SHORT_KURTA/colour/${colour}`, {
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
      fetch(`http://localhost:8080/other/SHORT_KURTA/filter/${size}/colour/${colour}`, {
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
  }

  return (
    <div className='flex flex-wrap flex-col justify-center items-center'>
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
                    <FormLabel className='pl-2'>Size:</FormLabel>
                  </Typography>
                  <div className='flex justify-center items-center w-full mt-2'>
                    <Input
                      type='text'
                      placeholder='Enter Size'
                      style={{ width: 170 }}
                      onChange={(e) => {
                        if (
                          !isNaN(e.target.value) &&
                          e.target.value.trim() !== ""
                        ) {
                          setSize(Number(e.target.value.trim()));
                        } else {
                          setSize(e.target.value);
                        }
                      }}
                    />
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
                    <FormLabel className='pl-2'>Colour:</FormLabel>
                  </Typography>
                  <div className='flex justify-center items-center w-full mt-2'>
                    <Input
                      type='text'
                      placeholder='Enter Colour'
                      style={{ width: 170 }}
                      onChange={(e) => setColour(e.target.value.trim())}
                    />
                  </div>
                </div>
              </div>
            </FormControl>
          </div>
          {isNaN(size) ? (
            <Button className='bg-gray-500 hover:bg-gray-700'>Go</Button>
          ) : (
            <Button
              className='bg-grey-500 hover:bg-grey-700'
              onClick={handleJacketsAndSuits}
            >
              Go
            </Button>
          )}
        </Sheet>
      </main>
      <div className='text-center my-5'> {applied}</div>
      {loading && (
        <div className='w-full font-semibold text-4xl text-center'>
          Loading...
        </div>
      )}
      <div className='grid grid-cols-2 w-full gap-3 px-10 h-full'>
        {!loading &&
          data &&
          data.map((item, index) => {
            return (
              <div key={index}>
                <ItemsPalette
                  filterItems={[item]}
                  Item={data[index]}
                  newQuantity={newQuantity}
                  setNewQuantity={setNewQuantity}
                  Type={"other"}
                />
              </div>
            );
          })}
      </div>
    </div>
  );
}
