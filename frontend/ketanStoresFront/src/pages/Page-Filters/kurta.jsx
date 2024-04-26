import React from 'react'
import { Link } from 'react-router-dom'
import HomeSection from '../../components/Card'
import { useGlobalContext } from '../../context'
import CardPalette from '../../components/CardPalette'
import { useLayoutEffect } from "react";

export default function kurta() {
  const { filterKurta } = useGlobalContext();

  const silk = filterKurta.filter((item) => item.name === "Silk");
  const cotton = filterKurta.filter((item) => item.name === "Cotton");
  useLayoutEffect(() => {
    window.scrollTo(0, 0);
  });

  return (
    <div className='w-full grid lg:grid-cols-2 md:grid-cols-1 sm:grid-cols-1 gap-5 my-10 px-20'>
      <Link to='/ketan-stores/men/kurta/silk'>
        <CardPalette filterItems={silk} />
      </Link>
      <Link to='/ketan-stores/men/kurta/cotton'>
        <CardPalette filterItems={cotton} />
      </Link>
    </div>
  );
}
