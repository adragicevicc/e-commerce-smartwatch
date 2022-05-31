import {  FacebookOutlined, } from '@mui/icons-material';
import InstagramIcon from '@mui/icons-material/Instagram';
import React from 'react'
import styled from 'styled-components';

const Container = styled.div`
    display:flex;
`;

const Left = styled.div`
    flex: 1;
    display: flex;
    flex-direction: column;
    padding: 20px;`;

const Center = styled.div`
    flex:1;
    padding: 20px;`;
    

const Right = styled.div`
    flex:1;`;

const SocialContainer = styled.div`
    display: flex;
`;


const SocialIcon = styled.div`
  width: 40px;
  height: 40px;
  border-radius: 50%;
  color: white;
  background-color: #${(props) => props.color};
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;`;


const Footer = () => {
  return (
    <Container>
        <Left>
            <SocialContainer>
                <SocialIcon color="3B5999">
                    <FacebookOutlined/>
                </SocialIcon>
                <SocialIcon color="E4405F">
                    <InstagramIcon/>
                </SocialIcon>
            </SocialContainer>
        </Left>
        <Center></Center>
        <Right></Right>
    </Container>
  )
}

export default Footer