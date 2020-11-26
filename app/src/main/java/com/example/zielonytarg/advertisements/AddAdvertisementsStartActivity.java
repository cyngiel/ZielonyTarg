package com.example.zielonytarg.advertisements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        linkResourcesToFields(); //tutaj są wszystkie rzeczy typu findViewById( );
        firebaseInit();
        floatingButtonListener();
        spinnerInit();

        BtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = fAuth.getCurrentUser();

                AddTitleText = findViewById(R.id.addTitleText);
                AddCenaText = findViewById(R.id.addCenaText);
                AddOpisText = findViewById(R.id.addOpisText);

                Map<String, Object> addAd = new HashMap<>();
                addAd.put("Title", AddTitleText.getText().toString());
                String categoryValueFromSpinner = addCategorySpinner.getSelectedItem().toString();
                addAd.put("Category", categoryValueFromSpinner);
                addAd.put("Price", AddCenaText.getText().toString());
                addAd.put("Description", AddOpisText.getText().toString());

                DocumentReference df = fStore.collection("Users").document(user.getUid());
                df.collection("Ads").document().set(addAd);

                Toast.makeText(AddAdvertisementsStartActivity.this, "Advertisements Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });
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
        addCategorySpinner = findViewById(R.id.addAdSpinnerCategory);
        BtnReturn = findViewById(R.id.btnDetailsAccountReturn);
    }

    void spinnerInit() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoires_spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addCategorySpinner.setAdapter(adapter);

    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

}