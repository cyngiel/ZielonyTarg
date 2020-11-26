package com.example.zielonytarg.displayAdvertisements;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DisplayAdvertisementMoreInfo extends AppCompatActivity {

    String nazwa, opis, cena, miasto, name, city, tel, uid;
    TextView disp_ad_nazwa, disp_ad_cena, disp_ad_opis, disp_ad_miasto, disp_ad_name, disp_ad_tel, disp_ad_city;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Task getSizeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_advertisement_more_info);

        nazwa = getIntent().getExtras().getString("nazwa");
        cena = getIntent().getExtras().getString("cena");
        opis = getIntent().getExtras().getString("opis");
        miasto = getIntent().getExtras().getString("miasto");
        uid = getIntent().getExtras().getString("uid");

        linkResToFields();
        firebaseInit();
        getUserInfo();

    }

    private void getUserInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getSizeTask = fStore.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            city = document.getString("City");
                            name = document.getString("FullName");
                            tel = document.getString("Tel");
                            setText();

                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }


    private void setText() {
        disp_ad_nazwa.setText("nazwa: " + nazwa);
        disp_ad_cena.setText(" cena: " + cena);
        disp_ad_opis.setText("opis: " + opis);
        disp_ad_miasto.setText(" miasto: " + miasto);
        disp_ad_name.setText("Imie nazwisko: " + name);
        disp_ad_tel.setText(" miasto: " + city);
        disp_ad_city.setText(" tel: " + tel);
    }

    private void linkResToFields() {
        disp_ad_nazwa = findViewById(R.id.disp_ad_nazwa);
        disp_ad_cena = findViewById(R.id.disp_ad_cena);
        disp_ad_opis = findViewById(R.id.disp_ad_opis);
        disp_ad_miasto = findViewById(R.id.disp_ad_miasto);
        disp_ad_name = findViewById(R.id.disp_ad_name);
        disp_ad_tel = findViewById(R.id.disp_ad_tel);
        disp_ad_city = findViewById(R.id.disp_ad_city);
    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }
}