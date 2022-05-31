import http from "../http-common";

const getAll = () => {
    return http.get("/korpe");
  };
  const get = id => {
    return http.get(`/korpe/${id}`);
  };
  const create = data => {
    return http.post("/korpe", data);
  };
  const update = (id, data) => {
    return http.put(`/korpe/${id}`, data);
  };
  const remove = id => {
    return http.delete(`/korpe/${id}`);
  };

  const CartService = {
    getAll,
    get,
    create,
    update,
    remove
  };
  export default CartService;