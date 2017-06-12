package com.ucr.fofis.geoapp;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ucr.fofis.dataaccess.entity.Punto;

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

        Punto p =  (Punto) getOwnerActivity().getIntent().getSerializableExtra("punto");

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