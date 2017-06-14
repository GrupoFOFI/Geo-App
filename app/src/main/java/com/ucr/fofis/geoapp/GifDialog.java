package com.ucr.fofis.geoapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.Application.AfterCameraActivity;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by oscar on 04/06/17.
 */

public class GifDialog extends Dialog {


    public GifDialog(@NonNull Context context) {
        super(context);
    }

    Punto punto;
    GifImageView gifImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif_dialog);
        punto = (Punto) AfterCameraActivity.getInstance().getIntent().getSerializableExtra("punto");
        gifImageView = (GifImageView) findViewById(R.id.gif_image_view);
        gifImageView.setImageResource(punto.getAnimationId());

    }

    /*
    *                 GifDialog gifDialog = new GifDialog( getContext());
                gifDialog.show();
                */
}
