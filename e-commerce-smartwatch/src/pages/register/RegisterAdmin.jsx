import "./register.css"
import FormInput from '../../components/form-input/FormInput'
import { useState } from "react"
import { useNotification } from "../../components/notification/NotificationProvider";
import { useNavigate } from "react-router-dom";

const RegisterAdmin = () => {

  const dispatch = useNotification();
  const navigate = useNavigate();



const [values, setValues] = useState({
    email:"",
    ime:"",
    prezime:"",
    lozinka:"",
    pozicija:"admin",
    tip:"admin",
    uloga:"ROLE_ADMIN"

  })

  const inputs = [
    {
      id:1,
      name:"email",
      type:"email",
      placeholder:"Email",
      errorMessage:"It should be a valid email address!",
      label:"Email",
      required: true,
    },
    {
      id:2,
      name:"ime",
      type:"text",
      placeholder:"Ime",
      errorMessage:"Invalid input!",
      label:"Ime",
      required: true,
      pattern: `^[A-Z][a-z ,.'-]+$`
    },
    {
      id:3,
      name:"prezime",
      type:"text",
      placeholder:"Prezime",
      errorMessage:"Invalid input!",
      label:"Prezime",
      required: true,
      pattern: `^[A-Z][a-z ,.'-]+$`
    },
    {
      id:4,
      name:"lozinka",
      type:"password",
      placeholder:"Lozinka",
      errorMessage:"Password should be at least 8 characters!",
      label:"Lozinka",
      required: true,
      pattern: `^.{8,}$`
    }
  ]

  const inputsSecondRow = [
    {
      id:5,
      name:"pozicija",
      type:"text",
      placeholder:"Pozicija",
      errorMessage:"Invalid input!",
      label:"Pozicija",
      required: true,
      pattern: `^.{1,}$`
    }
  ]


  const handleSubmit = (e) => {
    e.preventDefault()
    fetch('http://localhost:8080/api/auth/signup', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(values)
      })
        .then(res => {
          if (!res.ok){
            if (res.status == 409){
              console.log('email exists error');
              throw Error('E-mail već postoji!');
            }
            console.log('unknown error')
            throw Error('Unknown fetch error occurred!')
          }
          return res.json()
        })
        .then(data => {
          sendNotification("success", "Uspešno ste se registrovali! Molimo Vas da se prijavite.");
        })
        .catch(err => {
          sendNotification("error", err.message)
        })
  }

  const sendNotification = (type, message) => {
    dispatch({
      type: type,
      message: message,
      navigateTo: '/login'
    });
  }

  const onChange = (e) => {
    setValues({...values, [e.target.name]: e.target.value})
  }

  return (
    <div className="registration">
      <div className="form">
        <h1>Registracija</h1>
        <form onSubmit={handleSubmit}>
          <div className="formElements">
            <div className="row">
              {inputs.map((input) => (
                <FormInput
                  key={input.id}
                  {...input}
                  value={values[input.name]}
                  onChange={onChange}
                />
              ))}
            </div>
            <div className="row">
              {inputsSecondRow.map((input) => (
                <FormInput
                  key={input.id}
                  {...input}
                  value={values[input.name]}
                  onChange={onChange}
                />
              ))}
            </div>
          </div>
          <button>Registruj se</button>
        </form>
      </div>
    </div>

  )
}

export default RegisterAdmin