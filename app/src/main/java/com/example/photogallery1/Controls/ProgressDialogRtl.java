package com.example.photogallery1.Controls;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;






public class ProgressDialogRtl extends ProgressDialog {
    Context context;
    public ProgressDialogRtl(Context context) {
        super(context);
        this.context=context;
    }

    public ProgressDialogRtl(Context context, int theme) {
        super(context, theme);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = this.findViewById(android.R.id.message);
        if (view != null) {
            // Shouldn't be null. Just to be paranoid enough.
           // FontCache.setIranSansTypeface(view, context);
        }
    }


}
