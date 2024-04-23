/** @format */

import * as React from "react";
import AspectRatio from "@mui/joy/AspectRatio";
import Button from "@mui/joy/Button";
import Card from "@mui/joy/Card";
import CardContent from "@mui/joy/CardContent";
import IconButton from "@mui/joy/IconButton";
import Typography from "@mui/joy/Typography";
import BookmarkAdd from "@mui/icons-material/BookmarkAddOutlined";
import Base64decode from "./Base64decode";
import { AppContext } from "../context";
import { useNavigate } from "react-router-dom";

export default function ItemsPalette({ filterItems }) {
  console.log(filterItems);
  const name = filterItems[0].name;
  const price = filterItems[0].price;
  const quantity = filterItems[0].quantity;
  const size = filterItems[0].size;
  const image = filterItems[0].image;

  const handleAvailable = () => {
    if (quantity > 0) {
      return "Yes";
    } else {
      return "No";
    }
  };
  return (
    <div>
      <Card sx={{ width: "100%" , height : "100%" }}>
        <div className='flex justify-evenly w-full'>
          <div className='w-1/2'>
            <AspectRatio minHeight='120px' maxHeight='200px'>
              <Base64decode base64String={image} />
            </AspectRatio>
          </div>
          <div className='w-1/2 flex flex-col justify-evenly items-start ml-4'>
            <Typography level='title-lg'>{name}</Typography>
            <Typography level='body-md'>Price : ₹ {price}</Typography>
            {quantity > 0 ? (
              <Typography level='body-md'>
                {quantity > 0 ? `Quantity : ${quantity}` : ""}
              </Typography>
            ) : (
              <Typography level='body-md' color='error'>Quantity : Outof Stock</Typography>
            )}
            <Typography level='body-md'>Size : {size}</Typography>
            <Typography level='body-md'>
              Available : {handleAvailable()}
            </Typography>
          </div>
        </div>
      </Card>
    </div>
  );
}
