import http from "../http-common";

const getAll = () => {
    return http.get("/proizvodjaci");
  };
  const get = id => {
    return http.get(`/proizvodjaci/${id}`);
  };
  const create = data => {
    return http.post("/proizvodjaci", data);
  };
  const update = (id, data) => {
    return http.put(`/proizvodjaci/${id}`, data);
  };
  const remove = id => {
    return http.delete(`/proizvodjaci/${id}`);
  };

  const ProducerService = {
    getAll,
    get,
    create,
    update,
    remove
  };
  export default ProducerService;