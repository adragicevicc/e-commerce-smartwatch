export const productInputs = [
    {
      id:1,
      name:"nazivProizvoda",
      type:"text",
      placeholder:"Naziv proizvoda",
      errorMessage: "This field is required!",
      label:"Naziv Proizvoda",
      required: true,
    },
    {
      id:2,
      name:"karakteristike",
      type:"text",
      placeholder:"Karakteristike",
      errorMessage:"Invalid input!",
      label:"Karakteristike",
      required: true,
    },
    {
      id:3,
      name:"cena",
      type:"number",
      placeholder:"Cena",
      errorMessage:"Invalid input!",
      label:"Cena",
      required: true
    },
    {
        id:4,
        name:"dostupna_kolicina",
        type:"number",
        placeholder:"Dostupna Kolicina",
        errorMessage:"Invalid input!",
        label:"Dostupna kolicina",
        required: true,
        min: 0
    },
    {
      id:5,
      name:"nazivProizvodjaca",
      type:"text",
      placeholder:"Naziv",
      errorMessage:"Invalid input!",
      label:"Naziv proizvodjaca",
      required: true
    }
  ];