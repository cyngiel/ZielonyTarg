package com.example.zielonytarg.advertisements;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.zielonytarg.basicActivities.SearchActivity;
import com.example.zielonytarg.R;

public class AdvertismentsByCityActivity extends AppCompatActivity {
    Button BtnWarszawa;
    Button BtnKatowice;
    Button BtnLodz;
    Button BtnBydgoszcz;
    Button BtnGdynia;
    Button BtnKrakow;

public class AdvertismentsByCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisments_by_city);

        BtnWarszawa = findViewById(R.id.btn_warszawa);
        BtnKatowice = findViewById(R.id.btn_katowice);
        BtnLodz = findViewById(R.id.btn_lodz);
        BtnBydgoszcz = findViewById(R.id.btn_bydgoszcz);
        BtnGdynia = findViewById(R.id.btn_gdynia);
        BtnKrakow = findViewById(R.id.btn_krakow);

        BtnWarszawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        BtnKatowice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        BtnLodz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        BtnBydgoszcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        BtnGdynia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        BtnKrakow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }
}
