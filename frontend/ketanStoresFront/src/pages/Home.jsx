import React from 'react'
import { useGlobalContext } from '../context'

const Home = () => {
    const { mode } = useGlobalContext()
    console.log(mode)
  return (
    <div>Home</div>
  )
}

export default Home