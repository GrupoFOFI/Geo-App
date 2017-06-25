package com.ucr.fofis.geoapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ucr.fofis.dataaccess.database.Ruta;
import com.ucr.fofis.geoapp.Application.GeoApp;
import com.ucr.fofis.geoapp.Dialog.RecommendationDialog;
import com.ucr.fofis.geoapp.Fragment.HomeFragment;

/**
 *
 * Actividad principal del App, controla menú de navegación, Diálogo de recomendaciones y audio introductorio
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecommendationDialog.DialogDismissInterface {
    private static final int CODE_RE = 121;
    Class currentFragmentType;
    FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    public MediaPlayer introMediaPlayer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setear la vista/layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //para controlar la navegacion del menu y para poder cambiar de fragmento
        homeFragment = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        currentFragmentType = homeFragment.getClass();
        setFragment(homeFragment);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (GeoApp.audioPlay == true) {
            autoplayIntro();
        }
        GeoApp.audioPlay = false;
    }


    /**
     * Realiza el pedido de permisos: escritura de dsico (WRITE_EXTERNAL_STORAGE), posición precisa(ACCESS_FINE_LOCATION) y cámara (CAMERA)
     */
    private void requestPermission() {

        //Permiso de External Storage
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_RE);
        }
        //Permiso de Location
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CODE_RE);

            SharedPreferences prefs = this.getSharedPreferences("ibx", Context.MODE_PRIVATE);
        }

        //Permiso de Cámara
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CODE_RE);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CODE_RE){
            switch (resultCode) {
                case RESULT_OK:
                    // Resultado correcto
                    break;
                case RESULT_CANCELED:
                    // Cancelación o cualquier situación de error
                    break;
            }
        }
    }

    /**
     * Cierra el menú lateral si está abierto cuando se apreta atrás
     *
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            currentFragmentType = HomeFragment.class;
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * navegacion del menu, define lo que se hace para cada item del menu
     * @param item
     * @return boolean - si fue exitoso o no
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.inicio) {
            if (!(currentFragmentType == HomeFragment.class)) {
                setFragment(homeFragment);
                currentFragmentType = homeFragment.getClass();
                setTitle(Ruta.TITULO);
            }
            //Mostrar recomendaciones
        } else if (id == R.id.nav_recomendaciones) {
            this.showRecommentdationDialog();
            //Reproducir audio
        } else if (id == R.id.nav_audio) {
            autoplayIntro();
            //Link a pagina web
        } else if (id == R.id.web) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(Ruta.WEB_PAGE_URL));
            startActivity(i);

        } else if (id == R.id.nav_creditos) {
            Intent i = new Intent(this,CreditsActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     *     Coloca el fragment seleccionado en la pantalla
     * @param fragment
     */
    private void setFragment(Fragment fragment) {
        if (fragmentManager.getBackStackEntryCount() == 1 && fragment.getClass() == HomeFragment.class) {
            fragmentManager.popBackStack();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        if (fragmentManager.getBackStackEntryCount() == 0 && fragment.getClass() != HomeFragment.class) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        fragmentTransaction.commit();
    }

    /**
     * Carga el diálogo de recomendaciones cargadas desde Datos.RECOMENDACIONES
     */
    public void showRecommentdationDialog() {
        RecommendationDialog rd = new RecommendationDialog();
        rd.setDialogDismissInterface(this);
        rd.show(getSupportFragmentManager(), "\r\n  \r\n \r\n");
    }

    /**
     * Reproduce audio introductorio
     */
    private void autoplayIntro() {

        AudioManager manager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        if(!(manager.isMusicActive()))
        {
            introMediaPlayer = new MediaPlayer();
            introMediaPlayer = MediaPlayer.create(this, Ruta.AUDIO_INTRO);
            introMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            introMediaPlayer.setLooping(false);
            introMediaPlayer.start();
        }
    }

    /**
     * Solicita permiso de la camara
     */
    private void checkCameraPermission() {

        final Activity currentActivity = this;

        if (ContextCompat.checkSelfPermission(currentActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(currentActivity, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }


    @Override
    protected void onPause() {
        if (introMediaPlayer != null) {
            if (introMediaPlayer.isPlaying())
                introMediaPlayer.stop();
            introMediaPlayer.release();
            introMediaPlayer = null;
        }
        super.onPause();
    }

    @Override
    public void onDialogDismiss() {
        navigationView.getMenu().getItem(0).setChecked(true);
    }
}
