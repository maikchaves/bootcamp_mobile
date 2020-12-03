  const fetch = require("node-fetch");

  const getAccounts = async () => {
    const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")
    responseAccounts.json().then(obj => {

      let contas = obj
        .filter(item=>item.balance>100)
      
        //contas com saldo acima de 100
        console.info(contas);
    })

  }
  getAccounts()
