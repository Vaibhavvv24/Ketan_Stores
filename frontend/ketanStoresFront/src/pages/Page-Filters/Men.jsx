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
    <div className='absolute top-[50px] h-[500px] w-full flex flex-col justify-evenly items-center'>
      <div className='flex flex-row justify-evenly gap-10 items-center'>
        <Link to='/ketan-stores/men/kurta/'>
          <CardPalette filterItems={kurta} />
        </Link>
        <Link to='/ketan-stores/men/chudidar/'>
          <CardPalette filterItems={chudidar} />
        </Link>
        <Link to='/ketan-stores/men/jacket-suit'>
          <CardPalette filterItems={jacketSuit} />
        </Link>
      </div>
      <div className='flex flex-row justify-evenly gap-10 items-center'>
        <Link to='/ketan-stores/men/indo-western'>
          <CardPalette filterItems={indoWestern} />
        </Link>
        <Link to='/ketan-stores/men/short-kurta'>
          <CardPalette filterItems={shortKurta} />
        </Link>
        <Link to='/ketan-stores/men/plus-size'>
          <CardPalette filterItems={plusSize} />
        </Link>
      </div>
    </div>
  );
}
