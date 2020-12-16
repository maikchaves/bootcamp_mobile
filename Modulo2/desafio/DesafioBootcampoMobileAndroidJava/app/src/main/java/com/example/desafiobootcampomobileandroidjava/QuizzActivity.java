package com.example.desafiobootcampomobileandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuizzActivity extends AppCompatActivity {

    BufferedReader reader = null;

    List<Quizz> quizzList = new ArrayList<>();

    TextView textQuestion;
    Button buttonTrue, buttonFalse;
    int countQuizz, correctNum;
    boolean isClickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        //avoids user to click same button more than once before next question is loaded
        isClickable = true;

        textQuestion = findViewById(R.id.textQuestion);
        buttonTrue = findViewById(R.id.button_true);
        buttonFalse = findViewById(R.id.button_false);

        correctNum = 0;
        countQuizz = -1;

        setupQuizz();

        if (quizzList.size() == 0) {
            Toast.makeText(this, getString(R.string.no_quizz), Toast.LENGTH_SHORT).show();
        } else {

            //start quizz
            nextQuestion();


            buttonTrue.setOnClickListener(v -> {
                if (isClickable) {
                    if (quizzList.get(countQuizz).getAnswer()) {
                        isClickable = false;
                        correctNum++;
                        Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
                        nextQuestion();
                    } else {
                        isClickable = false;
                        Toast.makeText(this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show();
                        nextQuestion();
                    }
                }
            });

            buttonFalse.setOnClickListener(v -> {
                if (isClickable) {
                    if (!quizzList.get(countQuizz).getAnswer()) {
                        isClickable = false;
                        correctNum++;
                        Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
                        nextQuestion();
                    } else {
                        isClickable = false;
                        Toast.makeText(this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show();
                        nextQuestion();
                    }
                }

            });
        }
    }

    private void nextQuestion() {

        if (countQuizz < quizzList.size() - 1) {
            textQuestion.setText(quizzList.get(++countQuizz).getQuestion());
            isClickable = true;
        } else {
            //reached limit, go to result screen
            gotoResultActivity();
        }


    }

    private void setupQuizz() {

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("perguntas.txt")));

            String line;
            while ((line = reader.readLine()) != null) {

                String[] question = line.split("; ");

                quizzList.add(new Quizz(question[0], question[1].equals("verdadeiro")));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void gotoResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);

        intent.putExtra(getString(R.string.preference_num_questions), quizzList.size());
        intent.putExtra(getString(R.string.preference_num_correct), correctNum);

        startActivity(intent);
        finish();
    }
}
