import axios from 'axios';
import React, { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import StripeCheckout from 'react-stripe-checkout';
import styled from 'styled-components';
import { AuthContext } from '../context/AuthContext';

const Container = styled.div`
    height: fit-content;
`;

const StripeCheckoutButton = ({ price, id_korpa }) => {
    const stripePrice = price * 100;
    const publishableKey = 'pk_test_51L32PoFYveM0ufMdHF3HxSWmVn9bwmsYAnLHyjdoTu6NTnB46vZwn5Yh4JzP4cdZFzn5CssswKWR9Cu3vVMXpeTL00kbCCQOxB';

  const [orderId, setOrderId] = useState(null);
  const [cart, setCartId] = useState();
  const [products, setProducts] = useState([]);

  const navigate = useNavigate();

  const {user} = useContext(AuthContext);
  let headers = {};
  if (user){
    headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.accessToken}`,
    }
  }

    const createOrder = async () => {
        try {
            console.log(user);
          const res = await axios.post("http://localhost:8080/api/auth/porudzbine", {
            id_korpa: id_korpa,
            id_kupac:user.id
          }, {headers:headers});
          setOrderId(res.data._id);
        } catch {}
      };

    const refreshCart = () => {
        try {
            axios.put(`http://localhost:8080/api/auth/korpeRefresh/${user.id_korpa}`, {headers:headers});
            navigate("/products");
        } catch {}
      };


    const onToken = token => {
        axios.post('http://localhost:8080/api/auth/payment',
        {amount: stripePrice, token})
        .then((response) => {alert('Uplata uspesno izvrsena!')})
        .then(() => {createOrder()})
        .then(()=> {refreshCart()})
        .catch((error) => {alert('uplata neuspesna')})
    };

    return (
        <Container>
            <StripeCheckout
            name='SmartWatch'
            description={`Ukupan iznos za placanje $${price}`}
            amount={stripePrice}
            panelLabel='Kreiraj porudzbinu i plati'
            token={onToken}
            stripeKey={publishableKey}
            currency="USD">
                <button style={{border:"none", width:120, borderRadius:5, padding:"20px", backgroundColor:"black", fontWeight:"600", cursor:"pointer", color:"white"}}>Završi i plati</button>
            </StripeCheckout>
        </Container>
    )
}

export default StripeCheckoutButton;