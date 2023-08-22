package com.example.tryfortawsila1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class LoginD extends AppCompatActivity {

    private EditText email, password;
    private Button loginBtn;
    private TextView registerNow;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login_d);

        email = findViewById(R.id.emaild2);
        password = findViewById(R.id.pwdd);
        loginBtn = findViewById(R.id.logd);
        registerNow = findViewById(R.id.registerNowD);

        mAuth = FirebaseAuth.getInstance();



        // adding click listener for our new user tv.
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line opening a login activity.
                Intent i = new Intent(LoginD.this, RegisterD.class);
                startActivity(i);
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

                // adding on click listener for our login button.
                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // getting data from our edit text on below line.
                        String Email = email.getText().toString();
                        String Password = password.getText().toString();
                        // on below line validating the text input.
                        if (TextUtils.isEmpty(Email) && TextUtils.isEmpty(Password)) {
                            Toast.makeText(LoginD.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // on below line we are calling a sign in method and passing email and password to it.
                        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // on below line we are checking if the task is success or not.
                                if (task.isSuccessful()) {
                                    // on below line we are hiding our progress bar.

                                    Toast.makeText(LoginD.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                                    // on below line we are opening our mainactivity.
                                    Intent intent1 = new Intent(LoginD.this, Read.class);
                                    startActivity(intent1);
                                    finish();
                                } else {
                                    Toast.makeText(LoginD.this, "Please enter valid user credentials..", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }

        });
    }
}