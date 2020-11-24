package com.example.zielonytarg.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;

public class SearchProductActivity extends AppCompatActivity {
    Button BtnMarchew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        BtnMarchew = findViewById(R.id.btn_marchew);

        BtnMarchew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}