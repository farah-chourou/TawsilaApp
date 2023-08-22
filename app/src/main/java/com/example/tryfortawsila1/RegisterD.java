package com.example.tryfortawsila1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterD extends AppCompatActivity {

    EditText email, password, cpassword;
    TextView loginNow;

    FirebaseAuth mAuth;
    Button signup;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register_d);

        email = findViewById(R.id.emaild);
        password = findViewById(R.id.pwdd2);
        cpassword = findViewById(R.id.cpwdd);

        signup = findViewById(R.id.signupd);
        loginNow = findViewById(R.id.loginNowD);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(view ->{
            createUser();
        });

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterD.this, LoginD.class);
                startActivity(LoginIntent);
            }
        });

        //image for hiding password or showing it
        ImageView showHideBtn = findViewById(R.id.showHideBtn2);
        showHideBtn.setImageResource(R.drawable.visibilityoff);
        showHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    //if pass visible then hide it
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //change the eye icon
                    showHideBtn.setImageResource(R.drawable.eye);
                } else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showHideBtn.setImageResource(R.drawable.visibilityoff);
                }
            }
        });
    }
    private void createUser(){
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Email)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if (TextUtils.isEmpty(Password)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }else if(!cpassword.getText().toString().equals(password.getText().toString())){
            Toast.makeText(RegisterD.this, "Password mismatch", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterD.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterD.this, Read.class));
                    }else{
                        Toast.makeText(RegisterD.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}