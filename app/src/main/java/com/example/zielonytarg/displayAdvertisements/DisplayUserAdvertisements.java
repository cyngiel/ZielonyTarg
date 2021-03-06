package com.example.zielonytarg.displayAdvertisements;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DisplayUserAdvertisements extends AppCompatActivity {

    private GridLayout mLayout;
    DynamicViews dnV;
    Context context;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_advertisements);

        firebaseInit();
        uid = getIntent().getExtras().getString("userID");
        mLayout = findViewById(R.id.mylayout3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                fStore.collection("Users").document(uid).collection("Ads").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = task.getResult().size();
                        getUserAds(i);
                        Toast.makeText(DisplayUserAdvertisements.this, "Liczba ogloszen: " + i, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    private void getUserAds(int size){
        DocumentReference df;
        if(size > 0) {
            for (int i = 0; i < size; i++) {
                df = getAd(i);
                addNextAd(df);
            }
        }
        else
            Toast.makeText(DisplayUserAdvertisements.this, "Nie masz żadnych ogłoszeń", Toast.LENGTH_SHORT).show();
    }

    private DocumentReference getAd(int id){
        DocumentReference df = fStore.collection("Users").document(uid).collection("Ads").document(Integer.toString(id));
        return df;
    }

    private void addNextAd(final DocumentReference df) {
        dnV = new DynamicViews(context);
        //final String[] documentValues = new String[3]; //0-nazwa  1-cena   2-opis

        new Thread(new Runnable() {
            @Override
            public void run() {
                df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        String nazwa =  task.getResult().getString("nazwa");
                        String cena =  task.getResult().getString("cena");
                        String opis =  task.getResult().getString("opis");
                        String miasto =  task.getResult().getString("miasto");
                        String uid =  task.getResult().getString("userID");

                        mLayout.addView(dnV.titleTextView(getApplicationContext(), nazwa), 3);
                        mLayout.addView(dnV.priceofItem(getApplicationContext(), cena), 4);
                        // mLayout.addView(dnV.descriptionTextView(getApplicationContext(), opis), 5);
                        Button moreInfo = dnV.moreInfoButton(getApplicationContext());
                        moreInfo.setOnClickListener(new DisplayAdMoreInfoOnClickListener(nazwa, opis, cena, miasto, uid, getApplicationContext()));
                        mLayout.addView(moreInfo, 5);
                    }


                });
            }


        }).start();

    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }
}