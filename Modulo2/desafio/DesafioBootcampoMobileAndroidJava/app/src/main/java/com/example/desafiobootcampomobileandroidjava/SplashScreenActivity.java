package com.example.desafiobootcampomobileandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Boolean alreadyLogged = sharedPreferences.getBoolean(getString(R.string.preference_logged), false);
        //primeiro login do usuráio
        if(!alreadyLogged){

            goToMainActivity();

        } else {


            //salva a informação que o usuário já realizou um login no app
            editor.putBoolean(getString(R.string.preference_logged), true);
            editor.apply();

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToMainActivity();
                }
            }, 3000);
        }
    }

    private void goToMainActivity(){
        startActivity(new Intent(getApplicationContext(), QuizzActivity.class));
        finish();
    }
}