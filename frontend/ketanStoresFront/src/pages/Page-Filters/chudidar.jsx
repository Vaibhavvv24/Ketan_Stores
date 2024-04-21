import React from 'react'
import Navbar from '../../components/Navbar'
import Footer from '../../components/Footer'
import { Link } from 'react-router-dom'
import HomeSection from '../../components/Card'

export default function chudidar() {
  return (
    <>
        <Navbar />
        {/* <div>
        <div><Link to='/ketan-stores/men/chudidar/white'>White</Link></div>
        <div><Link to='/ketan-stores/men/chudidar/cream'>Cream</Link></div>
        <div><Link to='/ketan-stores/men/chudidar/colour'>Colour</Link></div>
        </div> */}
        <HomeSection/>
        <Footer/>
    </>
  )
}
