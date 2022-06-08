import React, { useContext } from 'react'
import { AuthContext } from '../components/context/AuthContext'
import Footer from '../components/Footer'
import Navbar from '../components/Navbar'
import Slider from '../components/Slider'

const Home = () => {

  return (
    <div>
        <Navbar></Navbar>
        <Slider></Slider>
        <Footer></Footer>
    </div>
  )
}

export default Home