package com.example.zielonytarg.displayUserAds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.zielonytarg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.ExecutionException;

public class DisplayUserAdvertisements extends AppCompatActivity {

    private GridLayout mLayout;
    DynamicViews dnV;
    Context context;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Task getSizeTask;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_advertisements);

        firebaseInit();

        mLayout = findViewById(R.id.mylayout);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getSizeTask = fStore.collection("Users").document(uid).collection("Ads").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

                        mLayout.addView(dnV.titleTextView(getApplicationContext(), task.getResult().getString("nazwa")), 3);
                        mLayout.addView(dnV.priceofItem(getApplicationContext(), task.getResult().getString("cena")), 4);
                        mLayout.addView(dnV.descriptionTextView(getApplicationContext(), task.getResult().getString("opis")), 5);
                    }
                });
            }
        }).start();

    }

    void firebaseInit() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        uid = fAuth.getCurrentUser().getUid();
    }
}