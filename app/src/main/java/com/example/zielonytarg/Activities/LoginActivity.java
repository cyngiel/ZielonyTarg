package com.example.zielonytarg.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zielonytarg.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText loginEmailLayout, loginPasswordLayout;
    private Button loginSignIn, loginSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linkResourcesToFields();

        loginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
    }

    void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    void linkResourcesToFields() {
        loginEmailLayout = findViewById(R.id.loginEmail);
        loginPasswordLayout = findViewById(R.id.loginPassword);
        loginSignIn = findViewById(R.id.loginSignIn);
        loginSignUp = findViewById(R.id.loginSignUp);
    }
}