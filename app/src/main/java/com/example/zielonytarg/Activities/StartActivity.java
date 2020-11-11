package com.example.zielonytarg.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;

public class StartActivity extends AppCompatActivity {
    Button BtnAddAdvertisements;
    Button BtnMoreInfo;
    Button BtnFindAdvertisements;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        BtnAddAdvertisements = findViewById(R.id.btn_add_advertisements);
        BtnMoreInfo = findViewById(R.id.btn_more_info);
        BtnFindAdvertisements = findViewById(R.id.btn_find_advertisements);

        BtnAddAdvertisements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        BtnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MoreInfoActivity.class);
                startActivity(intent);
            }
        });

        BtnFindAdvertisements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdvertismentsByCityActivity.class);
                startActivity(intent);
            }
        });

    }
}
