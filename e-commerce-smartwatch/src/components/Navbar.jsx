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

const AdminDash = styled.div`
    display:flex;
    justify-content:space-between;
    align-items: center;
    flex-direction: row;
    `;


const Navbar = () => {

    const {user, dispatch } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogout = async (e) => {
        e.preventDefault();
        dispatch({ type: "LOGOUT" });
        navigate('/login');
    }

    let uloga;
    {user ? uloga = user.uloge[0] : uloga=null}

    return (
    <Container>
        <Wrapper>
            <Left>
            </Left>
            <Center>
                <Link to={`/`} style={{ textDecoration: 'none', color: 'black' }}><Logo>SmartWatch</Logo></Link>
            </Center>
            <Right>
            {user ? null : <Link to={`/register`}><MenuItem> Registruj se</MenuItem></Link>}
            {user ? null : <Link to={`/login`}><MenuItem> Prijavi se</MenuItem></Link>}
            {user ? <MenuItem onClick={handleLogout}>Odjavi se</MenuItem> : null}
            {uloga === 'ROLE_ADMIN' ?
            <AdminDash>
                <Link to='/dashboard' style={{ textDecoration: 'none', color: 'black' }}>
                    <MenuItem>Dashboard</MenuItem>
                </Link>
                <Link to='/products' style={{ textDecoration: 'none', color: 'black' }}>
                    <MenuItem>Proizvodi</MenuItem>
                </Link>
                <Link to='/newProduct' style={{ textDecoration: 'none', color: 'black' }}>
                    <MenuItem>Dodaj proizvod</MenuItem>
                </Link>
            </AdminDash>
                 : null}
            {user && uloga != 'ROLE_ADMIN' ?
            <Link to={`/cart`} style={{ color: 'black' }}>
                <MenuItem>
                    <Badge color="primary">
                        <ShoppingCartOutlined/>
                    </Badge>
                </MenuItem>
            </Link>
                 : null}
            </Right>
        </Wrapper>
    </Container>
  )
}

export default Navbar