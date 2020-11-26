package com.example.zielonytarg.advertisements;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.example.zielonytarg.basicActivities.SearchActivity;

public class AdvertismentsByCityActivity extends AppCompatActivity {

    Button BtnWarszawa;
    Button BtnKatowice;
    Button BtnLodz;
    Button BtnBydgoszcz;
    Button BtnGdynia;
    Button BtnKrakow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisments_by_city);

        likResToFields();
        initButtonsListeners();


    }

    private void initButtonsListeners() {
        BtnWarszawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                Bundle b = new Bundle();
                b.putString("city", "warszawa");
                intent.putExtras(b);
                startActivity(intent);
                startActivity(intent);
            }
        });

        BtnKatowice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                Bundle b = new Bundle();
                b.putString("city", "katowice");
                intent.putExtras(b);
                startActivity(intent);
                startActivity(intent);
            }
        });

        BtnLodz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                Bundle b = new Bundle();
                b.putString("city", "lodz");
                intent.putExtras(b);
                startActivity(intent);
                startActivity(intent);
            }
        });

        BtnBydgoszcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                Bundle b = new Bundle();
                b.putString("city", "bydgoszcz");
                intent.putExtras(b);
                startActivity(intent);
                startActivity(intent);
            }
        });

        BtnGdynia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                Bundle b = new Bundle();
                b.putString("city", "gdynia");
                intent.putExtras(b);
                startActivity(intent);
                startActivity(intent);
            }
        });

        BtnKrakow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                Bundle b = new Bundle();
                b.putString("city", "krakow");
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    void likResToFields(){
        BtnWarszawa = findViewById(R.id.btn_warszawa);
        BtnKatowice = findViewById(R.id.btn_katowice);
        BtnLodz = findViewById(R.id.btn_lodz);
        BtnBydgoszcz = findViewById(R.id.btn_bydgoszcz);
        BtnGdynia = findViewById(R.id.btn_gdynia);
        BtnKrakow = findViewById(R.id.btn_krakow);
    }
}

