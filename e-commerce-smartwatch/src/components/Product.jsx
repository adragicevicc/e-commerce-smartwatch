import { SearchOutlined, ShoppingCartOutlined } from '@mui/icons-material';
import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import styled from "styled-components";
import { AuthContext } from './context/AuthContext';

const Info = styled.div`
  opacity: 0;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.2);
  z-index: 3;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.5s ease;
  cursor: pointer;
`;

const Container = styled.div`
  flex: 1;
  margin: 5px;
  min-width: 280px;
  height: 350px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #f5fbfd;
  position: relative;
  &:hover ${Info}{
    opacity: 1;
  }
`;

const Image = styled.img`
  height: 75%;
  z-index: 2;
`;

const Icon = styled.div`
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px;
  transition: all 0.5s ease;
  &:hover {
    background-color: #e9f5f5;
    transform: scale(1.1);
  }
`;

const Details=styled.div`
  width: 90%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
`;


const Name=styled.span`
  font-size: 20px;
  font-weight: 450;
  flex:1;
  text-align: center;
`;
const Price=styled.span`
  font-size: 20px;
  font-weight: 300;
  flex:1;
  text-align: center;
`;

const Product = ({item}) => {

  const {user} = useContext(AuthContext);

  return (
    <Container>
      <Image src={item.url}/>
        <Info>
            <Icon>
              {user ? <Link to={`/product/${item.id_proizvod}`}><SearchOutlined/></Link> : <Link to={`/login`}><SearchOutlined/></Link>}
            </Icon>
        </Info>
        <Details>
          <Name>{item.nazivProizvoda}</Name>
          <Price>{item.cena}$</Price>
        </Details>
    </Container>
  )
}

export default Product