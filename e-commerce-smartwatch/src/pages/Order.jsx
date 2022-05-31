import { useEffect, useState } from "react";
import { useLocation } from "react-router";
import { userRequest } from "../requestMethods";

const Order = () => {
  const location = useLocation();const data = location.state.stripeData;
  const cart = location.state.cart;
  const [orderId, setOrderId] = useState(null);

  useEffect(() => {
    const createOrder = async () => {
      try {
        const res = await userRequest.post("/porudzbine", {          
          status:"poruceno",
          uplata:true,
          ukupan_iznos: cart.total,
          id_korpa: cart._id
        });
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