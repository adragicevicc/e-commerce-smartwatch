import React, { useContext, useEffect, useState } from 'react';
import styled from 'styled-components';
import SearchIcon from '@mui/icons-material/Search';
import ShoppingCartOutlined from '@mui/icons-material/ShoppingCartOutlined';
import { Badge, Menu } from '@mui/material';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { AuthContext } from './context/AuthContext';
import CartService from '../services/CartService';

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

    const {user, dispatch } = useContext(AuthContext);
    const navigate = useNavigate();
    console.log(user);

    const [korpa, setKorpa] = useState({});
    const [quantity, setQuantity] = useState(0);

    useEffect(() => {
        if(!user===null) {

        retriveQuantity();
        } else {
            setQuantity(0);
        }
  }, [korpa]);

    const retriveQuantity = () => {
        CartService.get(user.id_korpa).then(response => {
            setKorpa(response.data);
            console.log(response.data);

            setQuantity(korpa.broj_stavki);
            console.log(quantity);
          })
          .catch(e => {
            console.log(e);
          });
      };

    const handleLogout = async (e) => {
        e.preventDefault();
        dispatch({ type: "LOGOUT" });
        navigate('/login');
    }

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
            {user ? null : <Link to={`/register`}><MenuItem> Registruj se</MenuItem></Link>}
            {user ? null : <Link to={`/login`}><MenuItem> Prijavi se</MenuItem></Link>}
            {user ? <MenuItem onClick={handleLogout}>Odjavi se</MenuItem> : null}
            <Link to={`/cart`}>
            {user ? <MenuItem>
                        <Badge badgeContent={quantity} color="primary">
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