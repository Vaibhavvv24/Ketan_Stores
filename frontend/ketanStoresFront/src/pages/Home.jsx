import React from 'react'
import { useGlobalContext } from '../context'
import Navbar from '../components/Navbar'
import Footer from '../components/Footer'
import KetanStores from './Page-Filters/KetanStores'

const Home = () => {
    return (
    <>
        <Navbar />
        <KetanStores />
        <Footer/>
            
    </>
    );
}

export default Home