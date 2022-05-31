import http from "../http-common";

const getAll = () => {
    return http.get("/proizvodiUKorpi");
  };
  const get = id => {
    return http.get(`/proizvodiUKorpi/${id}`);
  };
  const create = data => {
    return http.post("/proizvodiUKorpi", data);
  };
  const update = (id, data) => {
    return http.put(`/proizvodiUKorpi/${id}`, data);
  };
  const remove = id => {
    return http.delete(`/proizvodiUKorpi/${id}`);
  };

  const ProductInCartService = {
    getAll,
    get,
    create,
    update,
    remove
  };
  export default ProductInCartService;