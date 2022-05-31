import styled from 'styled-components';
import { useContext, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../components/context/AuthContext';
import axios from "axios";



const Container = styled.div`
    width:100vw;
    height: 100vh;
    background: linear-gradient(#eaeaea7e, #4f4b4b7b), url("https://res.cloudinary.com/ecommercesmartwatch/image/upload/v1654018106/wallpaper4_k1klzf.jpg");
    background-size: 100%;
    display:flex;
    align-items:center;
    justify-content: center;
`;
const Wrapper = styled.div`
    width: 25%;
    height: fit-content;
    padding:20px;
    background-color: white;
    border-radius: 3%;
`;
const Title = styled.h1`
    font-size: 24px;
    font-weight: 300;
`;
const Form = styled.form`
    display: flex;
    flex-direction: column;
`;
const Input = styled.input`
    flex:1;
    min-width: 40%;
    margin: 10px 0;
    padding: 10px;
`;
const Button = styled.button`
    width:30%;
    height: 35px;
    border:none;
    margin: 15px 0px;
    background-color: teal;
    color: white;
    cursor: pointer;
`;

const Span =  styled.span`
    margin:5px 0px;
    font-size: 13px;
    text-decoration: underline;
    cursor:pointer;
    margin-bottom: 10px;
`;

const Error = styled.span`
  color: red;
`;

const Login = () => {
  const [credentials, setCredentials] = useState({
    email: undefined,
    password: undefined,
  });

  const { loading, error, dispatch } = useContext(AuthContext);

  const navigate = useNavigate();

  const handleChange = (e) => {
    setCredentials((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleClick = async (e) => {
    e.preventDefault();

    dispatch({ type: "LOGIN_START" });

    try {
      const res = await axios.post("http://localhost:8080/api/auth/signin", {email:credentials.email, lozinka:credentials.password});
      dispatch({ type: "LOGIN_SUCCESS", payload: res.data });
      navigate("/");
    } catch (error) {
      dispatch({ type: "LOGIN_FAILURE", payload: error });
    }
  };


  return (
    <Container>
        <Wrapper>
            <Title>Prijavi se</Title>
            <Form>
                <Input name="email" placeholder="Email" onChange={handleChange}/>
                <Input name="password" placeholder="Lozinka" type="password" onChange={handleChange}/>
                <Button disabled={loading} onClick={handleClick}>Prijavi se</Button>
                {error && <span className="wrong">Email ili lozinka neispravni</span>}
                <Link to="/register"><Span>Nemaš nalog? Registruj se</Span></Link>
            </Form>
        </Wrapper>
    </Container>
  )
}

export default Login