/** @format */

import React from "react";
import { Link } from "react-router-dom";
import { useColorScheme } from "@mui/joy/styles";
import Sheet from "@mui/joy/Sheet";
import CssBaseline from "@mui/joy/CssBaseline";
import Typography from "@mui/joy/Typography";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import Input from "@mui/joy/Input";
import Button from "@mui/joy/Button";
import RadioGroup from "@mui/material/RadioGroup";
import Radio from "@mui/material/Radio";
import { useGlobalContext } from "../context";
import MensAddItems from "./Mens-Options/MensAddItems";

export default function AddItems() {
    const { optionsKetanStores, displayMensOptions } = useGlobalContext();
    
  return (
    <div>
      <main>
        <CssBaseline />
        <Sheet
          sx={{
            width: 500,
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
              <b>Welcome!</b>
            </Typography>
            <Typography level='body-sm'>Add Items to Inventory.</Typography>
          </div>
          <FormControl>
            <div className='flex-col justify-evenly h-full w-full'>
              <div className='flex-col justify-left pl-1 items-center gap-2'>
                <Typography level='h6' component='h1'>
                  <span className='text-xs'>1.</span>
                  <b className='pl-2'>Choose:</b>
                </Typography>
                <div className='flex justify-center items-center w-full'>
                  <RadioGroup
                    row
                    aria-labelledby='demo-row-radio-buttons-group-label'
                    name='row-radio-buttons-group'
                  >
                    <div className='flex justify-center items-center gap-12'>
                      <div className='flex justify-left items-center h-[20px]'>
                        <input
                          type='radio'
                          value='mens'
                          onClick={(e) => {
                            displayMensOptions(e.target.value);
                          }}
                          name='clothing'
                        />
                        <Typography level='body-sm' className='pl-2'>
                          Mens
                        </Typography>
                      </div>
                      <div className='flex justify-left items-center h-[20px]'>
                        <input
                          type='radio'
                          value='kids'
                          onClick={(e) => displayMensOptions(e.target.value)}
                          name='clothing'
                        />
                        <Typography level='body-sm' className='pl-2'>
                          Kids
                        </Typography>
                      </div>
                    </div>
                  </RadioGroup>
                </div>
              </div>
            </div>
            {optionsKetanStores === "mens" ? <MensAddItems /> : null}
          </FormControl>
        </Sheet>
      </main>
    </div>
  );
}
