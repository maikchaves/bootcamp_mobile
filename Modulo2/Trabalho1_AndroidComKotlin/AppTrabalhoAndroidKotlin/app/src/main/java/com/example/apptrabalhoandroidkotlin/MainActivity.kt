package com.example.apptrabalhoandroidkotlin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //define constante para padronizar nomes de parâmetros enviados pela Intent
    companion object {
        const val SALARIO_VALUE = "SALARIO_VALUE"
        const val OUTROS_DESCONTOS_VALUE = "OUTROS_DESCONTOS_VALUE"
        const val NUM_DEPENDENTES = "NUM_DEPENDENTES"
    }

    private var salarioBrutoEditText: EditText? = null
    private var numDependentesEditText: EditText? = null
    private var outrosDescontosEditText: EditText? = null
    private var calcularButton: Button? = null
    private var limparButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inicia variáveis

        //inicia variáveis
        salarioBrutoEditText = findViewById(R.id.editTextSalarioBruto)
        numDependentesEditText = findViewById(R.id.editTextDependentes)
        outrosDescontosEditText = findViewById(R.id.editTextOutrosDescontos)
        calcularButton = findViewById(R.id.buttonCalcular)
        limparButton = findViewById(R.id.limparButton)

        limparButton?.setOnClickListener{
            limpar();
        }

        calcularButton?.setOnClickListener {
            val intent = Intent(applicationContext, ResultActivity::class.java)

            val salarioBrutoValue = salarioBrutoEditText?.getText().toString()
            val numDependentesValue = numDependentesEditText?.getText().toString()
            val outrosDescontosValue = outrosDescontosEditText?.getText().toString()

            //resgata valores digitados pelo usuário para enviar para a próxima activity

            //resgata valores digitados pelo usuário para enviar para a próxima activity
            val salarioBruto: Double =
                if (!salarioBrutoValue.isEmpty()) salarioBrutoValue.toDouble() else 0.toDouble()
            val numDependentes   =
                if (!numDependentesValue.isEmpty()) numDependentesValue.toInt() else 0
            val outrosDescontos: Double =
                if (!outrosDescontosValue.isEmpty()) outrosDescontosValue.toDouble() else 0.toDouble()

            //define valores a serem enviados para próxima activity

            //define valores a serem enviados para próxima activity
            intent.putExtra(SALARIO_VALUE, salarioBruto)
            intent.putExtra(NUM_DEPENDENTES, numDependentes)
            intent.putExtra(OUTROS_DESCONTOS_VALUE, outrosDescontos)

            startActivity(intent)
        }

    }

    private fun limpar(): Void? {

        salarioBrutoEditText?.setText("", TextView.BufferType.EDITABLE)
        numDependentesEditText?.setText("", TextView.BufferType.EDITABLE)
        outrosDescontosEditText?.setText("", TextView.BufferType.EDITABLE);


        return null
    }
}