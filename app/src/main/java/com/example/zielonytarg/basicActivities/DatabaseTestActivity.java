package com.example.zielonytarg.basicActivities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseTestActivity extends AppCompatActivity {
    TextView TextCategoryGet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        TextCategoryGet = findViewById(R.id.text_category_get);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //CollectionReference cities = db.collection("cities");

        //Map<String, Object> data1 = new HashMap<>();
        //data1.put("name", "Warszawa");
        //cities.document("ci1").set(data1);


        DocumentReference docRef = db.collection("Users").document("O2qRuvYCagshqC0zuCiI").collection("Ads").document("VdUKxUQxTlAuIExHpYav");
        //DocumentReference df = fStore.collection("Users").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        TextCategoryGet.setText(document.getData().toString());
                    } else {
                        TextCategoryGet.setText("No such document");
                    }
                } else {
                    TextCategoryGet.setText("Failed");
                }
            }
        });

    }
}
