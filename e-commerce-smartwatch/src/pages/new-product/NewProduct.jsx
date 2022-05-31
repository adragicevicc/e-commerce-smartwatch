import { DriveFolderUploadOutlined } from "@mui/icons-material";
import axios from "axios";
import { useContext, useState } from "react";
import { AuthContext } from "../../../../components/context/AuthContext";
import FormInput from "../../../../components/formInput/FormInput";
import FormTextArea from "../../../../components/formTextArea/FormTextArea";
import { useNotification } from "../../../../components/notification/NotificationProvider";
import { productInputs } from "../../../formSource";
import {boatTypes} from "../../../formSource";
import "./newBoat.scss"

const NewBoat = () => {

  const dispatch = useNotification();

  const { user } = useContext(AuthContext);

  const headers = {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${user.accessToken}`,
  }

  const [files, setFiles] = useState("");
  
  const [values, setValues] = useState({
    nazivProizvoda: "",
    country: "",
    city: "",
    street: "",
    description: "",
    capacity: "",
    regulations: "",
    price: "",
    fee: "",
    boatType: boatTypes[0].value,
    length: "",
    engineNum: "",
    enginePow: "",
    maxSpeed: "",
  })

  const handleClick = async (e) => {
    e.preventDefault();
    try {
      const list = await Promise.all(
        Object.values(files).map(async (file) => {
          const data = new FormData();
          data.append("file", file);
          data.append("upload_preset", "upload");
          const uploadRes = await axios.post(
            "https://api.cloudinary.com/v1_1/bookerapp/image/upload",
            data
          );

          const { url } = uploadRes.data;
          return url;
        })
      );

      const newBoat = {
        ...values,
        additionalServices: services.map(function(item){
          delete item.id;
          return item;
        }),
        navGear: navGear.map(({name}) => name),
        fishingGear: fishingGear.map(({name}) => name),
        photos: list,
        owner_id: user.id,
      };

      await axios.post("http://localhost:8080/api/add-boat", newBoat, {
        headers: headers
      });

      sendNotification("success", "You successfully added a new boat!");

    } catch (err) {
      console.log(err)
      sendNotification("error", err.message);
    }
  };

  const sendNotification = (type, message) => {
    dispatch({
      type: type,
      message: message,
      navigateTo: '/dashboard/my-offers'
    });
  }

  const onChange = (e) => {
    setValues({...values, [e.target.name]: e.target.value})
  }

  const additionalServices = (e) => {
    e.preventDefault();
    setShowAddServices(!showAddServices);
  }

  const navGearModal = (e) => {
    e.preventDefault();
    setShowNavGear(!showNavGear);
  }

  const fishingGearModal = (e) => {
    e.preventDefault();
    setShowFishingGear(!showFishingGear);
  }

  const handleSelect = (e) => {
    setValues({...values, ["boatType"]: e.target.value}); 
  }

  return (
    <div className="newBoat">
      <Sidebar />
      <div className="newContainer">
        <DashNavbar />
        <div className="top">
          <h1>Add New Boat</h1>
        </div>
        <div className="bottom">
          <div className="left">
            <div className="images">
              <span>Uploaded images:</span>
              <img
                src={
                  files
                    ? URL.createObjectURL(files[0])
                    : "https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg"
                }
                alt=""
              />
            </div>
          </div>
          <div className="right">
            <form onSubmit={handleClick}>
              {boatInputs.map((input) => (
                !input.multiline ? (<>
                  {!input.select ? <FormInput 
                    key={input.id}
                    {...input}
                    value={values[input.name]}
                    onChange={onChange}
                  />
                  :
                  <div className="boatTypeSelect"> 
                    <label>Boat Type</label>
                    <select onChange={handleSelect}>
                      {boatTypes.map((type) => (
                        <option key={type.id} value={type.value}>
                          {type.type}
                        </option>
                      ))}
                    </select>
                  </div>
                  }
                  
                </>)
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
                  Upload images: <DriveFolderUploadOutlined className="icon" />
                </label>
                <input
                  type="file"
                  accept="image/png, image/gif, image/jpeg"
                  id="file"
                  multiple
                  onChange={(e) => setFiles(e.target.files)}
                  style={{ display: "none" }}
                />
              </div>
              <div className="buttons">
                <div className="modalsBtns">
                  <button onClick={navGearModal}>Navigation Equipment</button> 
                  <button onClick={fishingGearModal}>Fishing Equipment</button> 
                  <button onClick={additionalServices}>Additional Services</button>
                </div>
                <div className="sendBtnWrapper">
                  <button className="sendBtn">ADD</button>
                </div>
              </div>
            </form>
            <AdditionalServicesModal 
              services={services} 
              setServices={setServices}
              showAddServices={showAddServices}
              setShowAddServices={setShowAddServices}
            />
            <GearModalInput
              title="Add Navigation Equipment"
              gear={navGear}
              setGear={setNavGear}
              showGear={showNavGear}
              setShowGear={setShowNavGear}
            />
            <GearModalInput
              title="Add Fishing Equipment"
              gear={fishingGear}
              setGear={setFishingGear}
              showGear={showFishingGear}
              setShowGear={setShowFishingGear}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default NewBoat