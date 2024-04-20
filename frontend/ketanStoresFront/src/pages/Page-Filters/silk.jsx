import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../../components/Navbar'
import Footer from '../../components/Footer'

export default function silk() {
  return (
    <>
    <Navbar/>
    <div>
        <div><Link to='/ketan-stores/men/kurta/silk/plain'>Plain</Link></div>
        <div><Link to='/ketan-stores/men/kurta/silk/print-&-design'>Print & Design</Link></div>
    </div>
    <Footer/>
    </>
  )
}
