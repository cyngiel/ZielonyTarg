package com.example.zielonytarg.basicActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;

public class SearchActivity extends AppCompatActivity {
    Button BtnOwoce;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        BtnOwoce = findViewById(R.id.btn_warzywa);

        BtnOwoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchProductActivity.class);
                startActivity(intent);
            }
        });


    }
}
