   const fetch = require("node-fetch");

  const getAccounts = async () => {
    const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")
    responseAccounts.json().then(obj => {

      let menorSaldoAgencia47 = obj
        .filter(item=>item.agencia == 47)
        .sort((conta1, conta2) => conta1.balance - conta2.balance)
        .map(item=>item.name)[0]
        
        
        console.info(JSON.stringify(menorSaldoAgencia47, null, 2));  

    })

  }
  getAccounts()
