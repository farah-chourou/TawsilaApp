package com.example.tryfortawsila1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tryfortawsila1.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText fullNameET = findViewById(R.id.fullname);
        EditText emailET = findViewById(R.id.email);
        EditText phoneET = findViewById(R.id.phonenum);
        Button update = findViewById(R.id.updtBtn);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        db.collection("users").document(currentUserUid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Users user = documentSnapshot.toObject(Users.class);
                        if (user != null) {
                            fullNameET.setText(user.getFullname());
                            emailET.setText(user.getEmail());
                            phoneET.setText(user.getPhonenum());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Update.this, "Error while fetching user data", Toast.LENGTH_SHORT).show();
                });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = fullNameET.getText().toString().trim();
                String email = emailET.getText().toString().trim();
                String phone = phoneET.getText().toString().trim();

                Map<String, Object> updatedUser = new HashMap<>();
                updatedUser.put("fullname", fullName);
                updatedUser.put("email", email);
                updatedUser.put("phonenum", phone);

                db.collection("users").document(currentUserUid).update(updatedUser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Update.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Update.this, Read.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Update.this, "Error while updating profile", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
