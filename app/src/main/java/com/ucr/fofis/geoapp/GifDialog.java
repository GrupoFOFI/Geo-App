package com.ucr.fofis.geoapp;

import android.app.*;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by oscar on 04/06/17.
 */

public class GifDialog extends Dialog {


    public GifDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif_dialog);
    }

    /*
    *                 GifDialog gifDialog = new GifDialog( getContext());
                gifDialog.show();
                */
}
