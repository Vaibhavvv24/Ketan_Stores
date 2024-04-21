import React from 'react'
import { Link } from 'react-router-dom'
import HomeSection from '../../components/Card'
import { useGlobalContext } from '../../context'


export default function kurta() {
  const { filterKurta } = useGlobalContext();

  const silk = filterKurta.filter((item) => item.name === 'Silk');
  const cotton = filterKurta.filter((item) => item.name === 'Cotton');

  return (
    <>
      <div>
        
      </div>
    </>
  )
}
