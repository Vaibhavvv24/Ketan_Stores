import React from 'react'
import { useGlobalContext } from '../context'
import Navbar from '../components/Navbar'

const Home = () => {
    const { mode } = useGlobalContext()
    console.log(mode)
    return (
    <>
        <Navbar />
        <div>Home</div>
      </>
    );
}

export default Home