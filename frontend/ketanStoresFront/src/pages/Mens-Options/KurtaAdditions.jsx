import React from 'react'
import { Form, Link } from 'react-router-dom'
import { useColorScheme } from "@mui/joy/styles";
import Sheet from "@mui/joy/Sheet";
import CssBaseline from "@mui/joy/CssBaseline";
import Typography from "@mui/joy/Typography";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import Input from "@mui/joy/Input";
import Button from "@mui/joy/Button";
import RadioGroup from '@mui/material/RadioGroup';
import Radio from '@mui/material/Radio';
import { useState } from "react";
import { useGlobalContext } from "../../context";
import axios from "axios";

export default function KurtaAdditions() {
  const { displayClothKurtaOptions, optionsKurta, jwt } = useGlobalContext();
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [quantity, setQuantity] = useState("");
  const [color, setColor] = useState("");
  const [size, setSize] = useState("");
  const [image, setImage] = useState(null);
  const [type, setType] = useState("");

  const handleBothSilk_Cotton = async (e) => {
    e.preventDefault();
    console.log(optionsKurta);
    if (optionsKurta === "silk") {
      handleSilk(e.target.value);
    } else {
      handleCotton(e.target.value);
    }
  };

  // Silk Post routes
  const handleSilk = async (e) => {
    console.log(name, price, quantity, color, size, image, type);
    const formData = new FormData();
    formData.append("name", name);
    formData.append("img", image);
    formData.append("price", Number(price));
    formData.append("quantity", Number(quantity));
    formData.append("colour", color);
    formData.append("size", Number(size));
    formData.append("type", type.toUpperCase());
    try {
      const response = await axios.post(
        "http://localhost:8080/kurta_silk/silk",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      const data = await response;
      console.log(data);
      if (data) {
        console.log(data);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleCotton = async (e) => {
    console.log(name, price, quantity, color, size, image, type);
    const formData = new FormData();
    formData.append("name", name);
    formData.append("img", image);
    formData.append("price", Number(price));
    formData.append("quantity", Number(quantity));
    formData.append("colour", color);
    formData.append("size", Number(size));
    formData.append("type", type.toUpperCase());
    try {
      const response = await axios.post(
        "http://localhost:8080/kurta_cotton/cotton",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      const data = await response;
      console.log(data);
      if (data) {
        console.log(data);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <FormControl>
      <div className='flex-col justify-evenly h-full w-full mt-2'>
        <div className='flex-col justify-left pl-1 items-center gap-2'>
          <Typography
            level='h6'
            component='h1'
            className='flex items-center h-[22px]'
          >
            <span className='text-xs'>3.</span>
            <FormLabel className='pl-2'>Choose from Kurta:</FormLabel>
          </Typography>
          <div className='flex justify-center items-center w-full mt-2'>
            <RadioGroup
              row
              aria-labelledby='demo-row-radio-buttons-group-label'
              name='row-radio-buttons-group-2'
            >
              <div className='flex justify-center items-center gap-12'>
                <div className='flex justify-left items-center h-[20px]'>
                  <input
                    type='radio'
                    value='silk'
                    name='kurta'
                    onClick={(e) => {
                      displayClothKurtaOptions(e.target.value);
                    }}
                  />
                  <Typography level='body-sm' className='pl-2'>
                    Silk
                  </Typography>
                </div>
                <div className='flex justify-left items-center h-[20px]'>
                  <input
                    type='radio'
                    value='cotton'
                    name='kurta'
                    onClick={(e) => displayClothKurtaOptions(e.target.value)}
                  />
                  <Typography level='body-sm' className='pl-2'>
                    Cotton
                  </Typography>
                </div>
              </div>
            </RadioGroup>
          </div>
        </div>
      </div>
      <FormControl>
        <div className='flex-col justify-evenly h-full w-full mt-2 pl-1'>
          <div>
            <FormControl>
              <Typography
                level='h6'
                component='h1'
                className='flex items-center h-[22px] mt-2'
              >
                <span className='text-xs'>4.</span>
                <FormLabel className='pl-2'>Name:</FormLabel>
              </Typography>
              <Input
                name='name'
                type='name'
                onChange={(e) => {
                  setName(e.target.value.trim());
                }}
              />
            </FormControl>
            <div className='flex justify-left h-full w-full mt-2 items-center gap-12'>
              <FormControl className='flex-col justify-left h-full mt-2 items-start gap-2'>
                <Typography
                  level='h6'
                  component='h1'
                  className='flex items-center justify-left h-[22px]'
                >
                  <span className='text-xs'>5.</span>
                  <FormLabel className='pl-2'>Color:</FormLabel>
                </Typography>
                <div className='flex justify-left items-center gap-12'>
                  <Input
                    style={{ width: 206 }}
                    name='type'
                    type='text'
                    onChange={(e) => {
                      setColor(e.target.value.trim());
                    }}
                  />
                </div>
              </FormControl>
              <FormControl className='flex-col justify-left h-full mt-2 items-start gap-2'>
                <Typography
                  level='h6'
                  component='h1'
                  className='flex items-center justify-left h-[22px]'
                >
                  <span className='text-xs'>6.</span>
                  <FormLabel className='pl-2'>Size:</FormLabel>
                </Typography>
                <div className='flex justify-left items-center gap-5'>
                  <Input
                    style={{ width: 206 }}
                    name='type'
                    type='text'
                    onChange={(e) => {
                      if (!isNaN(e.target.value) && e.target.value === "") {
                        setSize(Number(e.target.value))
                      }
                      else{
                        setSize("");
                      }
                    }}
                  />
                </div>
              </FormControl>
            </div>
            <div className='flex justify-left h-full w-full mt-2 items-center gap-12'>
              <FormControl className='flex-col justify-left h-full mt-2 items-start gap-2'>
                <Typography
                  level='h6'
                  component='h1'
                  className='flex items-center justify-left h-[22px]'
                >
                  <span className='text-xs'>7.</span>
                  <FormLabel className='pl-2'>Quantity:</FormLabel>
                </Typography>
                <div className='flex justify-left items-center gap-12'>
                  <Input
                    style={{ width: 206 }}
                    name='type'
                    type='text'
                    onChange={(e) => {
                      if (!isNaN(e.target.value) && e.target.value === "") {
                        setQuantity(Number(e.target.value))
                      }
                      else{
                        setQuantity("");
                      }
                    }}
                  />
                </div>
              </FormControl>
              <FormControl className='flex-col justify-left h-full mt-2 items-start gap-2'>
                <Typography
                  level='h6'
                  component='h1'
                  className='flex items-center justify-left h-[22px]'
                >
                  <span className='text-xs'>8.</span>
                  <FormLabel className='pl-2'>Price:</FormLabel>
                </Typography>
                <div className='flex justify-left items-center gap-5'>
                  <span className='text-xl w-[10px] h-[30px]'>₹</span>
                  <Input
                    style={{ width: 165 }}
                    name='type'
                    type='text'
                    onChange={(e) => {
                      if (!isNaN(e.target.value) && e.target.value === "") {
                        setPrice(Number(e.target.value))
                      }
                      else{
                        setPrice("");
                      }
                    }}
                  />
                </div>
              </FormControl>
            </div>
            <FormControl>
              <Typography
                level='h6'
                component='h1'
                className='flex items-center justify-left h-[22px] mt-2'
              >
                <span className='text-xs'>9.</span>
                <FormLabel className='pl-2'>Image:</FormLabel>
              </Typography>
              <Input
                name='image'
                type='file'
                onChange={(e) => {
                  setImage(e.target.files[0]);
                }}
              />
            </FormControl>
            {optionsKurta === "silk" ? (
              <div className='flex-col justify-evenly h-full w-full mt-2'>
                <div className='flex-col justify-left items-center gap-2'>
                  <Typography
                    level='h6'
                    component='h1'
                    className='flex items-center h-[22px]'
                  >
                    <span className='text-xs'>10.</span>
                    <FormLabel className='pl-2'>Type:</FormLabel>
                  </Typography>
                  <div className='flex justify-center items-center w-full mt-2'>
                    <RadioGroup
                      row
                      aria-labelledby='demo-row-radio-buttons-group-label'
                      name='row-radio-buttons-group-2'
                    >
                      <div className='flex justify-center items-center gap-20'>
                        <div className='flex justify-left items-center h-[20px]'>
                          <input
                            type='radio'
                            value='plain'
                            name='silk'
                            onClick={(e) => {
                              setType(e.target.value);
                            }}
                          />
                          <Typography level='body-sm' className='pl-2'>
                            Plain
                          </Typography>
                        </div>
                        <div className='flex justify-left items-center h-[20px]'>
                          <input
                            type='radio'
                            value='print & design'
                            name='silk'
                            onClick={(e) => {
                              setType(e.target.value);
                            }}
                          />
                          <Typography level='body-sm' className='pl-2'>
                            Print & Design
                          </Typography>
                        </div>
                      </div>
                    </RadioGroup>
                  </div>
                </div>
              </div>
            ) : null}

            {optionsKurta === "cotton" ? (
              <div className='flex-col justify-evenly h-full w-full mt-2'>
                <div className='flex-col justify-left items-center gap-2 w-full mt-2'>
                  <Typography
                    level='h6'
                    component='h1'
                    className='flex items-center h-[22px]'
                  >
                    <span className='text-xs'>10.</span>
                    <FormLabel className='pl-2'>Type:</FormLabel>
                  </Typography>
                  <div className='flex justify-center items-center gap-15 w-full mt-2'>
                    <RadioGroup
                      style={{ width: 500 }}
                      row
                      aria-labelledby='demo-row-radio-buttons-group-label'
                      name='row-radio-buttons-group-2'
                    >
                      <div className='flex justify-center items-center gap-20 w-full'>
                        <div className='flex justify-left items-center h-[20px]'>
                          <input
                            type='radio'
                            value='plain'
                            name='cotton'
                            onClick={(e) => {
                              setType(e.target.value);
                            }}
                          />
                          <Typography level='body-sm' className='pl-2'>
                            Plain
                          </Typography>
                        </div>
                        <div className='flex justify-left items-center h-[20px]'>
                          <input
                            type='radio'
                            value='digital print'
                            name='cotton'
                            onClick={(e) => {
                              setType(e.target.value);
                            }}
                          />
                          <Typography level='body-sm' className='pl-2'>
                            DigitalPrint
                          </Typography>
                        </div>
                        <div className='flex justify-left items-center h-[20px]'>
                          <input
                            type='radio'
                            value='embriodery'
                            name='cotton'
                            onClick={(e) => {
                              setType(e.target.value);
                            }}
                          />
                          <Typography level='body-sm' className='pl-2'>
                            Embriodery
                          </Typography>
                        </div>
                      </div>
                    </RadioGroup>
                  </div>
                </div>
              </div>
            ) : null}
          </div>
        </div>
      </FormControl>
      {
            name === "" || price === "" || quantity === "" || size === "" || type === "" || image === null ? <Button className='bg-gray-500 hover:bg-gray-700' sx={{ mt: 2 }}>Add Item</Button>
            : <Button onClick={handleChudidar} className='bg-blue-500 hover:bg-blue-700' sx={{ mt: 2 }}>Add Item</Button>      }
    </FormControl>
  );
}
