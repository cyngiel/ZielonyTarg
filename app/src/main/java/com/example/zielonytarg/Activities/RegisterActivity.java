package com.example.zielonytarg.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout registerFullNameLayout, registerEmailLayout, registerPasswordLayout;
    private Button registerSignUp;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        linkResourcesToFields();
        firebaseInit();

        registerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButtonCreateAccountHandle(v);
            }
        });
    }

    // validation & register ////////////////////////////////////////////////////
    public void registerButtonCreateAccountHandle(View v){
        confirmInput(v);
    }

    private boolean validateEmail() {
        String emailInput = registerEmailLayout.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            registerEmailLayout.setError("Field can't be empty");
            return false;
        } else {
            registerEmailLayout.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = registerFullNameLayout.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            registerFullNameLayout.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            registerFullNameLayout.setError("Username too long");
            return false;
        } else {
            registerFullNameLayout.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = registerPasswordLayout.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            registerPasswordLayout.setError("Field can't be empty");
            return false;
        }
        else if (passwordInput.length() < 5) {
            registerFullNameLayout.setError("Password too short");
            return false;
        } else {
            registerPasswordLayout.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        createUser();

//        String input = "Email: " + registerEmailLayout.getEditText().getText().toString();
//        input += "\n";
//        input += "Username: " + registerFullNameLayout.getEditText().getText().toString();
//        input += "\n";
//        input += "Password: " + registerPasswordLayout.getEditText().getText().toString();
//        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
    }

    // create user
    private void createUser(){
        fAuth.createUserWithEmailAndPassword(registerEmailLayout.getEditText().getText().toString(),registerPasswordLayout.getEditText().getText().toString()).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = fAuth.getCurrentUser();
                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();

                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("FullName", registerFullNameLayout.getEditText().getText().toString());
                        userInfo.put("Email", registerEmailLayout.getEditText().getText().toString());
                        userInfo.put("Password", registerPasswordLayout.getEditText().getText().toString());

                        fStore.collection("users").document("dupa").set(userInfo);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Failed to Create Account", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // on start init //////////////////////////////////////////////////////////////

    void firebaseInit(){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    void linkResourcesToFields() {
        registerFullNameLayout = findViewById(R.id.registerFullNameLayout);
        registerEmailLayout = findViewById(R.id.registerEmailLayout);
        registerPasswordLayout = findViewById(R.id.registerPasswordLayout);
        registerSignUp = findViewById(R.id.registerSignUp);
    }

}