import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../../components/Navbar'
import Footer from '../../components/Footer'
import HomeSection from '../../components/Card'

export default function KetanStores() {
  return (
    <>
      <Navbar/>
      {/* <div>
          <div><Link to='/ketan-stores/men'>Men</Link></div>
          <div><Link to ='/ketan-stores/kids'>Kids</Link></div>
      </div> */}
      <HomeSection/>
      <Footer/>
    </>
  )
}
