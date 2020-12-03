const fetch = require("node-fetch");

  const getAccounts = async () => {
    const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")
    responseAccounts.json().then(obj => {

      
      let contasAgencia47 = obj
        .filter(item=>item.agencia == 47)
        .filter(item=>item.name.includes('Maria'))
        
      console.info(contasAgencia47.length)
    })

  }
  getAccounts()
