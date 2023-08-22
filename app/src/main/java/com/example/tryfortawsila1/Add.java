package com.example.tryfortawsila1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tryfortawsila1.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add extends AppCompatActivity {

    private static final String COLLECTION_NAME = "users"; // Firestore collection name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText fullNameET = findViewById(R.id.fullnameU);
        EditText phoneET = findViewById(R.id.phoneNumU);
        EditText emailET = findViewById(R.id.emailU);
        Button addUser = findViewById(R.id.add2);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = fullNameET.getText().toString().trim();
                String phone = phoneET.getText().toString().trim();
                String email = emailET.getText().toString().trim();

                if (fullName.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    Toast.makeText(Add.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                DocumentReference userDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                userDocRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Toast.makeText(Add.this, "User with the same ID already exists", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("fullname", fullName);
                                    user.put("phonenum", phone);
                                    user.put("email", email);

                                    DocumentReference newUserDocRef = db.collection(COLLECTION_NAME).document(currentUserUid);
                                    newUserDocRef.set(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(Add.this, "User profile added successfully", Toast.LENGTH_SHORT).show();
                                                    fullNameET.getText().clear();
                                                    phoneET.getText().clear();
                                                    emailET.getText().clear();
                                                    Intent intent = new Intent(Add.this, Read.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(Add.this, "Error adding user profile", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Add.this, "Error checking user existence", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



    }
}
