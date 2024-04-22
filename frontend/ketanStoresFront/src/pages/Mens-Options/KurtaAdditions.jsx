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

export default function KurtaAdditions() {
    const { displayClothKurtaOptions, optionsKurta } = useGlobalContext()
  return (
    <FormControl>
        <div className='flex-col justify-evenly h-full w-full mt-2'>
            <div className='flex-col justify-left pl-1 items-center gap-2'>
                <Typography level='h6' component='h1' className='flex items-center h-[22px]'>
                    <span className='text-xs'>4.</span><FormLabel className='pl-2'>Choose from Kurta:</FormLabel>
                </Typography>
                <div className='flex justify-center items-center w-full mt-2'>
                    <RadioGroup
                        row
                        aria-labelledby="demo-row-radio-buttons-group-label"
                        name="row-radio-buttons-group-2"
                    >
                        <div className='flex justify-center items-center gap-12'>
                            <div className='flex justify-left items-center h-[20px]'>
                                <input type="radio" value="silk" name="kurta" onClick={(e) => displayClothKurtaOptions(e.target.value)}/>
                                <Typography level='body-sm' className='pl-2'>Silk</Typography>
                            </div>
                            <div className='flex justify-left items-center h-[20px]'>
                                <input type="radio" value="cotton" name="kurta" onClick={(e) => displayClothKurtaOptions(e.target.value)}/>
                                <Typography level='body-sm' className='pl-2'>Cotton</Typography>
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
                    <Typography level='h6' component='h1' className='flex items-center h-[22px] mt-2'>
                    <span className='text-xs'>5.</span><FormLabel className='pl-2'>Name:</FormLabel>
                    </Typography>
                        <Input
                            name='name'
                            type='name'
                            onClick={ () => {    
                            }}
                        />
                    </FormControl>
                    <div className='flex justify-left h-full w-full mt-2 items-center gap-12'>
                        <FormControl className='flex-col justify-left h-full mt-2 items-start gap-2'>
                            <Typography level='h6' component='h1' className='flex items-center justify-left h-[22px]'>
                                <span className='text-xs'>6.</span><FormLabel className='pl-2'>Color:</FormLabel> 
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
                                <span className='text-xs'>7.</span><FormLabel className='pl-2'>Size:</FormLabel> 
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
                                <span className='text-xs'>8.</span><FormLabel className='pl-2'>Quantity:</FormLabel> 
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
                                <span className='text-xs'>9.</span><FormLabel className='pl-2'>Price:</FormLabel> 
                            </Typography>
                            <div className='flex justify-left items-center gap-5'>
                                <span className='text-xl w-[10px] h-[30px]'>₹</span>
                                <Input
                                    style = {{width: 177}}
                                    name = 'type'
                                    type = 'text'
                                />
                            </div>        
                        </FormControl>
                    </div>
                    <FormControl>
                        <Typography level='h6' component='h1' className='flex items-center justify-left h-[22px] mt-2'>
                            <span className='text-xs'>10.</span><FormLabel className='pl-2'>Image:</FormLabel>
                        </Typography>
                        <Input
                            name = 'image'
                            type = 'file'
                        />
                    </FormControl>             
                    {
                        optionsKurta === 'silk' ? (
                            <div className='flex-col justify-evenly h-full w-full mt-2'>
                            <div className='flex-col justify-left items-center gap-2'>
                                <Typography level='h6' component='h1' className='flex items-center h-[22px]'>
                                    <span className='text-xs'>11.</span><FormLabel className='pl-2'>Type:</FormLabel>
                                </Typography>
                                <div className='flex justify-center items-center w-full mt-2'>
                                    <RadioGroup
                                        row
                                        aria-labelledby="demo-row-radio-buttons-group-label"
                                        name="row-radio-buttons-group-2"
                                    >
                                        <div className='flex justify-center items-center gap-20'>
                                            <div className='flex justify-left items-center h-[20px]'>
                                                <input type="radio" value="plain" name="silk"/>
                                                <Typography level='body-sm' className='pl-2'>Plain</Typography>
                                            </div>
                                            <div className='flex justify-left items-center h-[20px]'>
                                                <input type="radio" value="print & design" name="silk"/>
                                                <Typography level='body-sm' className='pl-2'>Print & Design</Typography>
                                            </div>
                                        </div>
                                    </RadioGroup>
                                </div>
                            </div>
                        </div>
                        ): null}
                        
                    { optionsKurta === 'cotton' ? (<div className='flex-col justify-evenly h-full w-full mt-2'>
                        <div className='flex-col justify-left items-center gap-2 w-full mt-2'>
                            <Typography level='h6' component='h1' className='flex items-center h-[22px]'>
                                <span className='text-xs'>11.</span><FormLabel className='pl-2'>Type:</FormLabel>
                            </Typography>
                            <div className='flex justify-center items-center gap-15 w-full mt-2'>
                                <RadioGroup
                                    style={{width: 500}}
                                    row
                                    aria-labelledby="demo-row-radio-buttons-group-label"
                                    name="row-radio-buttons-group-2"
                                >
                                    <div className='flex justify-center items-center gap-20 w-full'>
                                        <div className='flex justify-left items-center h-[20px]'>
                                            <input type="radio" value="plain" name="cotton"/>
                                            <Typography level='body-sm' className='pl-2'>Plain</Typography>
                                        </div>
                                        <div className='flex justify-left items-center h-[20px]'>
                                            <input type="radio" value="digital print" name="cotton" />
                                            <Typography level='body-sm' className='pl-2'>Digital Print</Typography>
                                        </div>
                                        <div className='flex justify-left items-center h-[20px]'>
                                            <input type="radio" value="embriodery" name="cotton"/>
                                            <Typography level='body-sm' className='pl-2'>Embriodery</Typography>
                                        </div>
                                    </div>
                                </RadioGroup>
                            </div>
                        </div>
                    </div>
                        ) : null
                    }
                </div>
            </div>
        </FormControl>
        <Button className='bg-blue-500' sx={{ mt: 2 }}>Add Item</Button> 
    </FormControl>
  )
}
