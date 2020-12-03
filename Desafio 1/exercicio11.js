const fetch = require("node-fetch");

  const getAccounts = async () => {
    const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")
    responseAccounts.json().then(obj => {

      
      let contas = obj
        .sort((conta1, conta2) => conta2.id - conta1.id)
        .map(item=>item.id)[0];
      
      //próximo id
        console.info(contas+1);
    })

  }
  getAccounts()
