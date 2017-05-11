package com.ucr.fofis.geoapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class CameraActivity extends AppCompatActivity {

    private Camera mCamera=null;
    private CameraView mCameraView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        // Assume thisActivity is the current activity
        int cameraPermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        if(cameraPermissionCheck != PackageManager.PERMISSION_GRANTED){

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)){

                // Explanation

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        1);
            }
        }

        if(mCamera != null){
            mCameraView = new CameraView(this, mCamera);
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);
        }

        ImageButton imgClose = (ImageButton)findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.exit(0);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    try{
                        mCamera = Camera.open();
                    }catch (Exception e){
                        Log.d("ERROR", "Failed to get camera "+e.getMessage());
                    }


                } else {


                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
