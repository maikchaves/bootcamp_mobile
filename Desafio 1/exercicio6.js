 const fetch = require("node-fetch");

  const getAccounts = async () => {
    const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")
    responseAccounts.json().then(obj => {

      let maiorSaldoAgencia10 = obj
        .filter(item=>item.agencia == 10)
        .sort((conta1, conta2) => conta2.balance - conta1.balance)
        .map(item=>item.name)[0]
        
        
        console.info(JSON.stringify(maiorSaldoAgencia10, null, 2));  

    })

  }
  getAccounts()
