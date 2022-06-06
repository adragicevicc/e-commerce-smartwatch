import Cart from "./pages/Cart";
import Home from "./pages/Home"
import Login from "./pages/Login";
import Product from "./pages/Product";
import ProductList from "./pages/ProductList";
import Register from "./pages/register/Register";
import RegisterAdmin from "./pages/register/RegisterAdmin";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate
} from "react-router-dom";
import NotificationProvider from "./components/notification/NotificationProvider";
import { useContext } from "react";
import { AuthContext } from "./components/context/AuthContext";
import Dashboard from "./pages/Dashboard";

const App = () => {

  const { user } = useContext(AuthContext);
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/products" element={<ProductList/>}/>
        <Route path="/product/:id" element={<Product/>}/>
        <Route path="/cart" element={<Cart/>}/>
        <Route path="/login" element={user ? <Navigate to="/" /> : <Login />}/>
        <Route path="/register" element={user ? <Navigate to="/" /> : <NotificationProvider><Register/></NotificationProvider>}/>
        <Route path="/dashboard" element={<Dashboard/>}/>
        <Route path="/adminRegistration" element={<RegisterAdmin/>}/>
      </Routes>
    </Router>
  )
};

export default App;
