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
import { useGlobalContext } from "../context";
import { useNavigate } from "react-router-dom";

export default function ItemsPalette({ filterItems, Item }) {
  const { jwt } = useGlobalContext();
  // console.log(filterItems);
  const name = filterItems[0].name;
  const price = filterItems[0].price;
  const quantity = filterItems[0].quantity;
  const size = filterItems[0].size;
  const image = filterItems[0].image;
  const type = filterItems[0].type;
  const color = filterItems[0].colour;

  const handleAvailable = () => {
    if (quantity > 0) {
      return "Yes";
    } else {
      return "No";
    }
  };

  // /chudidar/update/:id
  // /kurta/cotton/update/:id
  // /kurta/silk/update/:id
  // /other/update/:id

  const handleQuantity = async (e) => {
    e.preventDefault();
    console.log(Item);
    try {
      const response = await fetch(
        `http://localhost:8080/chudidar/update/${Item.id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${jwt}`,
          },
          body: JSON.stringify({
            quantity: 11,
          }),
        }
      );
      const data = await response.json();
      console.log(data);
      if (data) {
        console.log(data);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <Card sx={{ width: "100%", height: "100%" }}>
        <div className='flex justify-evenly w-full'>
          <div className='w-1/2'>
            <AspectRatio
              minHeight='150px'
              maxHeight='250px'
              sx={{ height: "100%" }}
            >
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
              <Typography level='body-md' color='error'>
                Quantity : Outof Stock
              </Typography>
            )}
            <Typography level='body-md'>Size : {size}</Typography>
            <Typography level='body-md'>
              Available : {handleAvailable()}
            </Typography>
            {type && <Typography level='body-md'>Type : {type}</Typography>}
            {color && <Typography level='body-md'>Color : {color}</Typography>}
            <div>
              <input type='number' placeholder='Enter Quantity'/>
              <Button color='danger' className='my-3' onClick={handleQuantity}>
                Update Quantity
              </Button>
            </div>
          </div>
        </div>
      </Card>
    </div>
  );
}
