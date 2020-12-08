package com.example.zielonytarg.displayAdvertisements;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DynamicViews {
    Context context;

    public DynamicViews(Context context) {
        this.context = context;
    }

    public TextView descriptionTextView(Context context, String text) {
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView((context));
        textView.setLayoutParams(lparams);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setText(" " + text + " ");
        textView.setMaxEms(8);
        return textView;
    }

    public TextView titleTextView(Context context, String text) {
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView((context));
        textView.setLayoutParams(lparams);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setText(" " + text + " ");
        textView.setMaxEms(8);
        return textView;
    }

    public TextView priceofItem(Context context, String text){
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView((context));
        textView.setLayoutParams(lparams);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setText(" " + text + " ");
        textView.setMaxEms(8);
        return textView;
    }

    public Button moreInfoButton(Context context) {
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final Button button = new Button((context));
        button.setLayoutParams(lparams);
        button.setTextSize(20);
        button.setTextColor(Color.parseColor("#009933"));
        button.setMaxEms(8);
        button.setText("Więcej");
        button.setBackgroundColor(Color.parseColor("#F3F4F0"));
       // button.setTextColor(Color.parseColor("#009933"));
        return button;
    }

}
