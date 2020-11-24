package com.example.zielonytarg.advertisements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zielonytarg.basicActivities.StartActivity;
import com.example.zielonytarg.R;
import com.example.zielonytarg.basicActivities.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddAdvertisementsStartActivity extends AppCompatActivity {

    private FloatingActionButton fabAdsStart;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button BtnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advertisements_start);

        BtnReturn = findViewById(R.id.btn_return_2);

        BtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });

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