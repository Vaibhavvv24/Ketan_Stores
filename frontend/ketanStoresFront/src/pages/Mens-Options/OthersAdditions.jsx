import React, { useEffect } from 'react'
import { Form, Link } from 'react-router-dom'
import { useColorScheme } from "@mui/joy/styles";
import Sheet from "@mui/joy/Sheet";
import CssBaseline from "@mui/joy/CssBaseline";
import Typography from "@mui/joy/Typography";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import Input from "@mui/joy/Input";
import Button from "@mui/joy/Button";
import { useState } from 'react';
import { useGlobalContext } from '../../context';
import axios from 'axios';

export default function OthersAdditions() {
    const { jwt, optionsMens } = useGlobalContext();
    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [size, setSize] = useState("");
    const [type, setType] = useState("");
    const [image, setImage] = useState(null);
    const [color, setColor] = useState("");

    useEffect(() => {
        setType(optionsMens);
    }, [optionsMens]);
  
    const handleChudidar = async (e) => {
        e.preventDefault();
        setType(optionsMens);
        console.log(name, color, price, quantity, type, size, image, jwt);
    const formData = new FormData();
    formData.append("name", name);
    formData.append("img", image);
    formData.append("price", Number(price));
    formData.append("quantity", Number(quantity));
    formData.append("type", type.toUpperCase());
    formData.append("size", size);
    formData.append("colour", color);
    try {
      const response = await axios.post(
        "http://localhost:8080/other",
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
          alert("Item Added Successfully");
      }
    } catch (error) {
      console.log(error);
    }
  };


  return (
        <FormControl>
            <div className='flex-col justify-evenly h-full w-full mt-2 pl-1'>
                <div>
                    <FormControl>
                    <Typography level='h6' component='h1' className='flex items-center h-[22px] mt-2'>
                    <span className='text-xs'>3.</span><FormLabel className='pl-2'>Name:</FormLabel>
                    </Typography>
                        <Input
                            name='name'
                            type='name'
                            onChange = { (e) => {
                                setName(e.target.value.trim());
                            }}
                        />
                    </FormControl>
                    <div className='flex justify-left h-full w-full mt-2 items-center gap-12'>
                        <FormControl className='flex-col justify-left h-full mt-2 items-start gap-2'>
                            <Typography level='h6' component='h1' className='flex items-center justify-left h-[22px]'>
                                <span className='text-xs'>4.</span><FormLabel className='pl-2'>Color:</FormLabel> 
                            </Typography>
                            <div className='flex justify-left items-center gap-12'>
                                <Input
                                    style = {{width: 206}}
                                    name = 'color'
                                    type = 'text'
                                    onChange = { (e) => {
                                        setColor(e.target.value.trim().toLowerCase());
                                    }}
                                />
                            </div>        
                        </FormControl>
                        <FormControl className='flex-col justify-left h-full mt-2 items-start gap-2'>
                            <Typography level='h6' component='h1' className='flex items-center justify-left h-[22px]'>
                                <span className='text-xs'>5.</span><FormLabel className='pl-2'>Size:</FormLabel> 
                            </Typography>
                            <div className='flex justify-left items-center gap-5'>
                                <Input
                                    style = {{width: 206}}
                                    name = 'size'
                                    type = 'text'
                                    onChange = { (e) => {
                                        if (!isNaN(e.target.value) && e.target.value.trim() !== ""){
                                            setSize(Number(e.target.value));
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
                            <Typography level='h6' component='h1' className='flex items-center justify-left h-[22px]'>
                                <span className='text-xs'>6.</span><FormLabel className='pl-2'>Quantity:</FormLabel> 
                            </Typography>
                            <div className='flex justify-left items-center gap-12'>
                                <Input
                                    style = {{width: 206}}
                                    name = 'quantity'
                                    type = 'text'
                                    onChange = { (e) => {
                                        if (!isNaN(e.target.value.trim()) && e.target.value.trim() !== ""){
                                            setQuantity(Number(e.target.value.trim()));
                                        }
                                        else{
                                            setQuantity("");
                                        }
                                    }}
                                />
                            </div>        
                        </FormControl>
                        <FormControl className='flex-col justify-left h-full mt-2 items-start gap-2'>
                            <Typography level='h6' component='h1' className='flex items-center justify-left h-[22px]'>
                                <span className='text-xs'>7.</span><FormLabel className='pl-2'>Price:</FormLabel> 
                            </Typography>
                            <div className='flex justify-left items-center gap-5'>
                                <span className='text-xl w-[10px] h-[30px]'>₹</span>
                                <Input
                                    style = {{width: 170}}
                                    name='price'
                                    type='text'
                                    onChange = { (e) => {
                                        if (!isNaN(e.target.value.trim()) && e.target.value.trim() !== ""){
                                            setPrice(Number(e.target.value.trim()));
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
                    <Typography level='h6' component='h1' className='flex items-center justify-left h-[22px] mt-2'>
                        <span className='text-xs'>8.</span><FormLabel className='pl-2'>Image:</FormLabel>
                    </Typography>
                    <Input
                        name = 'image'
                        type = 'file'
                        onChange = { (e) => {
                            setImage(e.target.files[0]);
                        }}
                    />
                    </FormControl>
                </div>
            </div>
            {
                name === "" || price === "" || quantity === "" || size === "" || color === "" || type === "" || image === null ? <Button className='bg-gray-500 hover:bg-gray-700' sx={{ mt: 2 }}>Add Item</Button>
                : <Button onClick={handleChudidar} className='bg-blue-500 hover:bg-blue-700' sx={{ mt: 2 }}>Add Item</Button>
            }
        </FormControl>
    )
}

//name, image, price, color, quantity, type, size
