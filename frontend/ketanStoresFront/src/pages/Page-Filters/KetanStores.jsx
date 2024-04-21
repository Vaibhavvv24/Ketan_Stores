import React from 'react'
import { Link } from 'react-router-dom'
import HomeSection from '../../components/Card'
import { useGlobalContext } from '../../context'
import CardPalette from '../../components/CardPalette'


export default function KetanStores() {
  const { filterKetanStores } = useGlobalContext();

  const mens = filterKetanStores.filter((item) => item.name === 'Mens');
  const kids = filterKetanStores.filter((item) => item.name === 'Kids');

  return (
    <div className='absolute top-[50px] h-[500px] w-full flex flex-row justify-evenly items-center'>
        <Link to='/ketan-stores/men'>
          <CardPalette filterItems={mens} />
        </Link>
        <Link to='/ketan-stores/kids'>
          <CardPalette filterItems={kids} />
        </Link>
    </div>
  )
}
