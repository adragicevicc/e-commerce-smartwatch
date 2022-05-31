import React, { useEffect, useState } from 'react'
import { products } from '../data'
import Product from './Product'
import styled from 'styled-components';
import axios from 'axios';
import ProductDataService from '../services/ProductService';

const Container = styled.div`
padding: 20px;
display: flex;
flex-wrap: wrap;
justify-content: space-between;
`;

const Products = ({sort}) => {

  const [products, setProducts] = useState([]); 
  //const [filteredProducts, setFilteredProducts] = useState([]);

  /*useEffect(() => {
    const getProducts = async()=>{
      try {
        const res = await axios.get('http://localhost:8080/api/auth/proizvodi');
        console.log(res);
        setProducts(res.data);
      } catch (error) {}
    };
    getProducts();
  }, []);*/
  
  

  useEffect(() => {
    retrieveProducts();
  }, []);

  

  const retrieveProducts = () => {
    ProductDataService.getAll()
      .then(response => {
        setProducts(response.data);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  useEffect(() => {
    if (sort === "asc") {
      setProducts((prev) =>
        [...prev].sort((a, b) => a.cena - b.cena)
      );
    } else {
      setProducts((prev) =>
        [...prev].sort((a, b) => b.cena - a.cena)
      );
    }
  }, [sort]);



  return (
    <Container>
        {products.map((item) => (
        <Product item={item} key={item.id} />
      ))}
    </Container>
  )
}

export default Products