package com.ucr.fofis.geoapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.VideoView;

import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.Application.AfterCameraActivity;

/**
 * Created by william on 08/06/17.
 */

public class VideoDialog extends Dialog {

    Context cntx = null;


    public VideoDialog(@NonNull Context context) {
        super(context);
        cntx = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Punto p =  (Punto) AfterCameraActivity.getInstance().getIntent().getSerializableExtra("punto");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_dialog);
        setTitle(p.getNombre());

        VideoView vidVw = (VideoView) findViewById(R.id.video_view);
        vidVw.setVideoPath("android.resource://com.ucr.fofis.geoapp/"+p.getVideoId());
        vidVw.requestFocus();
        vidVw.setZOrderOnTop(true);

        vidVw.start();
    }

}