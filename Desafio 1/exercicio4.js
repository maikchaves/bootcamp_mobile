  const fetch = require("node-fetch");

  const getAccounts = async () => {
    const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")
    responseAccounts.json().then(obj => {

      let agenciaMaiorSaldo = obj
        .sort((conta1, conta2)=>conta2.balance - conta1.balance)
        .map(item=>item.agencia)[0]
        

        console.info(agenciaMaiorSaldo);
    })

  }
  getAccounts()
