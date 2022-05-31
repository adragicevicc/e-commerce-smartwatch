import React, { useState } from 'react'
import { useLocation } from 'react-router-dom';
import styled from "styled-components";
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import Products from '../components/Products';

const Container = styled.div``;

const FilterContainer = styled.div`
    display:flex;
    justify-content:space-between;`;

const Filter = styled.div`
    margin:20px;`;

const FilterText = styled.span`
    font-size:20px;
    font-weight:600;   
    margin-right: 20px;
`;

const Select = styled.select`
    padding: 10px;
  margin-right: 20px;`;

const Option = styled.option``;

const ProductList = () => {
    const location = useLocation();
    const [sort, setSort] = useState("desc");

return (
    <Container>
        <Navbar/>
        <FilterContainer>
            <Filter>
                <FilterText>Sortiraj Proizvode:</FilterText>
                <Select onChange={(e) => setSort(e.target.value)}>
                    <Option value="desc">Cena opadajuce</Option>
                    <Option value="asc">Cena rastuce</Option>
                </Select>
            </Filter>
        </FilterContainer>
        <Products sort={sort}/>
        <Footer/>
    </Container>
  )
}

export default ProductList