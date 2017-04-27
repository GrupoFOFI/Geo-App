package com.ucr.fofis.geoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ucr.fofis.geoapp.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Class currentFragmentType;
    FragmentManager fragmentManager;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setear la vista/layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences prefs = this.getSharedPreferences("ibx", Context.MODE_PRIVATE);
        if(prefs.contains("firsttime")){
        }
        else{
            prefs.edit().putString("firsttime", "val").apply();
            //autoplay Intro Sound
            autoplayIntro();
        }


    }


    //Menu lateral
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            currentFragmentType = HomeFragment.class;
            super.onBackPressed();
            setTitle("Isla Bolanhos Experience PAs");
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

    //navegacion del menu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.inicio) {
            if (!(currentFragmentType == HomeFragment.class)) {
                setFragment(homeFragment);
                currentFragmentType = homeFragment.getClass();
                setTitle("IBX");
            }
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //setear el fragment seleccionado
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

    private void autoplayIntro() {
        MediaPlayer introMediaPlayer = new MediaPlayer();
        introMediaPlayer = MediaPlayer.create(this, com.ucr.fofis.dataaccess.R.raw.intro);
        introMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        introMediaPlayer.setLooping(false);
        introMediaPlayer.start();
    }
}
