package com.ucr.fofis.geoapp.Application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.ucr.fofis.geoapp.GifDialog;
import com.ucr.fofis.geoapp.R;

/**
 * Created by b32080 on 05/06/2017.
 */

public class AfterCameraActivity extends AppCompatActivity {

    LinearLayout gifLayout;
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

    }
}
