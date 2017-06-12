package com.ucr.fofis.geoapp.Application;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.ucr.fofis.geoapp.GalleryDialog;
import com.ucr.fofis.geoapp.GifDialog;
import com.ucr.fofis.geoapp.R;
import com.ucr.fofis.geoapp.RecommendationDialog;

/**
 * Created by b32080 on 05/06/2017.
 */

public class AfterCameraActivity extends AppCompatActivity {

    LinearLayout gifLayout, carouselDialog;
    Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.after_camera_activity);
        gifLayout = (LinearLayout) findViewById(R.id.gif_dialog);
        gifLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GifDialog gifDialog = new GifDialog(getApplicationContext());
                gifDialog.show();
            }
        });
        carouselDialog = (LinearLayout) findViewById(R.id.image_carousel);
        carouselDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final GalleryDialog galleryDialog = new GalleryDialog();
                galleryDialog.setDialogDismissInterface(new GalleryDialog.DialogDismissInterface() {
                    @Override
                    public void onDialogDismiss() {
                        galleryDialog.dismiss();
                    }
                });
                galleryDialog.show(getSupportFragmentManager(), "\r\n  \r\n \r\n");
            }
        });

    }
}
