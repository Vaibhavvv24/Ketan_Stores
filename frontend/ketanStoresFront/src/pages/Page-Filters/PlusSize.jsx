/** @format */

import React, { useEffect } from "react";
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
import Select from "@mui/joy/Select";
import Option from "@mui/joy/Option";
import { useState } from "react";
import { useGlobalContext } from "../../context";
import ItemsPalette from "../../components/ItemsPalette";
import Base64decode from "../../components/Base64decode";

export default function PlusSize() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [newQuantity, setNewQuantity] = useState(0);
  const { jwt } = useGlobalContext();

  useEffect(() => {
    fetch("http://localhost:8080/other/filter/PLUS_SIZE", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwt}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setData(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <>
      {loading && (
        <div className='w-full font-semibold text-4xl text-center'>
          Loading...
        </div>
      )}
      <div className='grid grid-cols-2 w-full gap-3 px-10 h-full'>
        {!loading &&
          data &&
          data.map((item, index) => {
            return (
              <div key={index}>
                <ItemsPalette
                  filterItems={[item]}
                  Item={data[index]}
                  newQuantity={newQuantity}
                  setNewQuantity={setNewQuantity}
                  Type={"other"}
                />
              </div>
            );
          })}
      </div>
    </>
  );
}

//Size, colour, type