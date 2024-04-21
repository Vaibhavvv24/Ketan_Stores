import React from 'react'
import { useGlobalContext } from '../context'
import Navbar from '../components/Navbar'
import Footer from '../components/Footer'
import HomeSection from '../components/Card'

const Home = () => {
    return (
    <div className=''>
        <Navbar />
        <HomeSection/>
        <Footer/>
    </div>
    );
}

export default Home