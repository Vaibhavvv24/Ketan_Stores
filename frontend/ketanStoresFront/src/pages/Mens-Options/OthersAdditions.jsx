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

export default function OthersAdditions() {
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
                            onClick={ () => {    
                            }}
                        />
                    </Typography>
                    </FormControl>
                    <FormControl>
                    <FormLabel>Color</FormLabel>
                    <Input
                        name = 'color'
                        type = 'text'
                    />
                    </FormControl>
                    <FormControl>
                    <FormLabel>Price</FormLabel>
                    <Input
                        name = 'price'
                        type = 'number'
                    />
                    </FormControl>
                    <FormControl>
                    <FormLabel>Quantity</FormLabel>
                    <Input
                        name = 'quantity'
                        type = 'number'
                    />
                    </FormControl>
                    <FormControl>
                    <FormLabel>Type</FormLabel>
                    <Input
                        name = 'type'
                        type = 'text'
                    />
                    </FormControl>
                    <FormControl>
                    <FormLabel>Size</FormLabel>
                    <Input
                        name = 'size'
                        type = 'text'
                    />
                    </FormControl>
                    <FormControl>
                    <FormLabel>Image</FormLabel>
                    <Input
                        name = 'image'
                        type = 'file'
                    />
                    </FormControl>
                </div>
            </div>
        </FormControl>
    )
}

//name, file, price, color, quantity, type, size

