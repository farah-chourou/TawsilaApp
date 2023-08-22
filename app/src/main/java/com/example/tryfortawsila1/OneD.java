package com.example.tryfortawsila1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

public class OneD extends AppCompatActivity {

    Button logind, registerd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_one_d);

        logind = findViewById(R.id.logind);
        logind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logindIntent = new Intent(OneD.this, LoginD.class);
                startActivity(logindIntent);
            }
        });

        registerd = findViewById(R.id.registerd);
        registerd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerdIntent = new Intent(OneD.this, RegisterD.class);
                startActivity(registerdIntent);
            }
        });


    }
}
