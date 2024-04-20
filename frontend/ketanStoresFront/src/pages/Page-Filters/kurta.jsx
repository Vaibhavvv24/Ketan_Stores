import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../../components/Navbar'
import Footer from '../../components/Footer'

export default function kurta() {
  return (
    <>
        <Navbar/>
        <div>
            <div><Link to='/ketan-stores/men/kurta/silk'>Silk</Link></div>
            <div><Link to='/ketan-stores/men/kurta/cotton'>Cotton</Link></div>
        </div>
        <Footer/>
    </>
  )
}
