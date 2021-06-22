package com.example.robotechvalley.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.robotechvalley.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminLogIn extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private EditText email, password;
    private Button btnLogin;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_in);


        email = findViewById(R.id.IdAdminLogInEmail);
        password = findViewById(R.id.IdAdminPasswordLogIn);

        btnLogin = findViewById(R.id.IdAdminBtnLogIn);

        progressBar = findViewById(R.id.AdminProgressbar);

        mAuth = FirebaseAuth.getInstance();


    }

    public void AdminlogInWork(View view) {


        String logInEmail = email.getText().toString().trim();
        String logInPassword = password.getText().toString().trim();


        if (logInEmail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(logInEmail).matches()) {
            email.setError("Invalid Email Id");
            email.requestFocus();
            return;

        }

        if (logInPassword.isEmpty()) {
            password.setError("Password is Required");
            password.requestFocus();

            return;
        }

        if (logInPassword.length() < 6) {
            password.setError("Min length should be 6 characters");
            password.requestFocus();

            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(logInEmail, logInPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            DatabaseReference databaseReference = database.getReference(mAuth.getUid());

                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

//


                                    Intent intent = new Intent(adminLogIn.this, adimn.class);


                                    startActivity(intent);


//
                                    Toast.makeText(adminLogIn.this, " Successfully Logged in", Toast.LENGTH_SHORT).show();

                                    progressBar.setVisibility(View.GONE);

                                    email.setText("");
                                    password.setText("");



//
//                                    startActivity(intent);


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        } else {
                            email.setError("");

                            password.setError("");

                            Toast.makeText(adminLogIn.this, "Invalid password/email", Toast.LENGTH_LONG).show();

                        }

                    }
                });


    }
}
