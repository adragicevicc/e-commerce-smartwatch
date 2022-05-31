import React, { useContext, useEffect, useState } from 'react'
import styled from 'styled-components';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import { useLocation, useParams } from 'react-router-dom';
import axios from 'axios';
import ProductDataService from "../services/ProductService";
import { AuthContext } from '../components/context/AuthContext';

const Container = styled.div`

`;

const Wrapper = styled.div`
    padding:50px;
    display:flex;
    padding:100px;
`;

const ImageContainer = styled.div`
    flex:1;
    align-items:center;
`;

const Image = styled.img`
height:80%;
object-fit:cover;
margin-left:100px;
`;

const InfoContainer = styled.div`
flex:1;
padding: 0px 50px;
`;

const Title = styled.h1`
font-weight:200;
`;

const Desc = styled.p`
margin: 20px 0px;
`;

const Price = styled.span`
font-weight:200;
font-size:30px;
`;

const AddContainer = styled.div`
width:100%;
display:flex;
align-items:center;
justify-content:space-between;
`;
const AmountContainer = styled.div`
display:flex;
align-items:center;
font-weight:700;
`;
const Amount = styled.span`
    width:30px;
    height:30px;
    border-radius:10px;
    border:1px solid teal;
    display:flex;
    align-items:center;
    justify-content:center;
    margin:0px 5px;
`;
const Button = styled.button`
padding:15px;
border:1px solid teal;
background-color:white;
cursor:pointer;
align-items: center;
font-weight: 500;
font-size: medium;
  &:hover{
      background-color: #f8f4f4;
  }
`;

const Product = () => {

    const location = useLocation();
    const id = location.pathname.split("/")[2];
    const [product, setProduct] = useState({});
    const [quantity, setQuantity] = useState(1);
    const [cartId, setCartId] = useState({});
    const [cart, setCart] = useState({});

    const {user} = useContext(AuthContext);

    let headers = {};
    if (user){
     headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${user.accessToken}`,
        }
    }

    useEffect(() => {
        getProduct();
      }, []);
    
      
    
      const getProduct = () => {
        ProductDataService.get(id)
          .then(response => {
            setProduct(response.data);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      };

      useEffect(() => {
        if (id)
            getProduct(id);
      }, [id]);

    const handleQuantity = (type) => {
        if(type === 'minus'){
            quantity> 1 && setQuantity(quantity-1);
        } else {
            setQuantity(quantity+1);
        }
    };

    const handleClick = () => {
          addToCart();
        
    };

    const newdata = {
        "ukupan_iznos_korpe":50000,
        "broj_stavki":2,
        "id_kupac":user.id
    };
    const addToCart = () => {

        const putData = {
            proizvodId: id,
            kolicina: quantity
        }

        axios.put(`http://localhost:8080/api/auth/korpe/${user.id_korpa}`, putData,  {headers:headers})
        console.log(putData)
        };

  return (
    <Container>
        <Navbar/>
        <Wrapper>
            <ImageContainer>
                <Image src={product.url}/>
            </ImageContainer>
            <InfoContainer>
                <Title>{product.nazivProizvoda}</Title>
                <Desc>{product.karakteristike}</Desc>
                <Price>{product.cena}$</Price>   
                <AddContainer>
                    <AmountContainer>
                        <RemoveIcon onClick={()=>handleQuantity("minus")}/>
                        <Amount>{quantity}</Amount>
                        <AddIcon onClick={()=>handleQuantity("plus")}/>
                    </AmountContainer>
                    <Button onClick={handleClick}>Dodaj
                        <AddShoppingCartIcon fontSize='small'/>
                    </Button>
                </AddContainer>
            </InfoContainer>
        </Wrapper>
        <Footer/>
    </Container>
  )
}

export default Product