package com.example.desafiobootcampomobileandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView textViewResult;
    Button buttonReset;
    Double rateAnsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.textViewResult);
        buttonReset = findViewById(R.id.buttonReset);

        Integer correctTotal = getIntent().getIntExtra(getString(R.string.preference_num_correct),-1);
        Integer questionsTotal = getIntent().getIntExtra(getString(R.string.preference_num_questions),-1);

        buttonReset.setOnClickListener(v -> {
            gotoQuizzActivity();
        });

        if(questionsTotal == -1 || correctTotal == -1){
            textViewResult.setText(getString(R.string.no_answers));

        } else {
            rateAnsers = ((double)correctTotal/questionsTotal)*100;
            textViewResult.setText(String.format("%.0f%% de acertos", rateAnsers));

        }


    }

    private void gotoQuizzActivity(){
        startActivity(new Intent(getApplicationContext(), QuizzActivity.class));
        finish();
    }
}