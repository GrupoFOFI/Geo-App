package com.ucr.fofis.geoapp.Application;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.AudioDialog;
import com.ucr.fofis.geoapp.GalleryDialog;
import com.ucr.fofis.geoapp.GifDialog;
import com.ucr.fofis.geoapp.R;
import com.ucr.fofis.geoapp.VideoDialog;

/**
 * Created by b32080 on 05/06/2017.
 */

public class AfterCameraActivity extends AppCompatActivity {

    LinearLayout gifLayout, carouselDialog, videoLayout, audioLayout;
    Activity activity = this;
    Punto punto;

    private static AfterCameraActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_camera_activity);
        instance = this;
        punto = (Punto)getIntent().getSerializableExtra("punto");
        TextView title = (TextView) findViewById(R.id.title_point);
        title.setText(punto.getNombre());
        gifLayout = (LinearLayout) findViewById(R.id.gif_dialog);
        gifLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (punto.getAnimationId() == 0) {
                    Toast.makeText(AfterCameraActivity.this, "No existen animaciones asociadas a este punto", Toast.LENGTH_SHORT).show();
                } else {
                    GifDialog gifDialog = new GifDialog(AfterCameraActivity.this);
                    gifDialog.show();
                }
            }
        });
        carouselDialog = (LinearLayout) findViewById(R.id.image_carousel);
        carouselDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (punto.getImagenes() == null) {
                    Toast.makeText(AfterCameraActivity.this, "No existen imágenes asociadas a este punto", Toast.LENGTH_SHORT).show();
                } else {
                    final GalleryDialog galleryDialog = new GalleryDialog();
                    galleryDialog.setDialogDismissInterface(new GalleryDialog.DialogDismissInterface() {
                        @Override
                        public void onDialogDismiss() {
                            galleryDialog.dismiss();
                        }
                    });
                    galleryDialog.show(getSupportFragmentManager(), "\r\n  \r\n \r\n");
                }
            }
        });
        videoLayout = (LinearLayout) findViewById(R.id.video_dialog);
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (punto.getVideoId() == 0) {
                    Toast.makeText(AfterCameraActivity.this, "No existen videos asociados a este punto", Toast.LENGTH_SHORT).show();
                } else {
                    VideoDialog videoDialog = new VideoDialog(AfterCameraActivity.this);
                    videoDialog.show();
                }
            }
        });
        audioLayout = (LinearLayout) findViewById(R.id.audio_dialog);
        audioLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (punto.getAudios() == null || punto.getAudios().length == 0) {
                    Toast.makeText(AfterCameraActivity.this, "No existen audios asociados a este punto", Toast.LENGTH_SHORT).show();
                } else {
                    AudioDialog audioDialog = new AudioDialog(AfterCameraActivity.this);
                    audioDialog.show();
                }
            }
        });
    }

    public static AfterCameraActivity getInstance() {
        return instance;
    }
}
