import React from 'react'
import { Link } from 'react-router-dom'
import HomeSection from '../../components/Card'
import { useGlobalContext } from '../../context'
import { Card } from '@mui/material';
import CardPalette from '../../components/CardPalette';

export default function Men() {
  const { filterMens } = useGlobalContext();

  const kurta = filterMens.filter((item) => item.name === 'Kurta');
  const chudidar = filterMens.filter((item) => item.name === 'Chudidar');
  const jacketSuit = filterMens.filter((item) => item.name === 'Jacket Suit');
  const indoWestern = filterMens.filter((item) => item.name === 'Indo Western');
  const shortKurta = filterMens.filter((item) => item.name === 'Short Kurta');
  const plusSize = filterMens.filter((item) => item.name === 'Plus Size');

  return (
    <div className='w-full grid lg:grid-cols-3 md:grid-cols-2 sm:grid-cols-1 gap-5 my-10 px-20'>
      {/* <div className='flex flex-row justify-evenly gap-10 items-center'> */}
        <Link to='/ketan-stores/men/kurta/'>
          <CardPalette filterItems={kurta} />
        </Link>
        <Link to='/ketan-stores/men/chudidar/'>
          <CardPalette filterItems={chudidar} />
        </Link>
        <Link to='/ketan-stores/men/jacket-suit'>
          <CardPalette filterItems={jacketSuit} />
        </Link>
      {/* <div className='flex flex-row justify-evenly gap-10 items-center'> */}
        <Link to='/ketan-stores/men/indo-western'>
          <CardPalette filterItems={indoWestern} />
        </Link>
        <Link to='/ketan-stores/men/short-kurta'>
          <CardPalette filterItems={shortKurta} />
        </Link>
        <Link to='/ketan-stores/men/plus-size'>
          <CardPalette filterItems={plusSize} />
        </Link>
      {/* </div> */}
    </div>
  );
}
