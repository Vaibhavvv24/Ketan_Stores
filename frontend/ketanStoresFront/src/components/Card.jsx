import CardPalette from "./CardPalette"
import React from 'react'
import AspectRatio from '@mui/joy/AspectRatio';
import Card from '@mui/joy/Card';
import Typography from '@mui/joy/Typography';
import { AppContext } from "../context";
import { useGlobalContext } from "../context";

import { Link } from "react-router-dom";

const HomeSection = () => {
  const {filterArray, setClick} = useGlobalContext();
  const url = window.location.href;
  
  return (
    <div className="absolute top-[100px] bottom-[100px] w-full flex-col justify-evenly">{
    filterArray.length && filterArray.map((item, index) => {
    return (<div key = {index} className="mt-[40px] flex justify-evenly w-full">
    {item.length && item.map(({Name, lastName}, indexNew) => {
      return (
        <Card key = {indexNew} sx={{ width: 320 }}>
        <div>
          <Typography level="title-lg"><Link onClick={() => setClick(true)} to={`${url}/${lastName}`}>{Name}</Link></Typography>
        </div>
        <AspectRatio minHeight="120px" maxHeight="200px">
          <img
            src="https://images.unsplash.com/photo-1527549993586-dff825b37782?auto=format&fit=crop&w=286"
            srcSet="https://images.unsplash.com/photo-1527549993586-dff825b37782?auto=format&fit=crop&w=286&dpr=2 2x"
            loading="lazy"
            alt=""
          />
        </AspectRatio>
      </Card>
      )
    })}
    </div>)
    })
    }
    </div>
  )
}

export default HomeSection