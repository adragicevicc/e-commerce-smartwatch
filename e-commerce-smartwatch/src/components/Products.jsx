import React, { useEffect, useState } from 'react'
import { products } from '../data'
import Product from './Product'
import styled from 'styled-components';
import ProductDataService from '../services/ProductService';

const Container = styled.div`
padding: 20px;
display: flex;
flex-wrap: wrap;
justify-content: space-between;
`;

const Products = ({sort}) => {

  const [products, setProducts] = useState([]);

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