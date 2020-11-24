package com.example.zielonytarg.basicActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DatabaseTestActivity extends AppCompatActivity {
    TextView TextCategoryGet;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        TextCategoryGet = findViewById(R.id.text_category_get);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            TextCategoryGet.setText(FirebaseAuth.getInstance().getCurrentUser().toString());
        }

        Map<String, Object> ads = new HashMap<>();
        ads.put("name", "Los Angeles");
        ads.put("state", "CA");
        ads.put("country", "USA");

        Map<String, Object> data = new HashMap<>();
        data.put("Email", true);
        data.put("FullName", true);
        data.put("Password", true);

        //db.collection("users").document("MOMXMaMttTfnLi5cl9582zXFHvr2")
        //        .set(data, SetOptions.merge());

        FirebaseUser user = fAuth.getCurrentUser();
        db.collection("Users").document(user.getUid())
                .set(ads)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error writing document", e);
                    }
                });

        db.collection("Users").document(user.getUid())
                .set(data, SetOptions.merge());

    }
}
