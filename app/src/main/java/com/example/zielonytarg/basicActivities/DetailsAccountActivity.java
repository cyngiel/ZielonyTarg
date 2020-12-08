package com.example.zielonytarg.basicActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.example.zielonytarg.advertisements.AddAdvertisementsStartActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailsAccountActivity extends AppCompatActivity {
    TextView detailsAccountProductName, detailsAccountProducent, detailsAccountTel, detailsAccountCity;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button btnDetailsAccountReturn, btnDetailsAccountLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_account);

        firebaseInit();
        linkResourcesToFields();

        try {
            FirebaseUser user = fAuth.getCurrentUser();
            DocumentReference docRef = fStore.collection("Users").document(user.getUid());
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot dcumentSnapshot ) {
                    if (dcumentSnapshot.exists()) {
                        String fullname = dcumentSnapshot.getString("FullName");
                        detailsAccountProductName.setText(fullname);
                        String producent = dcumentSnapshot.getString("FullName");
                        detailsAccountProducent.setText(producent);
                        String tel = dcumentSnapshot.getString("Tel");
                        detailsAccountTel.setText(tel);
                        String city = dcumentSnapshot.getString("City");
                        detailsAccountCity.setText(city);

                    } else {
                        Toast.makeText(DetailsAccountActivity.this, "Nie jesteś zalogowany!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (NullPointerException e) {
            Toast.makeText(DetailsAccountActivity.this, "Nie jesteś zalogowany!", Toast.LENGTH_SHORT).show();
        }

        btnDetailsAccountReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });

        btnDetailsAccountLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Toast.makeText(DetailsAccountActivity.this, "Wylogowano", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

    }

    void linkResourcesToFields(){
        detailsAccountProductName = findViewById(R.id.detailsAccountProductName);
        detailsAccountProducent = findViewById(R.id.detailsAccountProducent);
        detailsAccountTel = findViewById(R.id.detailsAccountTel);
        detailsAccountCity = findViewById(R.id.detailsAccountCity);
        btnDetailsAccountReturn = findViewById(R.id.btnDetailsAccountReturn);
        btnDetailsAccountLogout = findViewById(R.id.btnDetailsAccountLogout);
    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

}
