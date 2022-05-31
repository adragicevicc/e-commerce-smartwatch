import React, { useState } from "react";
import ProductDataService from "../services/ProductService";

const AddProduct = () => {
  const initialProductState = {
    id: null,
    name: "",
    price: "",
    quantity:"",
    char:"",
    url:""
  };
  const [product, setProduct] = useState(initialProductState);
  const [submitted, setSubmitted] = useState(false);
  const handleInputChange = event => {
    const { name, value } = event.target;
    setProduct({ ...product, [name]: value });
  };
  const saveProduct = () => {
    var data = {
        naziv_proizvoda: product.name,
        cena: product.price,
        dostupna_kolicina:product.quantity,
        karakteristike:product.char,
        url:product.url
    };
    ProductDataService.create(data)
      .then(response => {
        setProduct({
          id_proizvod: response.data.id,
          naziv_proizvoda: response.data.name,
          cena: response.data.price,
          dostupna_kolicina: response.data.quantity,
          karakteristike:response.data.char,
          url:response.data.url
        });
        setSubmitted(true);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };
  const newProduct = () => {
    setProduct(initialProductState);
    setSubmitted(false);
  };
  return (<div className="submit-form">
  {submitted ? (
    <div>
      <h4>You submitted successfully!</h4>
      <button className="btn btn-success" onClick={newProduct}>
        Add
      </button>
    </div>
  ) : (
    <div>
      <div className="form-group">
        <label htmlFor="title">Title</label>
        <input
          type="text"
          className="form-control"
          id="title"
          required
          value={product.name}
          onChange={handleInputChange}
          name="title"
        />
      </div>
      <div className="form-group">
        <label htmlFor="description">Description</label>
        <input
          type="text"
          className="form-control"
          id="description"
          required
          value={product.char}
          onChange={handleInputChange}
          name="description"
        />
      </div>
      <button onClick={saveProduct} className="btn btn-success">
        Submit
      </button>
    </div>
  )}
</div>
);
};
export default AddProduct;