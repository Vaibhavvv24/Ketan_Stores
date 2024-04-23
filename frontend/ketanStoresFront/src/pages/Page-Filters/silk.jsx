import React from 'react';
import Card from '@mui/joy/Card';
import Typography from '@mui/joy/Typography';
import AspectRatio from '@mui/joy/AspectRatio';
import FormLabel from "@mui/joy/FormLabel";

export default function Silk() {
  return (
    <div>
      <Card className='flex-row justify-evenly items-center' sx={{ width: 550, height : 230}}>
        <div>
          <img
            src='https://images.unsplash.com/photo-1527549993586-dff825b37782?auto=format&fit=crop&w=286'
            srcSet='https://images.unsplash.com/photo-1527549993586-dff825b37782?auto=format&fit=crop&w=286&dpr=2 2x'
            loading='lazy'
            alt=''
            className='w-[100%] h-[150px] object-cover rounded-md border border-2 border-gray-200 shadow-md'
          />
        </div>
        <div>
          <Typography level='h6' component='h1' className='flex items-center justify-left h-[22px]'>
              <FormLabel className='text-xl pl-2'>Yosemite National Park</FormLabel> 
          </Typography>
        </div>
      </Card>
    </div>
  );
}
