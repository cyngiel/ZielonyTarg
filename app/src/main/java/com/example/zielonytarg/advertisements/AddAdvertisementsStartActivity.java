package com.example.zielonytarg.advertisements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.zielonytarg.basicActivities.RegisterActivity;
import com.example.zielonytarg.basicActivities.StartActivity;
import com.example.zielonytarg.R;
import com.example.zielonytarg.basicActivities.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAdvertisementsStartActivity extends AppCompatActivity {

    private FloatingActionButton fabAdsStart;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button BtnReturn;
    EditText AddTitleText, AddCenaText, AddOpisText;
    Spinner addCategorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advertisements_start);

        addCategorySpinner = findViewById(R.id.addCategorySpinner);
        BtnReturn = findViewById(R.id.btn_return_2);

        spinnerInit();

        BtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = fAuth.getCurrentUser();
                //Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();

                AddTitleText = findViewById(R.id.addTitleText);
                //TextInputCategory = findViewById(R.id.addCategorySpinner);
                AddCenaText = findViewById(R.id.AddCenaText);
                AddOpisText = findViewById(R.id.addOpisText);

                Map<String, Object> addAd = new HashMap<>();
                addAd.put("Title", AddTitleText.getText().toString());
                String categoryValueFromSpinner = addCategorySpinner.getSelectedItem().toString();
                addAd.put("Category", categoryValueFromSpinner);
                addAd.put("Price", AddCenaText.getText().toString());
                addAd.put("Description", AddOpisText.getText().toString());

                DocumentReference df = fStore.collection("Users").document(user.getUid());
                //df.set(addAd);
                df.collection("Ads").document().set(addAd);

                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });

        linkResourcesToFields();
        firebaseInit();
        floatingButtonListener();
    }


    void floatingButtonListener(){
        FloatingActionButton fab = findViewById(R.id.fabAdsStart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    void spinnerInit() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoires_spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addCategorySpinner.setAdapter(adapter);
    }

    void linkResourcesToFields(){
        fabAdsStart = findViewById(R.id.fabAdsStart);
    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }
}