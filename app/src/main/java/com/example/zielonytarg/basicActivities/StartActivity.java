package com.example.zielonytarg.basicActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.example.zielonytarg.advertisements.AddAdvertisementsStartActivity;
import com.example.zielonytarg.advertisements.AdvertismentsByCityActivity;
import com.example.zielonytarg.displayAdvertisements.DisplayMyUserAdvertisements;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {
    Button BtnAddAdvertisements;
    Button BtnMoreInfo;
    Button BtnFindAdvertisements;
    Button BtnDatabaseTest;
    Button BtnMojeKonto;
    Button btn_disp_user_advertisements;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        linkResourcesToFields();

        BtnAddAdvertisements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), AddAdvertisementsStartActivity.class));
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

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

        BtnDatabaseTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DatabaseTestActivity.class);
                startActivity(intent);
            }
        });

        BtnMojeKonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                Intent intent = new Intent(getApplicationContext(), DetailsAccountActivity.class);
                startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_disp_user_advertisements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                Intent intent = new Intent(getApplicationContext(), DisplayMyUserAdvertisements.class);
                startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void linkResourcesToFields() {
        BtnAddAdvertisements = findViewById(R.id.btn_add_advertisements);
        BtnMoreInfo = findViewById(R.id.btn_more_info);
        BtnFindAdvertisements = findViewById(R.id.btn_find_advertisements);
        BtnDatabaseTest = findViewById(R.id.btn_database_test);
        BtnMojeKonto = findViewById(R.id.btn_moje_konto);
        btn_disp_user_advertisements = findViewById(R.id.btn_disp_user_advertisements);
    }
}
