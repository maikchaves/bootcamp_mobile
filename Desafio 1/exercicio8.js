const fetch = require("node-fetch");

  const getAccounts = async () => {
    const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")
    responseAccounts.json().then(obj => {

      let stringTresMenores = "";

      let menorSaldoAgencia47 = obj
        .filter(item=>item.agencia == 47)
        .sort((conta1, conta2) => conta1.balance - conta2.balance)
        .map(item=>item.name)
        
      for(i=0; i<3; i++){
        if(stringTresMenores==""){
          stringTresMenores = menorSaldoAgencia47[i]
        } else {
          stringTresMenores += ", "+menorSaldoAgencia47[i];
        }
      }
        
      console.info(stringTresMenores)
    })

  }
  getAccounts()
