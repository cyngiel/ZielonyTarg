package com.example.zielonytarg.advertisements;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AddAdvertisementsStartActivity extends AppCompatActivity {

    private FloatingActionButton fabAdsStart;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button BtnReturn;
    EditText AddTitleText, AddCenaText, AddOpisText;
    Spinner addCategorySpinner;
    String uid;
    Task getSizeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advertisements_start);
        linkResourcesToFields(); //tutaj są wszystkie rzeczy typu findViewById( );
        firebaseInit();
        //floatingButtonListener();
        spinnerInit();

        BtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = fAuth.getCurrentUser();

                uid = user.getUid();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getSizeTask = fStore.collection("Users").document(uid).collection("Ads").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                int i = task.getResult().size();
                                addUserAds(i);
                            }
                        });
                    }
                }).start();

            }
        });
    }

    private void addUserAds(int i) {
        Map<String, Object> addAd = new HashMap<>();
        addAd.put("nazwa", AddTitleText.getText().toString());
        String categoryValueFromSpinner = addCategorySpinner.getSelectedItem().toString();
        addAd.put("kategoria", categoryValueFromSpinner);
        addAd.put("cena", AddCenaText.getText().toString());
        addAd.put("opis", AddOpisText.getText().toString());
        addAd.put("miasto", addCategorySpinner.getSelectedItem().toString());
        addAd.put("userID", uid);

        DocumentReference df = fStore.collection("Users").document(uid);
        df.collection("Ads").document(String.valueOf(i)).set(addAd).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddAdvertisementsStartActivity.this, "Dodano ogłoszenie", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                finish();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddAdvertisementsStartActivity.this, "Błąd dodawania ogłoszenia", Toast.LENGTH_SHORT).show();
            }
        });
    }


/*    void floatingButtonListener(){
        FloatingActionButton fab = findViewById(R.id.fabAdsStart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }*/

    void spinnerInit() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoires_spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addCategorySpinner.setAdapter(adapter);
    }

    void linkResourcesToFields(){
        //fabAdsStart = findViewById(R.id.fabAdsStart);
        addCategorySpinner = findViewById(R.id.addAdSpinnerCategory);
        BtnReturn = findViewById(R.id.btnDetailsAccountReturn);
        AddTitleText = findViewById(R.id.addTitleText);
        AddCenaText = findViewById(R.id.addCenaText);
        AddOpisText = findViewById(R.id.addOpisText);
    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

}