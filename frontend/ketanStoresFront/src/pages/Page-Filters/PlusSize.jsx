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

export default function PlusSize() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const { jwt } = useGlobalContext();

  useEffect(() => {
    fetch("http://localhost:8080/other/filter/PLUS_SIZE", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${jwt}`,
      },
    })
    .then(response => response.json())
    .then(data => {
      console.log(data);
      setData(data);
      setLoading(false);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, []);

  return (
    <div>
      <main>
        <CssBaseline />
        <Sheet
          sx={{
            width: 660,
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
            <Typography level='body-sm'>Choose appropriate items using available filters:</Typography>
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
                  <Input type='text' placeholder='Enter Size' style = {{width: 170}}/>
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
                  <Input type='text' placeholder='Enter Colour' style = {{width: 170}} />
                </div>
              </div>
            </div>
          </FormControl>
          </div>
        </Sheet>
      </main>
      {
        !loading && data && data.map((item, index) => {
            console.log(item); // Check the structure of each item
            return (
              <div key={index}>
                <h1>{item.price}</h1>
                <h1>{item.size}</h1>
                <h1>{item.quantity}</h1>
                <h1>{item.type}</h1>
                <h1>{item.colour}</h1>
                <h1>{item.name}</h1>
                <Base64decode base64String={item.image} />
                {/* <img src={item.img} alt="img" /> */}
                {/* Render other properties as needed */}
              </div>
            );
          })
      }
    </div>
  );
}

//Size, colour, type