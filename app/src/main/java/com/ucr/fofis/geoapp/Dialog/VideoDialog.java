package com.ucr.fofis.geoapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.VideoView;

import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.AfterCameraActivity;
import com.ucr.fofis.geoapp.R;

/**
 * Created by william on 08/06/17.
 * Dialogo que maneja el despliegue de videos.
 */

public class VideoDialog extends Dialog {

    Context cntx = null;
    private int posicion;
    VideoView vidVw;

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

        //Se obtiene el video y se reproduce
        vidVw = (VideoView) findViewById(R.id.video_view);
        vidVw.setVideoPath("android.resource://com.ucr.fofis.geoapp/"+p.getVideoId());
        vidVw.requestFocus();
        vidVw.setZOrderOnTop(true);
        vidVw.start();
    }

}