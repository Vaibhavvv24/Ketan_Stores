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
import { useGlobalContext } from '../../context';


export default function ChudidarAdditions() {
  return (
      <FormControl>
            <div className='flex-col justify-evenly h-full w-full mt-2;'>
                <div>
                    <Typography level='h6' component='h1'>
                    <FormLabel>Name</FormLabel>
                        <Input
                            // html input attribute
                            name='name'
                            type='name'
                            onClick={ () => {
                            
                            }}
                        />
                    </Typography>
                    <FormLabel>Price</FormLabel>
                    <Input
                        name = 'price'
                        type = 'number'
                    />
                    <FormLabel>Quantity</FormLabel>
                    <Input
                        name = 'quantity'
                        type = 'number'
                    />
                    <FormLabel>Type</FormLabel>
                    <Input
                        name = 'type'
                        type = 'text'
                    />
                    <FormLabel>Size</FormLabel>
                    <Input
                        name = 'size'
                        type = 'text'
                    />
                    <FormLabel>Image</FormLabel>
                    <Input
                        name = 'image'
                        type = 'file'
                    />
                </div>
            </div>
            <Button className='bg-blue-500' sx={{ mt: 2 }}>Add Item</Button> 
      </FormControl>
  )
}
//name, image (img) json object, price, quantity, type, size
