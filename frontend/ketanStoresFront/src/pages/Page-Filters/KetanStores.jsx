import React from 'react'
import { Link } from 'react-router-dom'
import HomeSection from '../../components/Card'
import { useGlobalContext } from '../../context'
import CardPalette from '../../components/CardPalette'
import DividerText from '../../components/Welcome'


export default function KetanStores() {
  const { filterKetanStores } = useGlobalContext();

  const mens = filterKetanStores.filter((item) => item.name === 'Mens');
  const kids = filterKetanStores.filter((item) => item.name === 'Kids');

  return (
    <>
      <div className='py-32'>
        <DividerText text='Ketan Stores' />
      </div>
      <div className='w-full grid lg:grid-cols-2 md:grid-cols-1 sm:grid-cols-1 gap-5 my-10 px-20'>
        <Link to='/ketan-stores/men'>
          <CardPalette filterItems={mens} />
        </Link>
        <Link to='/ketan-stores/kids'>
          <CardPalette filterItems={kids} />
        </Link>
      </div>
    </>
  );
}
