package com.example.zielonytarg.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zielonytarg.R;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button mainSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkResourcesToFields();

        mainSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainOpenLoginActivity(v);
            }
        });

    }

    public void mainOpenLoginActivity(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    void linkResourcesToFields() {
        mainSignInButton = findViewById(R.id.mainSignInButton);

    }

}