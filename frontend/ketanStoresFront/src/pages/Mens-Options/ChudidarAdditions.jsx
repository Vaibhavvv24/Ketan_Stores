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

export default function ChudidarAdditions() {
  const { jwt } = useGlobalContext();
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [quantity, setQuantity] = useState("");
  const [type, setType] = useState("");
  const [size, setSize] = useState("");
  const [image, setImage] = useState(null);

  const handleChudidar = async (e) => {
    e.preventDefault();
    console.log(name, price, quantity, type, size, image);
    try {
      const response = await fetch("http://localhost:8080/chudidar", {
        method: "POST",
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${jwt}`,
        },
        body: JSON.stringify({
          name: name,
          price: price,
          quantity: quantity,
          type: type,
          size: size,
          image: image,
        }),
      });
      const data = await response.json();
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
      <div className='flex-col justify-evenly h-full w-full mt-2;'>
        <div>
          <FormControl>
            <Typography level='h6' component='h1'>
              <FormLabel>Name</FormLabel>
              <Input
                // html input attribute
                name='name'
                type='name'
                onChange={(e) => {
                  setName(e.target.value);
                }}
              />
            </Typography>
          </FormControl>
          <FormControl>
            <FormLabel>Price</FormLabel>
            <Input
              name='price'
              type='number'
              onChange={(e) => {
                setPrice(e.target.value);
              }}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Quantity</FormLabel>
            <Input
              name='quantity'
              type='number'
              onChange={(e) => {
                setQuantity(e.target.value);
              }}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Type</FormLabel>
            <Input
              name='type'
              type='text'
              onChange={(e) => {
                setType(e.target.value);
              }}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Size</FormLabel>
            <Input
              name='size'
              type='text'
              onChange={(e) => {
                setSize(e.target.value);
              }}
            />
          </FormControl>
          <FormControl>
            <FormLabel>Image</FormLabel>
            <Input
              name='image'
              type='file'
              onChange={(e) => {
                setImage(e.target.files[0]);
              }}
            />
          </FormControl>
        </div>
        <Button sx={{ mt: 1 /* margin top */ }} onClick={handleChudidar}>
          Add Item
        </Button>
      </div>
    </FormControl>
  );
}
//name, image (img) json object, price, quantity, type, size
