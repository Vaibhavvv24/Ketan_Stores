import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../../components/Navbar'
import Footer from '../../components/Footer'
import { useState, useEffect } from 'react'
import { useGlobalContext } from '../../context'
import { CssBaseline, Sheet, Typography, FormControl, FormLabel, Input, Button, RadioGroup, Radio, Select, Option } from '@mui/joy'
import Base64decode from '../../components/Base64decode'
import ItemsPalette from '../../components/ItemsPalette'

export default function cotton() {
  const [cottondata, setCottonData] = useState([]);
  const [loading, setLoading] = useState(true);
  const { jwt } = useGlobalContext();

  useEffect(() => {
    fetch("http://localhost:8080/kurta_cotton/all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${jwt}`,
      },
    })
    .then(response => response.json())
    .then(data => {
      console.log(data);
      setCottonData(data);
      setLoading(false);
    })
    .catch(error => {
      console.error('Error fetching data:', error);
    });
  }, [jwt]);

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
                    <span className='text-xs'>3.</span>
                    <FormLabel className='pl-2'>Type:</FormLabel>
                  </Typography>
                  <div className='flex justify-center items-center w-full mt-2'>
                    <Select defaultValue='Plain' style={{ width: 170 }}>
                      <Option
                        value='Plain'
                        onClick={(e) => {
                          setType("Plain");
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
          </div>
        </Sheet>
      </main>
      <div className='grid grid-cols-3 w-full gap-3 px-10 h-full'>
        {!loading &&
          cottondata &&
          cottondata.map((item, index) => {
            console.log(item); // Check the structure of each item
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
