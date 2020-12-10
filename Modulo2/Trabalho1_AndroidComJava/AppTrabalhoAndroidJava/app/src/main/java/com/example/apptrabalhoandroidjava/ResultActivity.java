package com.example.apptrabalhoandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    //resgata os elementos da tela para exibir os valoes
    private TextView salarioTextView;
    private TextView inssTextView;
    private TextView irrfTextView;
    private TextView descontosTextView;

    private TextView salarioLiquidoTextView;
    private TextView totalDescontosTextView;

    private Button voltarButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //inicia variáveis das views
        salarioTextView = findViewById(R.id.textViewResultSalarioBrutoValue);
        inssTextView = findViewById(R.id.textDependentesINSSValue);
        irrfTextView = findViewById(R.id.textResultadoIRRFValue);
        descontosTextView = findViewById(R.id.textResultadoOutrosDescontosValue);
        voltarButton = findViewById(R.id.buttonVoltar);

        salarioLiquidoTextView = findViewById(R.id.textViewSalarioLiquidoValue);
        totalDescontosTextView = findViewById(R.id.textViewDescontosValue);

        //resgata os valores enviados pela outra Activity através da Intent
        Double salarioBruto = getIntent().getDoubleExtra(MainActivity.SALARIO_VALUE, 0);
        Integer numDependentes = getIntent().getIntExtra(MainActivity.NUM_DEPENDENTES, 0);
        Double outrosDescontos = getIntent().getDoubleExtra(MainActivity.OUTROS_DESCONTOS_VALUE, 0);


        Double descontoINSS = descontoINSS(salarioBruto);
        Double descontoIRRF = descontoIRRF(salarioBruto-descontoINSS-(numDependentes*189.59));

        Double salarioLiquido = salarioBruto-descontoINSS-descontoIRRF-outrosDescontos;
        Double descontoPorcentagem = (1-(salarioLiquido/salarioBruto))*100;

        salarioTextView.setText(String.format("%.2f", salarioBruto));
        inssTextView.setText(String.format("-%.2f", descontoINSS));
        irrfTextView.setText(String.format("-%.2f", descontoIRRF));
        descontosTextView.setText(String.format("-%.2f", outrosDescontos));

        salarioLiquidoTextView.setText(String.format("%.2f", salarioLiquido));
        totalDescontosTextView.setText(String.format("%.2f%%", descontoPorcentagem));

        //dá a ação para o botão de voltar
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chama o evento do botão de voltar (setinha), desempilhando a activity atual
                ResultActivity.super.onBackPressed();
            }
        });
    }

    private double descontoINSS(double salario){

        double desconto = 0;

        if(salario <= 1045){
            desconto = salario*0.075;
        } else if (salario <=2089.6){
            desconto = (salario*0.9)-15.67;
        } else if (salario <= 3134.4){
            desconto = (salario*0.12) - 78.36;
        } else if (salario <=6101.06){
            desconto = (salario*0.14) - 141.05;
        } else {
            desconto = 713.10;
        }

        return desconto;
    }

    private double descontoIRRF(double salarioBase){

        double desconto = 0;

        if(salarioBase <= 1903.98){
            desconto = 0;
        } else if (salarioBase <=2862.65){
            desconto = (salarioBase*0.075)-142.8;
        } else if (salarioBase <= 3751.05){
            desconto = (salarioBase*0.15) - 354.8;
        } else if (salarioBase <=4664){
            desconto = (salarioBase*0.225) - 636.13;
        } else {
            desconto = (salarioBase*0.275)-869.36;
        }

        return desconto;
    }
}