import React from 'react'
import { Link } from 'react-router-dom'
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
import KurtaAdditions from './KurtaAdditions';
import ChudidarAdditions from './ChudidarAdditions';
import OthersAdditions from './OthersAdditions';

export default function MensAddItems() {
  const { displayKurtaOptions, optionsMens } = useGlobalContext();

  return (
    <FormControl>
      <div className='flex-col justify-evenly h-full w-full mt-2'>
        <div className='flex-col justify-left pl-1 items-center gap-2 w-full'>
          <Typography
            level='h6'
            component='h1'
            className='flex items-center h-[22px]'
          >
            <span className='text-xs'>2.</span>
            <FormLabel className='pl-2'>Choose from Mens:</FormLabel>
          </Typography>
          <div className='flex justify-center items-center w-full'>
            <RadioGroup
              style={{ width: "100%" }}
              row
              aria-labelledby='demo-row-radio-buttons-group-label'
              name='row-radio-buttons-group'
            >
              <div className='flex justify-center items-center gap-15 w-full ml-4 mt-2'>
                <div className='flex-col justify-center items-center gap-20 w-full'>
                  <div className='flex justify-left items-center h-[20px]'>
                    <input
                      type='radio'
                      value='kurta'
                      onClick={(e) => displayKurtaOptions(e.target.value)}
                      name='mens'
                    />
                    <Typography level='body-sm' className='pl-2'>
                      Kurta
                    </Typography>
                  </div>
                  <div className='flex justify-left items-center h-[20px]'>
                    <input
                      type='radio'
                      value='chudidar'
                      onClick={(e) => displayKurtaOptions(e.target.value)}
                      name='mens'
                    />
                    <Typography level='body-sm' className='pl-2'>
                      Chudidar
                    </Typography>
                  </div>
                </div>
                <div className='flex-col justify-center items-center gap-20 w-full'>
                  <div className='flex justify-left items-center h-[20px]'>
                    <input
                      type='radio'
                      value='JACKET_SUIT'
                      onClick={(e) => displayKurtaOptions(e.target.value)}
                      name='mens'
                    />
                    <Typography level='body-sm' className='pl-2'>
                      Jacket & Suits
                    </Typography>
                  </div>
                  <div className='flex justify-left items-center h-[20px]'>
                    <input
                      type='radio'
                      value='SHORT_KURTA'
                      onClick={(e) => displayKurtaOptions(e.target.value)}
                      name='mens'
                    />
                    <Typography level='body-sm' className='pl-2'>
                      Short Kurta
                    </Typography>
                  </div>
                </div>
                <div className='flex-col justify-center items-center gap-20 w-full'>
                  <div className='flex justify-left items-center h-[20px]'>
                    <input
                      type='radio'
                      value='INDO_WESTERN'
                      onClick={(e) => displayKurtaOptions(e.target.value)}
                      name='mens'
                    />
                    <Typography level='body-sm' className='pl-2'>
                      Indo Western
                    </Typography>
                  </div>
                  <div className='flex justify-left items-center h-[20px]'>
                    <input
                      type='radio'
                      value='PLUS_SIZE'
                      onClick={(e) => displayKurtaOptions(e.target.value)}
                      name='mens'
                    />
                    <Typography level='body-sm' className='pl-2'>
                      Plus Size
                    </Typography>
                  </div>
                </div>
              </div>
            </RadioGroup>
          </div>
        </div>
      </div>
      {optionsMens === "kurta" ? <KurtaAdditions /> : null}
      {optionsMens === "chudidar" ? <ChudidarAdditions /> : null}
      {optionsMens === "JACKET_SUIT" ||
      optionsMens === "SHORT_KURTA" ||
      optionsMens === "INDO_WESTERN" ||
      optionsMens === "PLUS_SIZE" ? (
        <OthersAdditions />
      ) : null}
    </FormControl>
  );
}


    // JACKET_SUIT, INDO_WESTERN, SHORT_KURTA, PLUS_SIZE;
