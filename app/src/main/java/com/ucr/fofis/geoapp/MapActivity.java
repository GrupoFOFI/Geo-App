package com.ucr.fofis.geoapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;
import com.ucr.fofis.businesslogic.GeofenceManager;
import com.ucr.fofis.businesslogic.Geofences.Service.GeofenceService;
import com.ucr.fofis.businesslogic.LocationHelper;
import com.ucr.fofis.businesslogic.TourManager;
import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.Application.GeoApp;

import org.osmdroid.events.DelayedMapListener;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.modules.ArchiveFileFactory;
import org.osmdroid.tileprovider.modules.IArchiveFile;
import org.osmdroid.tileprovider.modules.OfflineTileProvider;
import org.osmdroid.tileprovider.tilesource.FileBasedTileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class MapActivity extends AppCompatActivity  implements View.OnClickListener  {


    LocationListener milocListener = new MiLocationListener();
    public MapView mMapView;
    final BoundingBox bBox14 = new BoundingBox(11.0680, -85.7100, 10.9222, -85.7420);
    final BoundingBox bBox15 = new BoundingBox(11.0658, -85.6700, 10.9222, -85.7650);
    final BoundingBox bBox16 = new BoundingBox(11.0658, -85.6780, 10.9322, -85.7820);
    public static final GeoPoint routeCenter = new GeoPoint(10.9891, -85.7025);
    public GeoPoint myLocation = new GeoPoint(0.0, 0.0);
    ArrayList<Marker> marcadores;
    Marker myPosition;
    public int markerTouched=-1;
    public MapController mapViewController;
    private LinearLayout layoutInfo;
    public Drawable markerColor;
    public Drawable iconMarker;
    private TextView txtInfo;
    public Marker nmbIfoWndw;
    GeofenceReceiver geofenceReceiver = new GeofenceReceiver();
    private Punto point;
    public boolean camaraHabilitada;

    private LocationHelper locationHelper;

    //Variable usada para revisar la selección de elementos no reconocibles en expresso
    public String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().hide();
        if(getIntent().getBooleanExtra("showRecomendation", false)){
            if(GeoApp.recommendationPlay == true) {
                showRecommentdationDialog();
            }
        }
        GeoApp.recommendationPlay = false;
        //--
       // getSupportActionBar().hide();
        mMapView = (MapView) findViewById(R.id.mMapView);
        mMapView.setUseDataConnection(true);
        mapViewController = (MapController) mMapView.getController();
        markerColor =  this.getResources().getDrawable(R.mipmap.ic_mtouched);
        iconMarker = this.getResources().getDrawable(R.mipmap.ic_marker2);
        loadOsmdroidTiles();
        mMapView.setMapListener(new DelayedMapListener(new miZoomListener()));
        setZoom(mapViewController);
        addOverlays(mMapView);
        initMyPoistion(mMapView);
        passPOItoMarker(mMapView);
        FloatingActionButton FabGPS;

        locationHelper = new LocationHelper();




        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                // TODO: Do something with yout menu items, or return false if you don't want to show them
                return true;
            }
        });

        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                //TODO: Start some activity
                if(menuItem.getTitle().equals("Cámara")){
                    if (point != null) {
                        Intent i = new Intent(getApplicationContext(), CameraActivity.class);
                        i.putExtra(CameraActivity.POINT_TAG, point);
                        startActivity(i);
                        selected =  "Cámara";
                    }
                }else if(menuItem.getTitle().equals("Ubicación")){
                    mapViewController.setZoom(16);
                    mMapView.setScrollableAreaLimitDouble(bBox16);
                    mapViewController.animateTo(myLocation);
                    selected = "Ubicación";
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        GeofenceManager.getInstance().init(this);
        registerReceiver(geofenceReceiver, new IntentFilter(GeofenceService.GEOFENCE_NOTIFICATION_FILTER));
    }

    @Override
    protected void onStop() {
        super.onStop();
        GeofenceManager.getInstance().stop();
        unregisterReceiver(geofenceReceiver);
    }

    @Override
    public void onClick(View v){

    }

    /*obtener posicion del usuario atraves del gps y cargar un marcador en el mapa, de esa posición*/
    private void initMyPoistion(MapView m){
        myPosition= new Marker(m);
        LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener milocListener = new MiLocationListener();
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }
        milocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, milocListener);
        milocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, milocListener);
        Location l = milocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        myPosition.setPosition( myLocation );
        myPosition.setIcon(this.getResources().getDrawable(R.mipmap.ic_myposition));
        myPosition.closeInfoWindow();
        myPosition.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
        if (l == null) { //no pone el marcador hasta que no encuentre una posicion
            return;
        }
        LocationHelper.updateLastLocation(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
        String coordenadas = "Mis coordenadas son: " + "Latitud = " + l.getLatitude() + "Longitud = " + l.getLongitude();
        //Toast.makeText( getApplicationContext(),coordenadas,Toast.LENGTH_LONG).show();
        GeoPoint p= new GeoPoint(l);
        // punto cerca Isla Bolaños 10.9523,-85.713
        //punto en la ECCI 9.937939, -84.051966
//        double latOffset = 1.014361;
//        double lngOffset = -1.661034;
        //punto en la casa de apú 9.991756, -84.112890
//        double latOffset = 10.9523 - 9.991756;
//        double lngOffset = -85.713 + 84.112890;
        myLocation.setCoords(p.getLatitude(),p.getLongitude());
        //GeoPoint p= new GeoPoint(routeCenter);
        drawMarker(myPosition);

    }


    private void createListMarker(){
        marcadores = new ArrayList<Marker>();
    }


    private Marker addMarker(MapView m, String name, double lat,double lon, int pto  ){
        marcadores.add(new Marker(m));
        GeoPoint gp = new GeoPoint(lat, lon);
        marcadores.get(pto).setPosition(gp);
        InfoWindow infoWindow = new MyInfoWindow(R.layout.info_window, mMapView,name);
        marcadores.get(pto).setInfoWindow(infoWindow);
        marcadores.get(pto).setTitle(name);
        marcadores.get(pto).setIcon(iconMarker);
        marcadores.get(pto).setAnchor(Marker.ANCHOR_CENTER, 1.0f);
        Marker.OnMarkerClickListener mrkeListnr = new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                    if (marker.isInfoWindowOpen()) {
                        marker.closeInfoWindow();
                        nmbIfoWndw = null;
                        marker.setIcon(iconMarker);
                        selected = marker.getTitle();
                        return false;
                    }
                    if (nmbIfoWndw != null) {
                        nmbIfoWndw.closeInfoWindow();
                        nmbIfoWndw.setIcon(iconMarker);
                    }
                    nmbIfoWndw = marker;
                    marker.setIcon(markerColor);
                    marker.showInfoWindow();
                    return true;
            }
        };
        marcadores.get(pto).setOnMarkerClickListener(mrkeListnr);
        return marcadores.get(pto);
    }

    private void drawMarker(Marker marcador){
        mMapView.getOverlays().add(marcador);
        mMapView.invalidate();
    }

    private void passPOItoMarker(MapView m){
        createListMarker();
       for(int i =0; i < TourManager.getPoints().size();i++){
            drawMarker(addMarker(m,TourManager.getPoints().get(i).getNombre(),TourManager.getPoints().get(i).getGeoPoint().getLatitude(),TourManager.getPoints().get(i).getGeoPoint().getLongitude() ,i));
            String s = " esto : "+(TourManager.getPoints().get(i));
        }
    }

    /* recibe el archivo tiles de assets(in) y el archivo generado tiles en la memoria de android(out)
    * mueve lo que contiene in hacia out
    */
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    /*Opciones de zoom, touch y limites de area para la vista mapa */
    private void setZoom(MapController mapViewController) {
        mMapView.setClickable(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setClickable(false);
        mapViewController.setZoom(14);
        mapViewController.animateTo(routeCenter);
        mMapView.setMinZoomLevel(14);
        mMapView.setMaxZoomLevel(16);
        mMapView.setScrollableAreaLimitDouble(bBox14);//delimitar el mapa
    }

    //Check if App Start First Time, return 0 fistTime , 1 not firtTime, 2 newVersion
    private int appGetFirstTimeRun() {
        SharedPreferences appPreferences = getSharedPreferences("MyAPP", 0);
        int appCurrentBuildVersion = BuildConfig.VERSION_CODE;
        int appLastBuildVersion = appPreferences.getInt("app_first_time", 0);

        if (appLastBuildVersion == appCurrentBuildVersion ) {
            return 1;

        } else {
            appPreferences.edit().putInt("app_first_time",
                    appCurrentBuildVersion).apply();
            if (appLastBuildVersion == 0) {
                return 0;
            } else {
                return 2;
            }
        }
    }

    /* Accede a la carpeta tiles de assets,
     * Crea la carpeta mapGeoIslaB en la memoria interna  y crea un archivo del mismo tipo de tiles (si es que no existen)
     * llama a la funcion copyFile
     */
    private void loadOsmdroidTiles() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = this.getAssets().open("tiles.zip");
            File fofi = new File(Environment.getExternalStorageDirectory(), "/mapGeoIslaB/");
            if (!fofi.exists() || (appGetFirstTimeRun()==0) ) {//primera vez del app o no existe el directorio
                fofi.mkdirs();
                File outFile = new File(Environment.getExternalStorageDirectory(), "/mapGeoIslaB/tiles.zip");
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            }else{
                File outFile = new File(Environment.getExternalStorageDirectory(), "/mapGeoIslaB/tiles.zip");
                if(!outFile.exists()) { //faltan archivos de subdirectorios
                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                }
            }
        } catch (IOException e) {
            return;
        }
        //cierra los archivos
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                return;
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                return;
            }
        }
    }

    /* carga el mapa desde la memoria interna
    */
    private void addOverlays(MapView mMapView) {
        File f = new File(Environment.getExternalStorageDirectory() + "/mapGeoIslaB/");
        if (f.exists()) {

            File[] list = f.listFiles();// el tiles es un conjunto de directorios
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    if (list[i].isDirectory()) {
                        continue;
                    }
                    String name = list[i].getName().toLowerCase();
                    if (!name.contains(".")) {
                        continue; //ignora archivos sin extension
                    }
                    name = name.substring(name.lastIndexOf(".") + 1);
                    if (name.length() == 0) {
                        continue;
                    }
                    if (ArchiveFileFactory.isFileExtensionRegistered(name)) {//verifica estension
                        try {

                            //ok found a file we support and have a driver for the format, for this demo, we'll just use the first one

                            //crea el offline tile provider, it will only do offline file archives
                            //again using the first file
                            OfflineTileProvider tileProvider = new OfflineTileProvider(new SimpleRegisterReceiver(this),
                                    new File[]{list[i]});

                            //tell osmdroid to use that provider instead of the default rig which is (asserts, cache, files/archives, online
                            mMapView.setTileProvider(tileProvider);

                            //this bit enables us to find out what tiles sources are available. note, that this action may take some time to run
                            //and should be ran asynchronously. we've put it inline for simplicity

                            String source = "";
                            IArchiveFile[] archives = tileProvider.getArchives();
                            if (archives.length > 0) {
                                //cheating a bit here, get the first archive file and ask for the tile sources names it contains
                                Set<String> tileSources = archives[0].getTileSources();
                                //presumably, this would be a great place to tell your users which tiles sources are available
                                if (!tileSources.isEmpty()) {
                                    //ok good, we found at least one tile source, create a basic file based tile source using that name
                                    //and set it. If we don't set it, osmdroid will attempt to use the default source, which is "MAPNIK",
                                    //which probably won't match your offline tile source, unless it's MAPNIK
                                    source = tileSources.iterator().next();
                                    mMapView.setTileSource(FileBasedTileSource.getSource(source));
                                } else {
                                    mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                                }

                            } else {
                                mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                            }
                            mMapView.invalidate();//actualiza el mapView
                            return;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            Toast.makeText(this, f.getAbsolutePath() + " did not have any files I can open! Try using TILE", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, f.getAbsolutePath() + " dir not found!", Toast.LENGTH_SHORT).show();
        }
    }

    public void showRecommentdationDialog() {
        RecommendationDialog rd = new RecommendationDialog();
        rd.show(getSupportFragmentManager(), "\r\n  \r\n \r\n");
    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------*/
    /*clase que escucha la interacción con el zoom del mapa*/
    public class miZoomListener implements MapListener {
        @Override
        public boolean onScroll(final ScrollEvent event) {

            return true;
        }

        @Override
        public boolean onZoom(final ZoomEvent event) {//si hay un zoom
            switch(mMapView.getZoomLevel()){//acomodar los limites de area del mapa dependiendo del nivel de zoom
                case 15:
                    mMapView.setScrollableAreaLimitDouble(bBox15);
                    //Toast.makeText( getApplicationContext(),"Zoom 15",Toast.LENGTH_LONG).show();
                    break;
                case 16:
                    mMapView.setScrollableAreaLimitDouble(bBox16);
                    //Toast.makeText( getApplicationContext(),"Zoom 16",Toast.LENGTH_LONG).show();
                    break;
                default:
                    mMapView.setScrollableAreaLimitDouble(bBox14);
                    //Toast.makeText( getApplicationContext(),"Zoom 14",Toast.LENGTH_LONG).show();
                    break;
            }
            return true;
        }

    }
    /*-----------------------------------------------------------------------------------------------------------------------------------------*/
    public class MiLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc)
        {
            loc.getLatitude();
            loc.getLongitude();
           String coordenadas = "Mis coordenadas son: " + "Latitud = " + loc.getLatitude() + "Longitud = " + loc.getLongitude();
           // Toast.makeText( getApplicationContext(),coordenadas,Toast.LENGTH_LONG).show();
            Log.d("location",coordenadas);
           // if(11.0680 >= loc.getLatitude() && -85.7100 >= loc.getLongitude() && 10.9222 <= loc.getLatitude() && -85.7420 <= loc.getLongitude()) {
              //  GeoPoint p = new GeoPoint(loc.getLatitude(), loc.getLongitude());
                myLocation.setCoords(loc.getLatitude(), loc.getLongitude());
                myPosition.setPosition(myLocation);
               // myPosition.setPosition(p);
                drawMarker(myPosition);
                Log.d("location","locationUpdated");
            //}
            LatLng position = new LatLng(loc.getLatitude(),loc.getLongitude());
            LocationHelper.updateLastLocation(position);

        }

        public void onProviderDisabled(String provider)
        {
            //String s = "esto :"+TourManager.getPoints().get(1);
            //Toast.makeText( getApplicationContext(),s,Toast.LENGTH_LONG).show();
          //  Toast.makeText( getApplicationContext(),"Gps Desactivado",Toast.LENGTH_SHORT ).show();
        }
        public void onProviderEnabled(String provider)
        {
            Toast.makeText( getApplicationContext(),"Gps Activo",Toast.LENGTH_SHORT ).show();
        }
        public void onStatusChanged(String provider, int status, Bundle extras){}
    }
    /*-----------------------------------------------------------------------------------------------------------------------------------------*/
    private class MyInfoWindow extends InfoWindow{
        String nameMarker;
        public MyInfoWindow(int layoutResId, MapView mapView,String namMrker) {
            super(layoutResId, mapView);
            nameMarker = namMrker;
        }
        public void onClose() {
            MyInfoWindow.closeAllInfoWindowsOn(mMapView);
        }

        public void onOpen(Object arg0) {
            TextView txtDescription = (TextView) mView.findViewById(R.id.txtIfoWndw);
            txtDescription.setText(nameMarker);

        }


    }

    private class GeofenceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int id = intent.getIntExtra(GeofenceService.GEOFENCE_ID, -1); // point id
            int trigger = intent.getIntExtra(GeofenceService.GEOFENCE_TRIGGER, 0);
            if (trigger == Geofence.GEOFENCE_TRANSITION_ENTER) { // entered region

                Log.i("Enter","Entrada2");
                point = TourManager.getPoints().get(id);
                showNotification("Atención","Se esta acercando al punto " + TourManager.getPoints().get(id).getNombre(), point);
                camaraHabilitada = true;
            }
            else if (trigger == 2) { // left region
                Log.i("Exit","Salida");
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.cancel(5);
                camaraHabilitada = false;
            }
            Log.i("Trigger",trigger + "");
        }

        /**
         * Manda una notificación a la hora de entrar a un geofence.
         *
         * @param title the notification's title.
         * @param description the notification's description.
         */
        private void showNotification(String title, String description, Punto point) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setContentTitle(title).setContentText(description);
            Intent resultIntent = new Intent(getApplicationContext(), CameraActivity.class);
            resultIntent.putExtra(CameraActivity.POINT_TAG, point);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            getApplicationContext(),
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            builder.setSmallIcon(R.drawable.ic_launcher);
            builder.setContentIntent(resultPendingIntent);
            builder.setAutoCancel(true);
            builder.setColor(getResources().getColor(R.color.colorPrimaryDark));
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(5, builder.build());
        }
   }
}
