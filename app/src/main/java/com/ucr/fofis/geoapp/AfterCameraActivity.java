package com.ucr.fofis.geoapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.Dialog.AudioDialog;
import com.ucr.fofis.geoapp.Dialog.GalleryDialog;
import com.ucr.fofis.geoapp.Dialog.VideoDialog;

/**
 * Actividad usada después de la cámara y después de que se ha detectado y apuntado un Punto.
 * Contiene los botones de la información extra de cada punto.
 *
 */

public class AfterCameraActivity extends AppCompatActivity {

    Button carouselDialog, videoLayout, audioLayout, gifLayout;
    Activity activity = this;
    Punto punto;
    private boolean animation;
    private static AfterCameraActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_camera_activity);
        instance = this;
        punto = (Punto)getIntent().getSerializableExtra("punto");
        TextView title = (TextView) findViewById(R.id.title_point);
        title.setText(punto.getNombre());
        gifLayout = (Button) findViewById(R.id.gif_dialog);
        gifLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (punto.getAnimation().getId() == 0) {
                    Toast.makeText(AfterCameraActivity.this, "No existen animaciones asociadas a este punto", Toast.LENGTH_SHORT).show();
                } else {
                    animation = true;
                    VideoDialog videoDialog = new VideoDialog(AfterCameraActivity.this);
                    videoDialog.show();
                }
            }
        });
        carouselDialog = (Button) findViewById(R.id.image_carousel);
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
        videoLayout = (Button) findViewById(R.id.video_dialog);
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (punto.getVideo().getId() == 0) {
                    Toast.makeText(AfterCameraActivity.this, "No existen videos asociados a este punto", Toast.LENGTH_SHORT).show();
                } else {
                    animation = false;
                    VideoDialog videoDialog = new VideoDialog(AfterCameraActivity.this);
                    videoDialog.show();
                }
            }
        });
        audioLayout = (Button) findViewById(R.id.audio_dialog);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static AfterCameraActivity getInstance() {
        return instance;
    }

    public boolean isAnimation() {
        return animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }
}
