import CardPalette from "./Card"
import React from 'react'

const HomeSection = () => {
  return (
    <div className="absolute mt-[80px] flex justify-evenly w-full">
        <CardPalette />
        <CardPalette />
    </div>
  )
}

export default HomeSection