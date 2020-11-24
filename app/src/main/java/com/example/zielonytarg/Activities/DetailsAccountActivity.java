package com.example.zielonytarg.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailsAccountActivity extends AppCompatActivity {
    TextView TextFullName;
    TextView TextProducent;
    TextView TextTel;
    TextView TextCity;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_account);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        TextFullName = findViewById(R.id.text_full_name);
        TextProducent = findViewById(R.id.text_producent);
        TextTel = findViewById(R.id.text_tel);
        TextCity = findViewById(R.id.text_city);

        FirebaseUser user = fAuth.getCurrentUser();
        DocumentReference docRef = fStore.collection("Users").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot dcumentSnapshot ) {
                if (dcumentSnapshot.exists()) {
                    String fullname = dcumentSnapshot.getString("FullName");
                    TextFullName .setText(fullname);
                    String producent = dcumentSnapshot.getString("FullName");
                    TextProducent .setText(producent);
                    String tel = dcumentSnapshot.getString("Tel");
                    TextTel .setText(tel);
                    String city = dcumentSnapshot.getString("City");
                    TextCity .setText(city);

                } else {
                    TextFullName.setText("Failed");
                }
            }
        });

    }
}
