import { useContext, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { AuthContext } from '../components/context/AuthContext';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import StripeCheckoutButton from '../components/stripe-button/stripe.button.component';
import useFetch from "../hooks/useFetch";

const Container = styled.div`

`;

const Wrapper = styled.div`
    padding:20px;

`;
const Tittle = styled.h1`
    font-weight: 300;
    text-align: center;
`;
const Top = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 20px;
`;

const TopButton = styled.button`
    padding: 10px;
    font-weight: 600;
    cursor: pointer;
    background-color:transparent;
    color:black;
`;
const Bottom = styled.div`
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`;

const Products = styled.div`
    flex:2;
    padding:30px;
`;

const Product = styled.div`
    height: fit-content;
    display: flex;
    justify-content: space-between;
    padding: 20px;
`;

const ProductDetail = styled.div`
    flex:2;
    display:flex;
`;

const Image = styled.img`
    height: 15vh;
`;

const ProductName = styled.span`
    padding: 20px;
    font-size: 18px;
`;

const PriceDetail = styled.div`
    flex:1;
`;

const Quantity = styled.span`
    font-size: 20px;
    margin:5px;
    font-weight: normal;
    display: inline;
`;

const Price = styled.div`
    font-size: 25px;
    font-weight: 200;
`;

const Sum = styled.div`
    display: flex;
    width: 35%;
    margin: 15px 0px 0px auto;
`;

const SumTitle = styled.h1`
    flex:1;
    font-weight: 250;
    font-size: 30px;
`;


const SumPrice = styled.span`
    flex:1;
    margin: 5px 10px 0px;
    font-weight: 500;
    font-size: 24px;
    text-align: center;
`;

const Hr = styled.hr`
  background-color: ${(props) => props.type === "final" ? "#727171" : "#eee"};
  border: none;
  height: 1px;
  
`;


const Cart = () => {

    const [cart, setCart]=useState();
    let products = [];
    const {user} = useContext(AuthContext);
    const { data, load, error} = useFetch(`http://localhost:8080/api/auth/korpe/${user.id_korpa}`)
    const [loading, setLoading] = useState(true);



    console.log(user);

    useEffect(() => {
        setCart(data);
        console.log(cart);
        if (cart) {
          setLoading(false);
        }
    }, [data])

    if (cart)
        products = cart.proizvodiUKorpi
    console.log(products);

  return (
    <Container>
        <Navbar/>
        <Wrapper>
            <Tittle>Tvoja Korpa</Tittle>
            <Top>
                <Link to="/products"><TopButton>Nastavi kupovinu</TopButton></Link>
                {!loading && <StripeCheckoutButton price={cart.ukupan_iznos_korpe}/> }
            </Top>
            { !loading && <Bottom>
                <Products>
                {products.map((product) => (
                    <Product id={product.id_proizvod_u_korpi}>
                        <ProductDetail>
                            <Image src={product.proizvod.url}></Image>
                            <ProductName><b>Proizvod: </b> {product.proizvod.nazivProizvoda}</ProductName>
                        </ProductDetail>
                        <PriceDetail>
                            <Quantity><span>Količina: </span>{product.kolicina}</Quantity>
                            <Price>{product.cena_za_kolicinu}</Price>
                        </PriceDetail>
                        <Hr/>
                    </Product>
                    ))}
                </Products>
                <Hr type="final"/>
                <Sum>
                    <SumTitle>Ukupan iznos:</SumTitle>
                    <SumPrice type="total">{cart.ukupan_iznos_korpe} $</SumPrice>
                </Sum>
            </Bottom> }
        </Wrapper>
        <Footer/>
    </Container>
  )
}

export default Cart