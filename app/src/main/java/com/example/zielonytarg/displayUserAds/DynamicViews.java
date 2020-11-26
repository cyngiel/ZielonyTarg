package com.example.zielonytarg.displayUserAds;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
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

}
