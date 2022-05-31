import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import SearchIcon from '@mui/icons-material/Search';
import ShoppingCartOutlined from '@mui/icons-material/ShoppingCartOutlined';
import { Badge } from '@mui/material';
import { Link, useParams } from 'react-router-dom';
import BuyerDataService from "../services/BuyerService";

const Container = styled.div`
    height: 60px;
`;

const Wrapper = styled.div`
    padding: 10px 20px;
    display:flex;
    justify-content: space-between;
`;

const Left = styled.div`
    flex:1;
    display: flex;
    align-items: center;
    `;

const SearchContainer = styled.div`
    border:0.5px solid lightgray;
    display:flex;
    align-items:center;
    margin-left: 25px;
    padding:2px;
`;

const Input = styled.input`
    border: none;
`;

const Center = styled.div`
    flex:1;
    text-align:center;
    `;

const Logo = styled.h1`
    font-weight: bold;
    `;

const Right = styled.div`
    flex:1;
    display:flex;
    align-items: center;
    justify-content:flex-end;
    `;

const MenuItem = styled.div`
    font-size:14px;
    cursor:pointer;
    margin-left:25px
    `;

const Navbar = () => {

    const [user, setUser] = useState({});
    const id = 2;

    useEffect(() => {
        getUser();
      }, []);
    
      
    
      const getUser = () => {
        BuyerDataService.get(id)
          .then(response => {
            setUser(response.data);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      };

      useEffect(() => {
        if (id)
            getUser(id);
      }, [id]);

    return (
    <Container>
        <Wrapper>
            <Left>
                <SearchContainer>
                    <Input/>
                    <SearchIcon style={{color:"gary", fontSize:16}}/>
                </SearchContainer>
            </Left>
            <Center
                ><Logo>SmartWatch</Logo>
            </Center>
            <Right>
            {user ? null : <MenuItem>Registruj se</MenuItem>}
            {user ? null : <MenuItem>Prijavi se</MenuItem>}
            <Link to={`/cart`}>
            {user ? <MenuItem>
                        <Badge badgeContent={1} color="primary">
                            <ShoppingCartOutlined/>
                        </Badge>
                    </MenuItem>
                 : null}
            </Link>
            </Right>
        </Wrapper>
    </Container>
  )
}

export default Navbar