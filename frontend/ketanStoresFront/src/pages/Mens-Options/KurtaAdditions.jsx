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

  return (
    <FormControl>
        <div className='flex-col justify-evenly h-full w-full mt-2'>
            <div className='flex-col justify-left pl-1 items-center gap-2'>
                <Typography level='h6' component='h1' className='flex items-center h-[22px]'>
                    <span className='text-xs'>3.</span><FormLabel className='pl-2'>Choose from Kurta:</FormLabel>
                </Typography>
                <div className='flex justify-center items-center w-full mt-2'>
                    <RadioGroup
                        row
                        aria-labelledby="demo-row-radio-buttons-group-label"
                        name="row-radio-buttons-group-2"
                    >
                        <div className='flex justify-center items-center gap-12'>
                            <div className='flex justify-left items-center h-[20px]'>
                                <input type="radio" value="silk" name="kurta" />
                                <Typography level='body-sm' className='pl-2'>Silk</Typography>
                            </div>
                            <div className='flex justify-left items-center h-[20px]'>
                                <input type="radio" value="cotton" name="kurta" />
                                <Typography level='body-sm' className='pl-2'>Cotton</Typography>
                            </div>
                        </div>
                    </RadioGroup>
                </div>
            </div>
        </div>
    </FormControl>
  )
}
