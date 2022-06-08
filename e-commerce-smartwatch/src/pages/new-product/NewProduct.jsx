import { DriveFolderUploadOutlined } from '@mui/icons-material';
import React, { useContext, useEffect, useState } from 'react'
import { useLocation } from 'react-router-dom';
import FormInput from '../../components/form-input/FormInput';
import Navbar from '../../components/Navbar';
import { useNotification } from '../../components/notification/NotificationProvider';
import useFetch from '../../hooks/useFetch';
import styled from 'styled-components';
import axios from 'axios';
import FormTextArea from '../../components/form-text-area/FormTextArea';
import {productInputs} from './productInputs';
import { AuthContext } from '../../components/context/AuthContext';
import "./newProduct.scss"

const NewProduct = ({edit, title}) => {

    const location = useLocation();
  
    const id = location.pathname.split("/")[3];
  
    const { data, load, error} = useFetch(`http://localhost:8080/api/auth/proizvodi/${id}`)
  
    const dispatch = useNotification();
  
    const { user } = useContext(AuthContext);
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${user.accessToken}`,
    }
  
    const [file, setFile] = useState("");
    const [photo, setPhoto] = useState("");

    const [url, setUrl] = useState("");
  
    
    const [values, setValues] = useState({
        nazivProizvoda: "",
        karakteristike: "",
        cena: "",
        dostupna_kolicina: "",
        nazivProizvodjaca: ""
      })
    
      useEffect(() => {
        if (Object.keys(data).length !== 0 && edit){
            console.log(data);
          const val = {
            'nazivProizvoda': data.nazivProizvoda,
            'karakteristike': data.karakteristike,
            'cena': data.cena,
            'dostupna_kolicina':data.dostupna_kolicina,
            'nazivProizvodjaca': data.proizvodjac.nazivProizvodjaca
          }
          setValues(val);
          setPhoto(data.url);
        }
      }, [data])

      const handleClick = async (e) => {
          await onUploadImg();
        e.preventDefault();
        try {
          const newProduct = {
            ...values,
            url: url
          };
          if (edit){
            await axios.put(`http://localhost:8080/api/auth/proizvodi/${id}`, newProduct, {
              headers: headers
            });
            sendNotification("success", "Uspesno izmenjeno");
          }
          else{
            await axios.post("http://localhost:8080/api/auth/proizvodi", newProduct, {
              headers: headers
            });
            sendNotification("success", "Uspesno dodato");
          }
        } catch (err) {
          console.log(err)
          sendNotification("error", err.message);
        }
      };
  
      console.log("PHOTO " + photo);

      const sendNotification = (type, message) => {
        dispatch({
          type: type,
          message: message,
          navigateTo: '/products'
        });
      }
      const onChange = (e) => {
        setValues({...values, [e.target.name]: e.target.value})
      }
      const deleteImages = () => {
        setPhoto("");
        setFile("");
      }
      const onUploadImg = async (e) => {
          
        try {
            console.log("ral")
            const list = Promise.all(
              Object.values(e.target.files).map(async (file) => {
                const data = new FormData();
                data.append("file", file);
                data.append("upload_preset", "upload");
                const uploadRes = await axios.post(
                  "https://api.cloudinary.com/v1_1/ecommercesmartwatch/image/upload",
                  data
                );
      
                const { url } = uploadRes.data;
                return url;
              })
            ).then((list)=>{
                console.log(list)
              setPhoto(list[0]);
              setFile(e.target.files[0])
            });
            
        } catch (err) {
          console.log(err);
        }
    }

    return (
      <div>
        <Navbar />
        <div className="newProduct">

        <div className="newContainer">
          <div className="top">
            <h1>{title}</h1>
          </div>
          <div className="bottom">
            <div className="left">
              <div className="images">
                <div>Dodata fotografija:</div>
                <div className="oneImgWrapper">
                  <img
                    className="oneImg"
                    src={
                      photo ? photo
                        : "https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg"
                    }
                    alt=""
                  />
                </div>
                {photo && <button className="deleteImgsBtn" onClick={deleteImages}>Izbrisi sliku</button>}
              </div>
            </div>
            <div className="right">
              <form onSubmit={handleClick}>
                {productInputs.map((input) => (
                    !input.multiline ? <FormInput 
                      key={input.id}
                      {...input}
                      value={values[input.name]}
                      onChange={onChange}
                    />
                    :
                    <FormTextArea 
                      key={input.id}
                      {...input}
                      value={values[input.name]}
                      onChange={onChange}
                    /> 
                ))}
                <div className="formInput">
                  <label htmlFor="file">
                    Dodaj fotografiju: <DriveFolderUploadOutlined className="icon" />
                  </label>
                  <input
                    type="file"
                    accept="image/png, image/gif, image/jpeg"
                    id="file"
                    multiple
                    onChange={onUploadImg}
                    style={{ display: "none" }}
                  />
                </div>
                <div className="buttons">
                <button className="sendBtn">DODAJ</button>
              </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      </div>
    );
  };
export default NewProduct