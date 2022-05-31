import http from "../http-common";

const getAll = () => {
    return http.get("/kupci");
  };
  const get = id => {
    return http.get(`/kupci/${id}`);
  };
  const create = data => {
    return http.post("/kupci", data);
  };
  const update = (id, data) => {
    return http.put(`/kupci/${id}`, data);
  };
  const remove = id => {
    return http.delete(`/kupci/${id}`);
  };

  const BuyerService = {
    getAll,
    get,
    create,
    update,
    remove
  };
  export default BuyerService;