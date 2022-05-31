import React from 'react'
import Announcement from '../components/Announcement'
import Categories from '../components/Categories'
import Footer from '../components/Footer'
import Navbar from '../components/Navbar'
import Products from '../components/Products'
import Slider from '../components/Slider'
import AddProduct from '../components/AddProduct'

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