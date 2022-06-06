import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import styled from 'styled-components';
import { AuthContext } from '../components/context/AuthContext';
import Navbar from '../components/Navbar';
import DeleteIcon from '@mui/icons-material/Delete';
import useFetch from '../hooks/useFetch';
import { Button } from '@mui/material';


const Container = styled.div`

`;

const Wrapper = styled.div`
    padding:20px;

`;
const Tittle = styled.h1`
    font-weight: 300;
    text-align: center;
`;

const Orders = styled.div`
    flex:2;
    padding:30px;
`;

const Order = styled.div`
    height: fit-content;
    display: flex;
    justify-content: space-between;
    padding: 20px;
    align-items: center;
`;

const OrderDetail = styled.div`
    flex:2;
    display:flex;
    flex-direction: column;
`;

const ProductName = styled.span`
    padding: 20px;
    font-size: 18px;
`;


const CustomerDetail = styled.div`
    flex:1;
    display: flex;
    justify-content: space-between;
    align-items: center;
`;

const CustomerName = styled.span`
    padding: 20px;
    font-size: 18px;
`;

const CustomerAdress = styled.span`
    padding: 20px;
    font-size: 18px;
`;



const Hr = styled.hr`
  background-color: ${(props) => props.type === "final" ? "#727171" : "#eee"};
  border: none;
  height: 1px;
`;

const DeleteContainer = styled.div`
    height: fit-content;
    align-items: center;
`;

const Dashboard = () => {

    const [orders, setOrders] = useState([]);
    const { user } = useContext(AuthContext);
    const [loading, setLoading] = useState(true);

    const getOrders = async () => {
        const res = await axios.get("http://localhost:8080/api/auth/porudzbine");
        setOrders(res.data);
        console.log(orders);
    }

    const handle = (id) => {
        try{
            console.log(id);
            axios.delete(`http://localhost:8080/api/auth/porudzbine/${id}`);
        } catch {};
        
    }

    useEffect(() => {
        getOrders();
    }, []);

    return (
        <Container>
            <Navbar />
            <Wrapper>
                <Tittle>Pregled Porudzbina</Tittle>
                <Orders>
                    {orders.map((order) => (
                        <Order id={order.id_porudzbina}>
                            <OrderDetail>{order.proizvodi.map((proizvod) => (
                                <ProductName><b>Proizvod: </b> {proizvod.nazivProizvoda}</ProductName>
                            ))}
                            </OrderDetail>
                            <CustomerDetail>
                                <CustomerName>{order.kupac.ime}</CustomerName>
                                <CustomerName>{order.kupac.prezime}</CustomerName>
                                <CustomerAdress>{order.kupac.adresa.nazivUlice}</CustomerAdress>
                                <CustomerAdress>{order.kupac.adresa.broj}</CustomerAdress>
                                <CustomerAdress>{order.kupac.adresa.grad}</CustomerAdress>
                            </CustomerDetail>
                            <DeleteContainer>
                                <Button variant="outlined" onClick={() => handle(order.id_porudzbina)}>Delete</Button>
                           </DeleteContainer>
                            <Hr/>
                        </Order>
                    ))}
                </Orders>
            </Wrapper>
        </Container>
    )
}

export default Dashboard