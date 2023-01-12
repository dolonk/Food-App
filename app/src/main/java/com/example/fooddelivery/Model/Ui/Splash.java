package com.example.fooddelivery.Model.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.fooddelivery.R;

public class Splash extends AppCompatActivity {
    Button getStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getStart = findViewById(R.id.button);
        getStart.setOnClickListener(v -> {
            startActivity(new Intent(this,MainActivity.class));
        });

    }
}