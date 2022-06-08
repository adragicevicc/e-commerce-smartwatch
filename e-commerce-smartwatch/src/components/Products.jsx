import React, { useEffect, useState } from 'react'
import Product from './Product'
import styled from 'styled-components';
import axios from 'axios';
import { Pagination } from '@mui/material';

const Container = styled.div`
padding: 20px;
display: flex;
flex-wrap: wrap;
justify-content: space-between;
flex-direction: column;

`;

const ProductsContainer = styled.div`
flex:1;
display: flex;
flex-direction: row;
`;

const Paginator = styled.div`
  flex:1;
  padding: 10px;
  align-items: center;
  margin-left: 40%;
`;

const Products = ({sort}) => {

  const [products, setProducts] = useState([]);
  const [pageNo, setPageNo] = useState(0);

  
  useEffect(()=> {
    retrieveProducts();
  }, [pageNo])

  const retrieveProducts = async () => {
    try{
        const response = await axios.get(`http://localhost:8080/api/auth/proizvodi/${pageNo}/3`);
        setProducts(response.data.content);
        console.log(response.data.content);
      } catch{};
  };

  const handleChange = (event, value) => {
    setPageNo(value-1);
    console.log("USAOOO U HANDLE CHANGE");
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
      <ProductsContainer>
        {products.map((item) => (
        <Product item={item} key={item.id} />))}
      </ProductsContainer>

      <Paginator><Pagination count={3} variant="outlined" onChange={handleChange}/></Paginator>

    </Container>
  )
}

export default Products