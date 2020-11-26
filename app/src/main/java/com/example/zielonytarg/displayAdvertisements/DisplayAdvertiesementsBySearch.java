package com.example.zielonytarg.displayAdvertisements;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class DisplayAdvertiesementsBySearch extends AppCompatActivity {

    String city, category;

    private GridLayout mLayout;
    DynamicViews dnV;
    Context context;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Task getSizeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_advertiesements_by_search);

        city = getIntent().getExtras().getString("city");
        category = getIntent().getExtras().getString("category");

        firebaseInit();

        mLayout = findViewById(R.id.mylayout2);

        getUserSize();

    }

    private void getUserSize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getSizeTask = fStore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = task.getResult().size();
                        getDocs(i);
                        //Toast.makeText(DisplayAdvertiesementsBySearch.this, "Liczba ogloszen: " + i, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    private DocumentReference getAd(int id) {

        DocumentReference df = fStore.collection("Users").document(String.valueOf(id)).collection("Ads").document(Integer.toString(id));
        return df;
    }

    private void getDocs(final int size) {
        fStore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<DocumentSnapshot> list = task.getResult().getDocuments();

                for (int i = 0; i < size; i++) {
                    getAds(list.get(i));
                }

            }
        });
    }

    private void getAds(final DocumentSnapshot ds){

        ds.getReference().collection("Ads").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<DocumentSnapshot> list = task.getResult().getDocuments();

                for (int i = 0; i < list.size(); i++) {
                    addNextAd(list.get(i));
                }

            }
        });
    }

    private void addNextAd(final DocumentSnapshot ds) {
        dnV = new DynamicViews(context);

        String miasto = ds.getString("miasto");
        String kategoria = ds.getString("kategoria");
        //Toast.makeText(DisplayAdvertiesementsBySearch.this, miasto + " " + kategoria, Toast.LENGTH_SHORT).show();

        if (city.equalsIgnoreCase(miasto) && category.equalsIgnoreCase(kategoria)) {
            String nazwa = ds.getString("nazwa");
            String cena = ds.getString("cena");
            String opis = ds.getString("opis");
            String uid = ds.getString("uid");

            mLayout.addView(dnV.titleTextView(getApplicationContext(), nazwa), 3);
            mLayout.addView(dnV.priceofItem(getApplicationContext(), cena), 4);
            // mLayout.addView(dnV.descriptionTextView(getApplicationContext(), opis), 5);
            Button moreInfo = dnV.moreInfoButton(getApplicationContext());
            moreInfo.setOnClickListener(new DisplayAdMoreInfoOnClickListener(nazwa, opis, cena, miasto, uid, getApplicationContext()));
            mLayout.addView(moreInfo, 5);

        }

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

                        String miasto = task.getResult().getString("miasto");
                        String kategoria = task.getResult().getString("kategoria");
                        //Toast.makeText(DisplayAdvertiesementsBySearch.this, miasto + " " + kategoria, Toast.LENGTH_SHORT).show();

                        if (miasto.equalsIgnoreCase(city) && kategoria.equalsIgnoreCase(category)) {
                            String nazwa = task.getResult().getString("nazwa");
                            String cena = task.getResult().getString("cena");
                            String opis = task.getResult().getString("opis");
                            String uid = task.getResult().getString("uid");

                            mLayout.addView(dnV.titleTextView(getApplicationContext(), nazwa), 3);
                            mLayout.addView(dnV.priceofItem(getApplicationContext(), cena), 4);
                            // mLayout.addView(dnV.descriptionTextView(getApplicationContext(), opis), 5);
                            Button moreInfo = dnV.moreInfoButton(getApplicationContext());
                            moreInfo.setOnClickListener(new DisplayAdMoreInfoOnClickListener(nazwa, opis, cena, miasto, uid, getApplicationContext()));
                            mLayout.addView(moreInfo, 5);
                        }
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