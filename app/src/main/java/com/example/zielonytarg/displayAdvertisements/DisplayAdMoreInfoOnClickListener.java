package com.example.zielonytarg.displayAdvertisements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayAdMoreInfoOnClickListener implements View.OnClickListener {

    String nazwa, opis, cena, miasto, uid;
    Context context;

    public DisplayAdMoreInfoOnClickListener(String nazwa, String opis, String cena, String miasto, String uid, Context context) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena = cena;
        this.miasto = miasto;
        this.uid = uid;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DisplayAdvertisementMoreInfo.class);
        Bundle b = new Bundle();
        b.putString("nazwa", nazwa);
        b.putString("opis", opis);
        b.putString("cena", cena);
        b.putString("miasto", miasto);
        b.putString("uid", uid);
        intent.putExtras(b);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
