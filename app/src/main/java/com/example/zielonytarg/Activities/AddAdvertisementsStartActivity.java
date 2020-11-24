package com.example.zielonytarg.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.zielonytarg.R;
import com.example.zielonytarg.Activities.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddAdvertisementsStartActivity extends AppCompatActivity {

    private FloatingActionButton fabAdsStart;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advertisements_start);

        linkResourcesToFields();
        firebaseInit();
        floatingButtonListener();
    }


    void floatingButtonListener(){
        FloatingActionButton fab = findViewById(R.id.fabAdsStart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    void linkResourcesToFields(){
        fabAdsStart = findViewById(R.id.fabAdsStart);
    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }
}