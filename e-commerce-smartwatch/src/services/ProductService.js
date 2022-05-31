import http from "../http-common";

const getAll = () => {
    return http.get("/proizvodi");
  };
  const get = id => {
    return http.get(`/proizvodi/${id}`);
  };
  const create = data => {
    return http.post("/proizvodi", data);
  };
  const update = (id, data) => {
    return http.put(`/proizvodi/${id}`, data);
  };
  const remove = id => {
    return http.delete(`/proizvodi/${id}`);
  };

  const ProductService = {
    getAll,
    get,
    create,
    update,
    remove
  };
  export default ProductService;