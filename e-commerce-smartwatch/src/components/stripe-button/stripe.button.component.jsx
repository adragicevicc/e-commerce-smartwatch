import axios from 'axios';
import React from 'react';
import StripeCheckout from 'react-stripe-checkout';
import styled from 'styled-components';

const Container = styled.div`
    height: fit-content;
`;

const StripeCheckoutButton = ({ price }) => {
    const stripePrice = price * 100;
    const publishableKey = 'pk_test_51L32PoFYveM0ufMdHF3HxSWmVn9bwmsYAnLHyjdoTu6NTnB46vZwn5Yh4JzP4cdZFzn5CssswKWR9Cu3vVMXpeTL00kbCCQOxB';

    const onToken = token => {
        axios.post('http://localhost:8080/api/auth/payment', 
        {amount: stripePrice, token})
        .then((response) => {alert('Uplata uspesno izvrsena!')})
        .catch((error) => {alert('uplata neuspesna')})
    };

    return (
        <Container>
            <StripeCheckout
            name='SmartWatch'
            description={`Ukupan iznos za placanje $${price}`}
            amount={stripePrice}
            panelLabel='Plati'
            token={onToken}
            stripeKey={publishableKey}
            currency="USD">
                <button style={{border:"none", width:120, borderRadius:5, padding:"20px", backgroundColor:"black", fontWeight:"600", cursor:"pointer", color:"white"}}>Završi i plati</button>
            </StripeCheckout>
        </Container>
        
    )
}

export default StripeCheckoutButton;