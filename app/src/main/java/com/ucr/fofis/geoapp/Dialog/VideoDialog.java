package com.ucr.fofis.geoapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.AfterCameraActivity;
import com.ucr.fofis.geoapp.R;

/**
 * Created by william on 08/06/17.
 * Dialogo que maneja el despliegue de videos.
 */

public class VideoDialog extends Dialog {

    public VideoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        VideoView vidVw;
        TextView title;
        Punto p =  (Punto) AfterCameraActivity.getInstance().getIntent().getSerializableExtra("punto");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_dialog);
        setTitle(p.getNombre());
        title = (TextView) findViewById(R.id.video_title);
        vidVw = (VideoView) findViewById(R.id.video_view);

        if(AfterCameraActivity.getInstance().isAnimation()){
            title.setText(p.getAnimation().getTitle());
            vidVw.setVideoPath("android.resource://com.ucr.fofis.geoapp/"+p.getAnimation().getId());
        }else{
            title.setText(p.getVideo().getTitle());
            vidVw.setVideoPath("android.resource://com.ucr.fofis.geoapp/"+p.getVideo().getId());
        }

        vidVw.requestFocus();
        vidVw.setZOrderOnTop(true);
        vidVw.start();
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
    }
}