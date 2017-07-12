package com.ucr.fofis.geoapp;

import android.app.Activity;
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
import com.ucr.fofis.dataaccess.entity.Resource;
import com.ucr.fofis.geoapp.Application.GeoApp;
import com.ucr.fofis.geoapp.Dialog.RecommendationDialog;

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


/**
* Activity del mapa, contiene almacena y carga el mapa offline, incluye xml de infowindow de marcadores.
* Contiene botones de ubicación gps y boton hacia el camera activity
* presenta las recomendaciones visuales
* */
public class MapActivity extends AppCompatActivity  implements View.OnClickListener  {


    LocationListener milocListener = new MiLocationListener();
    public MapView mMapView;
    final BoundingBox bBox14 = new BoundingBox(11.0680, -85.7100, 10.9222, -85.7420);
    final BoundingBox bBox15 = new BoundingBox(11.0658, -85.6700, 10.9222, -85.7650);
    final BoundingBox bBox16 = new BoundingBox(11.0658, -85.6780, 10.9322, -85.7820);
    public static final GeoPoint routeCenter = new GeoPoint(10.9891, -85.7025);//para ajustar vista del mapa al entrar
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
    private String puntoActual = "";

    private LocationHelper locationHelper;

    Activity activity = this;


    ArrayList<Punto> PUNTOS = new ArrayList<Punto>() {
        {
            add(new Punto(1, "Secuencia de estratos sedimentarios", null, new GeoPoint(10.9514833333333, -85.7151), 300.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_01_1, "Secuencia de estratos rocosos"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_01_2, "Estatos de la Formación Junquillal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_01_3, "Estatos de la Formación Junquillal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_01_4, "Estatos de la Formación Junquillal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_01_5, "Estatos de la Formación Junquillal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_01_6, "Estatos de la Formación Junquillal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_01_7, "Estatos de la Formación Junquillal")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio1, "Los Estratos")}, new Resource(com.ucr.fofis.dataaccess.R.raw.anim01_6, "Los Estratos")));
            add(new Punto(2, "Formas causadas por la erosión", null, new GeoPoint(10.9570833333333, -85.7336333333333), 500.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_1, "Rocas sedimentarias de Bajo Rojo"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_2, "Una vista a la peninsula de Santa Elena"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_3, "Estratificación en Isla David"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_4, "Isla David"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_5, "Isla David"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_6, "Isla David"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_7, "Isla David"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_8, "Isla David"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_9, "Gradación normal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_10, "Bajo Rojo"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_11, "Bajo Rojo"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_02_12, "Bajo Rojo")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio2, "La Erosión")}, new Resource(com.ucr.fofis.dataaccess.R.raw.anim02_5, "La Erosión")));
            add(new Punto(3, "El sinclinal de Bahía Junquillal", null, new GeoPoint(10.9698798844808, -85.7271389630961), 600.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_03_1, "Una vista de Bahía Junquillal")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio3, "Los Sinclinales")}, new Resource(com.ucr.fofis.dataaccess.R.raw.anim03_4, "Los Sinclinales")));
            add(new Punto(4, "Isla Muñecos y alrededores", null, new GeoPoint(10.9777046049834, -85.7178071072068), 200.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_04_1, "Arrecifes coralinos fosilizados"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_04_3, "Arrecife de coral")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio4, "Los Arrecifes y las Calizas")}, new Resource(0, "")));
            add(new Punto(5, "Isla Muñecos y sus corales", null, new GeoPoint(10.98176182858, -85.7177965097929), 50.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_05_1, "Fragmentos de corales fosilizados"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_05_2, "Fragmentos de corales fosilizados")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio5, "Isla Muñecos")}, new Resource(0, "")));
            add(new Punto(6, "Isla Muñecos", null, new GeoPoint(10.9821743821365, -85.7190790800442), 50.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_06_1, "Estructuras verticales por erosión"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_06_2, "El Muñeco")}, new Resource(com.ucr.fofis.dataaccess.R.raw.video06_3, "Una vista de Bahía Junquillal"), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio6, "Los Muñecos")}, new Resource(0, "")));
            add(new Punto(7, "Isla Muñecos", null, new GeoPoint(10.9839, -85.7189), 50.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_1, "Plegamiento de estratos de roca"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_2, "Isla Muñecos"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_3, "Isla Muñecos"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_4, "Isla Muñecos"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_5, "Isla Muñecos"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_6, "Isla Muñecos"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_7, "Fragmento de coral"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_8, "Fragmento de coral"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_9, "El Muñeco"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_10, "El Muñeco"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_11, "El Muñeco"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_12, "El Muñeco"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_13, "El Muñeco"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_14, "Disolución de carbonato de calcio"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_07_15, "Arrecife de coral fosilizado"),}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio7, "¿Rocas dobladas?")}, new Resource(0, "")));
            add(new Punto(8, "Isla Despensa (Isla Loro) y la estratificación cruzada", null, new GeoPoint(11.0016833333333, -85.748105), 200.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_1, "Estratificación cruzada"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_2, "Una vista de Bahía Junquillal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_5, "Isla Despensa"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_6, "Isla Despensa"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_7, "Isla Despensa"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_8, "Isla Despensa"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_9, "Isla Despensa"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_10, "Isla Despensa"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_11, "Isla Despensa"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_12, "Ichnofósiles"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_13, "Gradación normal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_14, "Formas generadas por erosión"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_15, "Formas generadas por erosión"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_16, "Formas generadas por erosión"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_17, "Estratificación cruzada"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_18, "Conglomerado"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_08_19, "Conglomerado"),}, new Resource(com.ucr.fofis.dataaccess.R.raw.video08_3, "Relieve producto de la erosión cerca de la Isla Despensa (Isla Loro)"), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio8, "Estratificación Cruzada")}, new Resource(com.ucr.fofis.dataaccess.R.raw.anim08_2, "Estratificación Cruzada")));
            add(new Punto(9, "La cordillera volcánica de Guanacaste", null, new GeoPoint(11.0361333333333, -85.7485833333333), 500.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_09_1, "Vista del complejo Orosí - Cacao")}, new Resource(com.ucr.fofis.dataaccess.R.raw.video09_6, "Relieve producto de la erosión"), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio9, "La Subducción y otros procesos")}, new Resource(com.ucr.fofis.dataaccess.R.raw.anim09_5, "La Subducción y otros procesos")));
            add(new Punto(10, "Bahía Jobo y el secreto del dragón", null, new GeoPoint(11.0381333333333, -85.7387166666666), 100.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_10_1, "Pliegue sinsedimentario"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_10_1a, "El dragón de roca")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio10, "Pliegues sinsedimentarios")}, new Resource(0, "")));
            add(new Punto(11, "El Gallito y la erasión", null, new GeoPoint(11.0448166666666, -85.7417), 200.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_1, "Rocas rojas por efectos de la meteorización"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_2, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_3, "Rocas ordenadas en capas"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_4, "Relictos de erosión"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_5, "Relictos de erosión"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_6, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_7, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_8, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_9, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_10, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_11, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_12, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_13, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_14, "El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_15, "De cerca en El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_16, "De cerca en El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_17, "De cerca en El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_18, "De cerca en El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_19, "De cerca en El gallito"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_11_20, "Concreción"),}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio11, "La Erosión")}, new Resource(0, "")));
            add(new Punto(12, "¿Dunas en Costa Rica?", null, new GeoPoint(11.0462333333333, -85.7380666666666), 200.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_12_1, "Duna costera")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio12, "Las Dunas Costeras")}, new Resource(com.ucr.fofis.dataaccess.R.raw.anim12_3, "Las Dunas Costeras")));
            add(new Punto(13, "Los ricones de Bahía Salinas", null, new GeoPoint(11.0488333333333, -85.7266666666666), 100.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_13_1, "Conglomerado")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio13, "Los Conglomerados")}, new Resource(0, "")));
            add(new Punto(14, "Upwelling: Riquezas submarinas de la costa Pacífica de Costa Rica", null, new GeoPoint(11.0457666666666, -85.7219), 200.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_14_1, "Duna costera"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_14_2, "Rocas ordenadas en capas")}, new Resource(com.ucr.fofis.dataaccess.R.raw.video14_4, "Duna activa"), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio14, "El upwelling")}, new Resource(0, "")));
            add(new Punto(15, "La isla Bolaños: el desembarco", null, new GeoPoint(11.0466881500087, -85.7075191718204), 100.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_15_1, "Arenas y clastos dispersos por acción del oleaje"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_15_2, "Una vista de Isla Bolaños"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_16_2, "Mapa geológico de Isla  Bolaños"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_17_1, "Secuencia de estratos rocosos"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_18_1, "Pares conjugados"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_18_2, "Isla Bolaños"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_18_3, "Isla Bolaños"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_18_4, "Otra vista de Isla Bolaños"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_18_5, "Depósito que puede llegar a formar un conglomerado")}, new Resource(com.ucr.fofis.dataaccess.R.raw.video15_2, "Isla Bolaños"), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio15, "Efectos del oleaje"), new Resource(com.ucr.fofis.dataaccess.R.raw.audio16, "Las Rocas de Isla Bolaños"), new Resource(com.ucr.fofis.dataaccess.R.raw.audio17, "La Formación Junquillal"), new Resource(com.ucr.fofis.dataaccess.R.raw.audio18, "Los Pares Conjugados")}, new Resource(0, "")));
            add(new Punto(19, "Adentrándose en Isla Bolaños", null, new GeoPoint(11.0491333333333, -85.7074666666666), 50.0d, new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_1, "Estratificación cruzada curva"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_1_a, "Laminaciones finas"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_1_b, "Ondulitas"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_1_c, "Concreciones"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_1_d, "Concreciones"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_1_e, "¡Carbón en rocas?"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_1_f, "Ichnofósiles"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_1_g, "Ichnofosiles"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_2, "Ondulitas"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_3, "Laminación paralela"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_4, "Ondulitas"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_5, "Estratos de la formación Junquillal"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_6, "Estratificación paralela fina"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_7, "Concreción"), new Resource(com.ucr.fofis.dataaccess.R.drawable.punto_19_2, "Concreción")}, new Resource(0, ""), new Resource[]{new Resource(com.ucr.fofis.dataaccess.R.raw.audio19_1, "Ondulitas"), new Resource(com.ucr.fofis.dataaccess.R.raw.audio19_1cyd, "Concreciones"), new Resource(com.ucr.fofis.dataaccess.R.raw.audio19_1e, "¿Carbón en rocas?"), new Resource(com.ucr.fofis.dataaccess.R.raw.audio19_1fyg, "Ichnofósiles")}, new Resource(0, "")));

        }
    };


    //Variable usada para revisar la selección de elementos no reconocibles en expresso
    public String selected;
    /**
    *llama a todos los metodos de carga de mapa, asinación de marcadores, ajustes de zoom, listener de gps , botones y zoom
    */
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
        FabSpeedDial fabSpeedDial2 = (FabSpeedDial) findViewById(R.id.fab_speed_dial2);
        fabSpeedDial.setEnabled(false);
        fabSpeedDial.setVisibility(View.GONE);

        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }
        });
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                //Aqui se selecciona que accion tomar dependiendo de cual subboton se escogio
                if(menuItem.getTitle().equals("Cámara")){//Ir a camara
                    if (camaraHabilitada) {
                        Intent i = new Intent(getApplicationContext(), CameraActivity.class);
                        i.putExtra(CameraActivity.POINT_TAG, point);
                        startActivity(i);
                        selected =  "Cámara";
                    }
                }else if(menuItem.getTitle().equals("Ubicación")){//Ir a ubicacion
                    mapViewController.setZoom(16);
                    mMapView.setScrollableAreaLimitDouble(bBox16);
                    mapViewController.animateTo(myLocation);
                    selected = "Ubicación";
                }
                return false;
            }
        });
        fabSpeedDial2.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }
        });
        fabSpeedDial2.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                //Aqui se selecciona que accion tomar dependiendo de cual subboton se escogio
                if(menuItem.getTitle().equals("Ubicación")){
                    mapViewController.setZoom(16);
                    mMapView.setScrollableAreaLimitDouble(bBox16);
                    mapViewController.animateTo(myLocation);
                }
                return false;
            }
        });

    }
    /**
    * incio de geofence
     * */
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

    /**
     * obtener posicion del usuario en la clase MiLocationListener y cargar un marcador en el mapa.
     * animación del marcador en conjunto con la ubicación
     * */
    private void initMyPoistion(MapView m){
        myPosition= new Marker(m);
        LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener milocListener = new MiLocationListener();//actualiza posición (cada que cambie la ubicación) del marcador
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

    /**
    * asigna a los marcadores un xml infowindow , un nombre , una posición , un clicklistener y un icono.(según corresponda con la lista de puntos)
    * muestra el titulo del marcador cuando es seleccionado(o lo oculta) y cambia de color
    * */
    private Marker addMarker(MapView m, String name, double lat,double lon, final int pto  ){
        marcadores.add(new Marker(m));
        GeoPoint gp = new GeoPoint(lat, lon);
        marcadores.get(pto).setPosition(gp);
        InfoWindow infoWindow = new MyInfoWindow(R.layout.info_window, mMapView,name);
        marcadores.get(pto).setInfoWindow(infoWindow);
        marcadores.get(pto).setTitle(name);
        marcadores.get(pto).setIcon(iconMarker);
        SharedPreferences prefs = activity.getSharedPreferences("ibx", Context.MODE_PRIVATE);
        if(prefs.contains(marcadores.get(pto).getTitle())) {
            marcadores.get(pto).setIcon(markerColor);

        }
        marcadores.get(pto).setAnchor(Marker.ANCHOR_CENTER, 1.0f);
        Marker.OnMarkerClickListener mrkeListnr = new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                    if (marker.isInfoWindowOpen()) {
                        SharedPreferences prefs = activity.getSharedPreferences("ibx", Context.MODE_PRIVATE);
                        if(prefs.contains(marker.getTitle())){
                            marker.setIcon(markerColor);
                            for(int i = 0; i<PUNTOS.size(); i++){
                                if(PUNTOS.get(i).getNombre().equals(marker.getTitle())){
                                    Intent intent = new Intent(activity, AfterCameraActivity.class);
                                    intent.putExtra("punto", PUNTOS.get(i));
                                    startActivity(intent);
                                }
                            }
                        }
                        marker.closeInfoWindow();
                        nmbIfoWndw = null;
                        selected = marker.getTitle();
                        return false;
                    }
                    if (nmbIfoWndw != null) {
                        nmbIfoWndw.closeInfoWindow();
                    }
                    nmbIfoWndw = marker;
                    marker.showInfoWindow();
                    return true;
            }
        };
        marcadores.get(pto).setOnMarkerClickListener(mrkeListnr);
        return marcadores.get(pto);
    }
    /**
     * recibe de passPOItoMarker un marcador, el cual lo dibuja en el mapa
    */
    private void drawMarker(Marker marcador){
        mMapView.getOverlays().add(marcador);
        mMapView.invalidate();
    }

    /**
    * envía a addmarker el geopoint y titulo del punto
    * recibe de addmarker el marcador correspondiente y lo envía a drawMarker
     * */
    private void passPOItoMarker(MapView m){
        createListMarker();
       for(int i =0; i < TourManager.getPoints().size();i++){
            drawMarker(addMarker(m,TourManager.getPoints().get(i).getNombre(),TourManager.getPoints().get(i).getGeoPoint().getLatitude(),TourManager.getPoints().get(i).getGeoPoint().getLongitude() ,i));
            String s = " esto : "+(TourManager.getPoints().get(i));
        }
    }

    /** recibe el archivo tiles de assets(in) y el archivo generado tiles en la memoria de android(out)
    * mueve lo que contiene in hacia out
    */
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    /**
     * set a las opciones de zoom, touch y limites de area para el mapa
     * */
    private void setZoom(MapController mapViewController) {
        mMapView.setClickable(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setClickable(false);
        mapViewController.setZoom(14);
        mapViewController.animateTo(routeCenter);
        mMapView.setMinZoomLevel(14);
        mMapView.setMaxZoomLevel(16);
        mMapView.setScrollableAreaLimitDouble(bBox14);//delimitar el mapa con la pantalla
    }

    /**
     *     chequea si el app inicia por primera vez, retorna 0 primera vez , 1 no es primera vez, 2 nueva version
     */
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

    /** Accede a la carpeta tiles de assets,
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

    /**
     *  carga el mapa desde la memoria interna
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


                            //crea el offline tile provider, it will only do offline file archives
                            //again using the first file
                            OfflineTileProvider tileProvider = new OfflineTileProvider(new SimpleRegisterReceiver(this),
                                    new File[]{list[i]});

                            //tell osmdroid to use that provider instead of the default rig which is (asserts, cache, files/archives, online
                            mMapView.setTileProvider(tileProvider);


                            String source = "";
                            IArchiveFile[] archives = tileProvider.getArchives();
                            if (archives.length > 0) {

                                Set<String> tileSources = archives[0].getTileSources();

                                if (!tileSources.isEmpty()) {
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
    /**
     * Clase que escucha la interacción con el zoom del mapa
     * */
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
    /**
     * Clase que escucha si el gps esta activo o no.
     * si la ubiación cambia, es ese caso vuelve a dibujar el marcador en el mapa
     */
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
    /**
     * Clase de info window para marcadores, escucha cuando la ventana es abierta y asigna el titulo correspondiente
     * */
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
    /*-----------------------------------------------------------------------------------------------------------------------------------------*/
    /**
     * Clase de GeoFence, deonde se encuentra la lógica de entrar y salir en la región de un punto
     */
    private class GeofenceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int id = intent.getIntExtra(GeofenceService.GEOFENCE_ID, -1); // point id
            int trigger = intent.getIntExtra(GeofenceService.GEOFENCE_TRIGGER, 0);
            FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
            FabSpeedDial fabSpeedDial2 = (FabSpeedDial) findViewById(R.id.fab_speed_dial2);
            if (trigger == Geofence.GEOFENCE_TRANSITION_ENTER) { // entered region

                Log.i("Enter","Entrada2");
                point = TourManager.getPoints().get(id);
                Log.d("Punto Actual",puntoActual + " " + TourManager.getPoints().get(id).getNombre());
                if(!puntoActual.equals(TourManager.getPoints().get(id).getNombre()))
                    showNotification("Atención","Se esta acercando al punto " + TourManager.getPoints().get(id).getNombre(), point);
                camaraHabilitada = true;
                fabSpeedDial.setEnabled(true);
                fabSpeedDial.setVisibility(View.VISIBLE);
                fabSpeedDial2.setEnabled(false);
                fabSpeedDial2.setVisibility(View.GONE);
                puntoActual = TourManager.getPoints().get(id).getNombre();
                //Se modifica el color del marcador para mostrar que ha sido visitado.
                marcadores.get(id).setIcon(markerColor);
            }
            else if (trigger == 2) { // left region
                Log.i("Exit","Salida");
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.cancel(5);
                camaraHabilitada = false;
                fabSpeedDial2.setEnabled(true);
                fabSpeedDial2.setVisibility(View.VISIBLE);
                fabSpeedDial.setEnabled(false);
                fabSpeedDial.setVisibility(View.GONE);
                puntoActual = "";
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
            builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(5, builder.build());
        }
    }

}
