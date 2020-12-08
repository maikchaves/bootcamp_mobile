const fetch = require("node-fetch");

const getAccounts = async () => {
  const responseAccounts = await fetch("https://igti-film.herokuapp.com/api/accounts")
  responseAccounts.json().then(obj => {

    let depositosPorAngencia = obj
      .sort((conta1, conta2) => conta2.balance - conta1.balance)
      .sort((conta1, conta2) => conta1.agencia - conta2.agencia)

    let maiorPorAgencia = [];
    let agenciaAtual = '';

    for (i = 0; i < depositosPorAngencia.length; i++) {

      if (depositosPorAngencia[i].agencia !== agenciaAtual) {
        maiorPorAgencia.push(depositosPorAngencia[i]);
      }

      agenciaAtual = depositosPorAngencia[i].agencia;

    }

    totalMaiorPorAgencia = maiorPorAgencia
      .map(item=>item.balance)
      .reduce((balance1,balance2)=>balance1+balance2);

    console.info(totalMaiorPorAgencia);  

  })

}
getAccounts()

