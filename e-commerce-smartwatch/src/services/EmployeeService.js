import http from "../http-common";

const getAll = () => {
    return http.get("/zaposleni");
  };
  const get = id => {
    return http.get(`/zaposleni/${id}`);
  };
  const create = data => {
    return http.post("/zaposleni", data);
  };
  const update = (id, data) => {
    return http.put(`/zaposleni/${id}`, data);
  };
  const remove = id => {
    return http.delete(`/zaposleni/${id}`);
  };

  const EmployeeService = {
    getAll,
    get,
    create,
    update,
    remove
  };
  export default EmployeeService;