import React, { useContext, useEffect, useState } from 'react';
import styled from 'styled-components';
import SearchIcon from '@mui/icons-material/Search';
import ShoppingCartOutlined from '@mui/icons-material/ShoppingCartOutlined';
import { Badge, Menu } from '@mui/material';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { AuthContext } from './context/AuthContext';

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

const AdminDash = styled.h3`

`;

const Navbar = () => {

    const {user, dispatch } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogout = async (e) => {
        e.preventDefault();
        dispatch({ type: "LOGOUT" });
        navigate('/login');
    }

    let uloga = user.uloge[0];

    return (
    <Container>
        <Wrapper>
            <Left>
                <SearchContainer>
                    <Input/>
                    <SearchIcon style={{color:"gary", fontSize:16}}/>
                </SearchContainer>
            </Left>
            <Center>
                <Link to={`/`} style={{ textDecoration: 'none', color: 'black' }}><Logo>SmartWatch</Logo></Link>
            </Center>
            <Right>
            {user ? null : <Link to={`/register`}><MenuItem> Registruj se</MenuItem></Link>}
            {user ? null : <Link to={`/login`}><MenuItem> Prijavi se</MenuItem></Link>}
            {user ? <MenuItem onClick={handleLogout}>Odjavi se</MenuItem> : null}
            <Link to={`/cart`} style={{ color: 'black' }}>
            {uloga === 'ROLE_ADMIN' ? 
                <Link to='/dashboard'>
                    <MenuItem>Dashboard</MenuItem>
                </Link> : null}
            {user && uloga != 'ROLE_ADMIN' ? <MenuItem>
                        <Badge color="primary">
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