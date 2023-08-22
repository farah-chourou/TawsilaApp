package com.example.tryfortawsila1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

public class OneC extends AppCompatActivity {

    Button loginc, registerc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_one_c);

        loginc = findViewById(R.id.loginc);
        loginc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logincIntent = new Intent(OneC.this, LoginC.class);
                startActivity(logincIntent);
            }
        });

        registerc = findViewById(R.id.registerc);
        registerc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registercIntent = new Intent(OneC.this, RegisterC.class);
                startActivity(registercIntent);
            }
        });


    }
}
