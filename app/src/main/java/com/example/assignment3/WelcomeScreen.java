/**
 * The first screen the user sees
 * and takes the user to the MainMenu
 */
package com.example.assignment3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        CountDownTimer timer = new CountDownTimer(5000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                ImageView arrow = findViewById(R.id.up_arrow);
                Animation floatUp= AnimationUtils.loadAnimation(WelcomeScreen.this, R.anim.float_up);
                arrow.startAnimation(floatUp);
            }



            @Override
            public void onFinish() {

                Intent intent = MainMenu.makeIntent(WelcomeScreen.this);
                startActivity(intent);
                finish();
            }



        }.start();


        setupNewGame(timer);



    }

    private void setupNewGame(CountDownTimer timer) {

        Button btn = findViewById(R.id.btnMainMenu);
        Animation floatUp= AnimationUtils.loadAnimation(WelcomeScreen.this, R.anim.float_up);
        btn.startAnimation(floatUp);

        ImageView skeleton1 = findViewById(R.id.skeleton1);
        ImageView skeleton2 = findViewById(R.id.skeleton2);

        Animation rotate = AnimationUtils.loadAnimation(WelcomeScreen.this, R.anim.rotate);
        skeleton1.startAnimation(rotate);
        skeleton2.startAnimation(rotate);

        TextView hint = findViewById(R.id.start_hint);
        hint.startAnimation(floatUp);
        btn.setOnClickListener(v -> {
            timer.cancel();
            Intent intent = MainMenu.makeIntent(WelcomeScreen.this);
            startActivity(intent);
            finish();
        });
    }




}