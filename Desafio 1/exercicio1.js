const fetch = require("node-fetch");

const getAccounts = async() => {
  const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")

  responseAccounts.json().then(obj=>{

    let totalDeposito = obj
      .map(item=>item.balance)
      .reduce((balanceAnterior, balanceAtual)=>balanceAnterior+balanceAtual);

    console.info(totalDeposito);  
  })

}

getAccounts()
