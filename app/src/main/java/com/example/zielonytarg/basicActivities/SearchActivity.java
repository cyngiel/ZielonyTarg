package com.example.zielonytarg.basicActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.example.zielonytarg.displayAdvertisements.DisplayAdvertiesementsBySearch;

public class SearchActivity extends AppCompatActivity {
    Button btnOwoce, btnWarzywa, btnMiody, btnRyby, btnMieso, btnBakalie, btnInne, btnSoki;

    String city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        city = getIntent().getExtras().getString("city");

        linkResToFields();
        initBtnListeners();
    }

    private void initBtnListeners() {
        btnSoki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAdvertiesementsBySearch.class);
                Bundle b = new Bundle();
                b.putString("city", city);
                b.putString("category", "soki");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnWarzywa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAdvertiesementsBySearch.class);
                Bundle b = new Bundle();
                b.putString("city", city);
                b.putString("category", "warzywa");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnRyby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAdvertiesementsBySearch.class);
                Bundle b = new Bundle();
                b.putString("city", city);
                b.putString("category", "ryby");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnMiody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAdvertiesementsBySearch.class);
                Bundle b = new Bundle();
                b.putString("city", city);
                b.putString("category", "miody");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnMieso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAdvertiesementsBySearch.class);
                Bundle b = new Bundle();
                b.putString("city", city);
                b.putString("category", "mieso");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnBakalie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAdvertiesementsBySearch.class);
                Bundle b = new Bundle();
                b.putString("city", city);
                b.putString("category", "bakalie");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnOwoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAdvertiesementsBySearch.class);
                Bundle b = new Bundle();
                b.putString("city", city);
                b.putString("category", "owoce");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnInne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAdvertiesementsBySearch.class);
                Bundle b = new Bundle();
                b.putString("city", city);
                b.putString("category", "inne");
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    private void linkResToFields() {
        btnOwoce = findViewById(R.id.search_btn_owoce);
        btnBakalie = findViewById(R.id.search_btn_bakalie);
        btnInne = findViewById(R.id.search_btn_inne);
        btnMieso = findViewById(R.id.search_btn_mieso);
        btnMiody = findViewById(R.id.search_btn_miody);
        btnRyby = findViewById(R.id.search_btn_ryby);
        btnWarzywa = findViewById(R.id.search_btn_warzywa);
        btnSoki = findViewById(R.id.search_btn_soki);
    }
}
