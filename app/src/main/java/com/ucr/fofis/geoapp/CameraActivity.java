package com.ucr.fofis.geoapp;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;
import com.ucr.fofis.businesslogic.Listener.OnLookAtTargetListener;
import com.ucr.fofis.businesslogic.LocationHelper;
import com.ucr.fofis.businesslogic.Math.MathUtils;
import com.ucr.fofis.businesslogic.SensorHelper;
import com.ucr.fofis.dataaccess.entity.Punto;

public class CameraActivity extends AppCompatActivity implements OnLookAtTargetListener {
    public static final String POINT_TAG = "POINT_TAG";
    double[] K = new double[]{0, 0, 1};

    private Camera mCamera=null;
    private CameraView mCameraView=null;
    ImageView arrow;
    SensorHelper sensorHelper;
    LocationRequest locationRequest;

    Punto point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        point = (Punto)getIntent().getSerializableExtra(POINT_TAG);

        sensorHelper = new SensorHelper(this);
        sensorHelper.setOnLookAtBuildingListener(this);
        sensorHelper.setTarget(point);

        mCamera = Camera.open(0);
        arrow = (ImageView)findViewById(R.id.arrow);
        int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int)((float)width * 0.8), (int)((float)height * 0.8));
        layoutParams.alignWithParent = true;
        arrow.setLayoutParams(layoutParams);

        arrow.setRotation(90); // set arrow's rotation in degrees

        if(mCamera != null){
            mCameraView = new CameraView(this, mCamera);
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);
        }

        ImageButton imgClose = (ImageButton)findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        sensorHelper.start();
    }

    @Override
    public void onStartLookingAtTarget(Punto targetObject) {
        arrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStopLookingAtTarget(Punto targetObject) {
        arrow.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRotationUpdate(float[] rotationVector) {
        //arrow.setRotation(rotationVector[0] * (float)(180.0 / Math.PI));
        String log = "";
        log += rotationVector[0] + ", " + rotationVector[1] + ", " + rotationVector[2];
        Log.i("ROTATION_UPDATE", log);
        LatLng last = LocationHelper.getLastLocation();
        if (last != null) {
            double x = point.getGeoPoint().getLatitude() - last.latitude;
            double y = point.getGeoPoint().getLongitude() - last.longitude;
            double[] dir = new double[]{x, y, 0};

            /*double viewx = Math.cos(rotationVector[0]);
            double viewy = Math.sin(rotationVector[1]);
            double[] viewdir = new double[]{viewx, viewy, 0};*/
            double[] viewdir = new double[3];
            for (int i = 0; i < 3; i++) {
                viewdir[i] = rotationVector[i];
            }

            double angle = MathUtils.angle(viewdir, dir);

            double[] up = K;
            double[] right = MathUtils.cross(dir, up);

            double proj1 = MathUtils.scalar_proj(viewdir, right);
            double[] proj2 = MathUtils.proj(viewdir, up);
            if (proj1 > 0) {
                arrow.setRotation(90);
            } else {
                arrow.setRotation(-90);
            }

            //double realangle = MathUtils.angle(K, proj1);

            //Log.i("ROTATION_UPDATE", "Angle is: " + realangle * (180.0 / Math.PI));

            if (angle < 30.0) {
                if (arrow.getVisibility() == View.VISIBLE) {
                    Toast.makeText(this, "¿Vieron el punto?", Toast.LENGTH_SHORT);
                }
                arrow.setVisibility(View.INVISIBLE);
            } else {
                arrow.setVisibility(View.VISIBLE);
            }
        }
    }
}
