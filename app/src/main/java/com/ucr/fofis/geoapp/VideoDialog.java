package com.ucr.fofis.geoapp;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.ucr.fofis.dataaccess.entity.Punto;

/**
 * Created by william on 08/06/17.
 */

public class VideoDialog extends Dialog {


    public VideoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Video");
        setContentView(R.layout.video_dialog);

        final VideoView vid = (VideoView) findViewById(R.id.video_view);
        String uriPath = "android.resource://com.ucr.fofis.dataaccess/"+R.raw.v29_6;
        Uri uri = Uri.parse(uriPath);
        vid.setVideoURI(uri);
        vid.requestFocus();
        vid.start();
        Punto p =  (Punto) getOwnerActivity().getIntent().getSerializableExtra("punto");
        p.getVideoId();
        Button dialogButton = (Button) findViewById(R.id.video_button);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    dismiss();
                }
            }

        );

    }

}