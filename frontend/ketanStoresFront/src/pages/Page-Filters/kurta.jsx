import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../../components/Navbar'
import Footer from '../../components/Footer'
import HomeSection from '../../components/Card'


export default function kurta() {
  return (
    <>
        <Navbar/>
        {/* <div>
            <div><Link to='/ketan-stores/men/kurta/silk'>Silk</Link></div>
            <div><Link to='/ketan-stores/men/kurta/cotton'>Cotton</Link></div>
        </div> */}
        <HomeSection/>
        <Footer/>
    </>
  )
}
