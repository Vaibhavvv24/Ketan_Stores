import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../../components/Navbar'
import Footer from '../../components/Footer'

export default function cotton() {
  return (
    <>
        <Navbar/>
        <div>
            <div><Link to='/ketan-stores/men/kurta/cotton/plain'>Plain</Link></div>
            <div><Link to='/ketan-stores/men/kurta/cotton/digital-print'>Digital Print</Link></div>
            <div><Link to='/ketan-stores/men/kurta/cotton/embroidery'>Embriodery</Link></div> 
        </div>
        <Footer/>
    </>
  )
}
