package com.example.zielonytarg.basicActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button loginSignIn, loginSignUp;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linkResourcesToFields();
        firebaseInit();

        loginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        loginSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput(v);
            }
        });

    }

    void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private boolean validatePassword() {
        String passwordInput = loginPassword.getText().toString();
        if (passwordInput.isEmpty()) {
            loginPassword.setError("uzupelnij pole!");
            return false;
        } else {
            loginEmail.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = loginEmail.getText().toString();
        if (emailInput.isEmpty()) {
            loginEmail.setError("uzupelnij pole!");
            return false;
        } else {
            loginEmail.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        } else {
            loginUser();
        }

//        String input = "Email: " + registerEmailLayout.getEditText().getText().toString();
//        input += "\n";
//        input += "Username: " + registerFullNameLayout.getEditText().getText().toString();
//        input += "\n";
//        input += "Password: " + registerPasswordLayout.getEditText().getText().toString();
//        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
    }

    void loginUser() {
        fAuth.signInWithEmailAndPassword(loginEmail.getText().toString(), loginPassword.getText().toString()).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Toast.makeText(LoginActivity.this, "Jestes zalogowany ;)", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), StartActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(LoginActivity.this, "Cos poszlo nie tak :c", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    void linkResourcesToFields() {
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginSignIn = findViewById(R.id.loginSignIn);
        loginSignUp = findViewById(R.id.loginSignUp);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Toast.makeText(LoginActivity.this, "Jestes zalogowany ;)", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), StartActivity.class));
            finish();
        }
    }

}

