package com.example.android.bookmyvisitzmedios.LoginSignupUser;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookmyvisitzmedios.Homescreen.HomeScreen;
import com.example.android.bookmyvisitzmedios.OtpLogin.Registration_atri;
import com.example.android.bookmyvisitzmedios.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    TextView logintext;
    EditText Username , Email , Password , Phone ;
    private Button btn_signUp;
    DatabaseReference firebaseDatabase;
    FirebaseAuth firebaseAuth;
    private static final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        logintext = findViewById(R.id.logintext);
        Username = findViewById(R.id.fullname);
        Phone = findViewById(R.id.phoneno);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        btn_signUp = findViewById(R.id.btn_signUp);



        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Register");
        firebaseAuth = FirebaseAuth.getInstance();

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = Username.getText().toString().trim();
                final String phone   = Phone.getText().toString().trim();
                final String password = Password.getText().toString().trim();
                final String email = Email.getText().toString().trim();

                if(username.isEmpty())
                {
                    Username.setError("enter correct username");
                    Username.requestFocus();
                    return;
                }

                if(phone.isEmpty())
                {
                    Phone.setError("enter mobile number");
                    Phone.requestFocus();
                    return;
                }
                if(phone.length() != 10)
                {
                    Phone.setError("enter valid mobile number");
                    Phone.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                   Password.setError("please enter password ");
                   Password.requestFocus();
                   return;
                }
                if(password.length() <= 6 )
                {
                    Password.setError("password must be greater then 6 character");
                    Password.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Email.setError("enter valid email Id");
                    Email.requestFocus();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Registration_atri information= new Registration_atri(username , email , phone , password);

                                    FirebaseDatabase.getInstance().getReference("Register")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(SignupActivity.this, "Successully .",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignupActivity.this  , HomeScreen.class));
                                        }
                                    });

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }


}