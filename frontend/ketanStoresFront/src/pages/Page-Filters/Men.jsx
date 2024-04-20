import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../../components/Navbar'
import Footer from '../../components/Footer'

export default function Men() {
  return (
    <>
      <Navbar/>
      <div>
          <div>
              <div><Link to='/ketan-stores/men/kurta'>Kurta</Link></div>
              <div><Link to='/ketan-stores/men/chudidar'>Chudidar</Link></div>
              <div><Link to='/ketan-stores/men/jacket-suit'>Jacket Suit</Link></div>
          </div>
          <div>
              <div><Link to='/ketan-stores/men/indo-western'>Indo Western</Link></div>
              <div><Link to='/ketan-stores/men/short-kurta'>Short Kurta</Link></div>
              <div><Link to='/ketan-stores/men/plus-size'>Plus Size</Link></div>
          </div>
      </div>
      <Footer/>
    </>
  )
}
