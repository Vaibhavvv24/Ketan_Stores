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
    const {settingOthersFormProp} = useGlobalContext();

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
                                settingOthersFormProp('name', e.target.value);
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
                                    name = 'type'
                                    type = 'text'
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
                                    name = 'type'
                                    type = 'text'
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
                                    name = 'type'
                                    type = 'text'
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
                                    style = {{width: 177}}
                                    name='price'
                                    type='text'
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
                    />
                    </FormControl>
                </div>
            </div>
            {
                //:<Button className='bg-gray-500' sx={{ mt: 2 }}>Add Item</Button>
            }
            <Button className='bg-blue-500' sx={{ mt: 2 }}>Add Item</Button> 
        </FormControl>
    )
}

//name, image, price, color, quantity, type, size
