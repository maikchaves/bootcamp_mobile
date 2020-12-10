package com.example.apptrabalhoandroidkotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private var salarioTextView: TextView? = null
    private var inssTextView: TextView? = null
    private var irrfTextView: TextView? = null
    private var descontosTextView: TextView? = null

    private var salarioLiquidoTextView: TextView? = null
    private var totalDescontosTextView: TextView? = null

    private var voltarButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //inicia variáveis das views

        //inicia variáveis das views
        salarioTextView = findViewById(R.id.textViewResultSalarioBrutoValue)
        inssTextView = findViewById(R.id.textDependentesINSSValue)
        irrfTextView = findViewById(R.id.textResultadoIRRFValue)
        descontosTextView = findViewById(R.id.textResultadoOutrosDescontosValue)
        voltarButton = findViewById(R.id.buttonVoltar)

        salarioLiquidoTextView = findViewById(R.id.textViewSalarioLiquidoValue)
        totalDescontosTextView = findViewById(R.id.textViewDescontosValue)

        //resgata os valores enviados pela outra Activity através da Intent

        //resgata os valores enviados pela outra Activity através da Intent
        val salarioBruto = intent.getDoubleExtra(MainActivity.SALARIO_VALUE, 0.0)
        val numDependentes = intent.getIntExtra(MainActivity.NUM_DEPENDENTES, 0)
        val outrosDescontos = intent.getDoubleExtra(MainActivity.OUTROS_DESCONTOS_VALUE, 0.0)


        val descontoINSS = descontoINSS(salarioBruto)
        val descontoIRRF = descontoIRRF(salarioBruto - descontoINSS - numDependentes * 189.59)

        val salarioLiquido = salarioBruto - descontoINSS - descontoIRRF - outrosDescontos
        val descontoPorcentagem = (1 - salarioLiquido / salarioBruto) * 100

        salarioTextView?.setText(String.format("%.2f", salarioBruto))
        inssTextView?.setText(String.format("-%.2f", descontoINSS))
        irrfTextView?.setText(String.format("-%.2f", descontoIRRF))
        descontosTextView?.setText(String.format("-%.2f", outrosDescontos))

        salarioLiquidoTextView?.setText(String.format("%.2f", salarioLiquido))
        totalDescontosTextView?.setText(String.format("%.2f%%", descontoPorcentagem))

        //dá a ação para o botão de voltar
        voltarButton?.setOnClickListener(View.OnClickListener { //chama o evento do botão de voltar (setinha), desempilhando a activity atual
            super@ResultActivity.onBackPressed()
        })

    }


    private fun descontoINSS(salario: Double): Double {
        var desconto = 0.0
        desconto = if (salario <= 1045) {
            salario * 0.075
        } else if (salario <= 2089.6) {
            salario * 0.9 - 15.67
        } else if (salario <= 3134.4) {
            salario * 0.12 - 78.36
        } else if (salario <= 6101.06) {
            salario * 0.14 - 141.05
        } else {
            713.10
        }
        return desconto
    }

    private fun descontoIRRF(salarioBase: Double): Double {
        var desconto = 0.0
        desconto = if (salarioBase <= 1903.98) {
            0.0
        } else if (salarioBase <= 2862.65) {
            salarioBase * 0.075 - 142.8
        } else if (salarioBase <= 3751.05) {
            salarioBase * 0.15 - 354.8
        } else if (salarioBase <= 4664) {
            salarioBase * 0.225 - 636.13
        } else {
            salarioBase * 0.275 - 869.36
        }
        return desconto
    }
}