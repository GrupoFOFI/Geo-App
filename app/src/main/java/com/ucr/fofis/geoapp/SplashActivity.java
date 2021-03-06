package com.ucr.fofis.geoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by oscar on 22/05/17.
 * Clase para mostrar el arte al inicio de la aplicaciǿn
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setea la actividad a la que se va a avanzar una vez desplegado el Splash y que haya cargado.
        Class activity = MainActivity.class;
        Intent intent = new Intent(SplashActivity.this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}