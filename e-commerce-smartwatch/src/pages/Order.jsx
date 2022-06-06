import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router";

const Order = () => {
  const location = useLocation();const data = location.state.stripeData;
  const cart = location.state.cart;
  const [orderId, setOrderId] = useState(null);

  const {user} = useContext(AuthContext)
  let headers = {};
  if (user){
    headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.accessToken}`,
    }
  }

  useEffect(() => {
    const createOrder = async () => {
      try {
        const res = await axios.post("http://localhost:8080/api/auth/porudzbine", {
          ukupan_iznos: cart.total,
          id_korpa: cart._id
        }, {headers:headers});
        setOrderId(res.data._id);
      } catch {}
    };
    data && createOrder();
  }, [cart, data, currentUser]);

  return (
    <div
      style={{
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      {orderId
        ? `Porudzbina uspesno kreirana`
        : `Successfull. Your order is being prepared...`}
      <button style={{ padding: 10, marginTop: 20 }}>Go to Homepage</button>
    </div>
  );
};

export default Order;