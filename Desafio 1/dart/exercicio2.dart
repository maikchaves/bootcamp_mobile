import "dart:html"; 
import "dart:convert";

class Conta{
  Conta(int id, int agencia, int conta, String name, int balance){
    this.id = id;
    this.agencia = agencia;
    this.conta = conta;
    this.name = name;
    this.balance = balance;
  }
  
  int id;
  int agencia;
  int conta;
  String name;
  int balance;
  
  String toString() => "\n\n Nome: ${id.toString()} \n Agencia: ${agencia.toString()} \n Conta: ${conta.toString()} \n Name: ${name.toString()} \n Balance: ${balance.toString()}";
}

void main(List<String> args) async {
  var contasResposta = await HttpRequest.getString("https://igti-film.herokuapp.com/api/accounts");
  
  var contasJson = jsonDecode(contasResposta);
  
  List<Conta> contas = new List();
  
  for(int i=0; i<contasJson.length; i++){
    Conta conta = new Conta(contasJson[i]["id"],
                         contasJson[i]["agencia"],
                         contasJson[i]["conta"],
                         contasJson[i]["name"],
                         contasJson[i]["balance"]);
    contas.add(conta);
    
  }
  
 var balances = contas.map<int>((item)=>item.balance)
   .where((balance)=>balance > 100).toList();
  
 print(balances.length);

}
