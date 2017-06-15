package com.ucr.fofis.geoapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
import com.ucr.fofis.geoapp.Application.AfterCameraActivity;

import static com.ucr.fofis.geoapp.R.id.btnStart;

public class CameraActivity extends AppCompatActivity implements OnLookAtTargetListener {
    public static final String POINT_TAG = "POINT_TAG";
    double[] J = new double[]{0, 1, 0};
    double[] K = new double[]{0, 0, 1};

    private Camera mCamera=null;
    private CameraView mCameraView=null;
    ImageView arrow;
    SensorHelper sensorHelper;
    LocationRequest locationRequest;

    Punto point;
/*asigna el sensorhelper , la imagen para la brujula , el punto de interes(target) */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //Setea el punto de interes que hizo que la camara pudiera ser abierta. El punto viene desde el Map Activity
        point = (Punto)getIntent().getSerializableExtra(POINT_TAG);

        //Esconde el boton de abrir geopunto
        Button openGP = (Button) findViewById(btnStart);
        openGP.setEnabled(false);
        openGP.setVisibility(View.GONE);


        //Sensor para la flecha de la camara para indicar la direccion del punto
        sensorHelper = new SensorHelper(this);
        sensorHelper.setOnLookAtBuildingListener(this);
        sensorHelper.setTarget(point);

        //trata de adquirir la camara ( el recurso )
        mCamera = Camera.open(0);
        arrow = (ImageView)findViewById(R.id.arrow);
        int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int)((float)width * 0.8), (int)((float)height * 0.8));
        layoutParams.alignWithParent = true;
        arrow.setLayoutParams(layoutParams);

        arrow.setRotation(90); // set arrow's rotation in degrees

        //Si la camara fue adquirida, desplegarla
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
    protected void onResume() {
        super.onResume();
        mCamera = Camera.open(0);
        if(mCamera != null){
            mCameraView = new CameraView(this, mCamera);
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_view);
            camera_view.removeAllViews();
            camera_view.addView(mCameraView);
        }
    }

    @Override
    public void onStartLookingAtTarget(Punto targetObject) {
        arrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStopLookingAtTarget(Punto targetObject) {
        arrow.setVisibility(View.VISIBLE);
    }

    /*actualiza rotaciones del la imagen de la brujula para encontrar el punto de interes*/
    @Override
    public void onRotationUpdate(float[] rotationVector) {
        //arrow.setRotation(rotationVector[0] * (float)(180.0 / Math.PI));
        String log = "";
        log += rotationVector[0] + ", " + rotationVector[1] + ", " + rotationVector[2];
        //Log.i("ROTATION_UPDATE", log);
        LatLng last = LocationHelper.getLastLocation();//Recibe posicion
        if (last != null) {
            double x = point.getGeoPoint().getLatitude() - last.latitude;
            double y = point.getGeoPoint().getLongitude() - last.longitude;
            double[] dir = new double[]{x, 0, y};

            /*double viewx = Math.cos(rotationVector[0]);
            double viewy = Math.sin(rotationVector[1]);
            double[] viewdir = new double[]{viewx, viewy, 0};*/
            double[] viewdir = new double[3];
            for (int i = 0; i < 3; i++) {
                viewdir[i] = rotationVector[i];
            }

            double angle = MathUtils.angle(viewdir, dir) * (180.0 / Math.PI);
            double up_angle = MathUtils.angle(J, viewdir) * (180.0 / Math.PI);

            double[] up = J;
            double[] right = MathUtils.cross(dir, up);

            double proj1 = MathUtils.scalar_proj(viewdir, right);
            //double[] proj2 = MathUtils.proj(viewdir, up);
            double base_angle;
            if (proj1 > 0) {
                base_angle = 270;
                base_angle -= up_angle + 90;
            } else {
                base_angle = 90;
                base_angle += up_angle + 90;
            }
            //Log.i("ROTATION_UPDATE", "angle is: " + up_angle);
            arrow.setRotation((float)base_angle);

            //double realangle = MathUtils.angle(K, proj1);

            //Log.i("ROTATION_UPDATE", "Angle is: " + realangle * (180.0 / Math.PI));
            Button openGP = (Button) findViewById(btnStart);
            if (angle < 30.0) {//Se reconocio el punto

                if (arrow.getVisibility() == View.VISIBLE) {
                    Toast.makeText(this, "¿Vieron el punto?", Toast.LENGTH_SHORT);
                }
                arrow.setVisibility(View.INVISIBLE);
                //showNotification("Punto Detectado", "Punto " + point.getNombre() + " detectado");
                openGP.setEnabled(true);
                openGP.setVisibility(View.VISIBLE);
                final Activity activity = this;
                openGP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(activity, AfterCameraActivity.class);
                        i.putExtra("punto", point);
                        startActivity(i);
                    }
                });
            } else {//Se dejo de visualizar el punto
                arrow.setVisibility(View.VISIBLE);
                //NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //manager.cancel(6);
                openGP.setEnabled(false);
                openGP.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Manda una notificación a la hora de entrar a un geofence.
     *
     * @param title the notification's title.
     * @param description the notification's description.
     */
    private void showNotification(String title, String description) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentTitle(title).setContentText(description);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setAutoCancel(true);
        builder.setColor(getResources().getColor(R.color.colorPrimaryDark));
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(6, builder.build());
    }
}
