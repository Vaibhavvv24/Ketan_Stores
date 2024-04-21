import React from 'react'
import { Link } from 'react-router-dom'
import HomeSection from '../../components/Card'
import { useGlobalContext } from '../../context'
import CardPalette from '../../components/CardPalette'


export default function kurta() {
  const { filterKurta } = useGlobalContext();

  const silk = filterKurta.filter((item) => item.name === 'Silk');
  const cotton = filterKurta.filter((item) => item.name === 'Cotton');

  return (
    <div className='absolute top-[50px] h-[500px] w-full flex flex-row justify-evenly items-center'>
        <Link to='/ketan-stores/men/kurta/silk'>
          <CardPalette filterItems={silk} />
        </Link>
        <Link to='/ketan-stores/men/kurta/cotton'>
          <CardPalette filterItems={cotton} />
        </Link>
    </div>
  )
}
