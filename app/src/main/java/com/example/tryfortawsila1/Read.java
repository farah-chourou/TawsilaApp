package com.example.tryfortawsila1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class Read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        FirebaseApp.initializeApp(Read.this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Button add = findViewById(R.id.add3);
        add.setOnClickListener(view -> startActivity(new Intent(Read.this, Add.class)));
        Button update = findViewById(R.id.updateProfile);
        update.setOnClickListener(view -> startActivity(new Intent(Read.this, Update.class)));

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUserUid != null) {
            db.collection("users").document(currentUserUid).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Users user = documentSnapshot.toObject(Users.class);
                                if (user != null) {
                                    TextView userFullName = findViewById(R.id.userFullName);
                                    TextView userPhoneNumber = findViewById(R.id.userPhoneNumber);
                                    TextView userEmail = findViewById(R.id.userEmail);

                                    userFullName.setText("Full Name: " + user.getFullname());
                                    userPhoneNumber.setText("Phone Number: " + user.getPhonenum());
                                    userEmail.setText("Email: " + user.getEmail());
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Read.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        ImageView delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (currentUserUid == null) {
                    Toast.makeText(Read.this, "No user authenticated", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("users").document(currentUserUid).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Read.this, "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Read.this, Read.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Read.this, "Error while deleting user", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
