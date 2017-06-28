package com.ucr.fofis.geoapp.Dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ucr.fofis.businesslogic.ListAdapterManager;
import com.ucr.fofis.businesslogic.ResourceManager;
import com.ucr.fofis.dataaccess.entity.Palabra;
import com.ucr.fofis.geoapp.MainActivity;
import com.ucr.fofis.geoapp.R;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlContext;
import java.util.ArrayList;

import pl.droidsonroids.gif.InputSource;

import static com.ucr.fofis.dataaccess.database.Datos.GLOSARIO;
import static java.security.AccessController.getContext;


public class GlossaryActivity extends Activity {

    private ListView listView;
    MediaPlayer mediaPlayer = null;
    Context ctx;
    int indexaudio;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_glossary);
        this.listView = (ListView) findViewById(R.id.listView);
        /*audio*/
        ctx = getApplicationContext();
        // mediaPlayer = MediaPlayer.create(ctx, GLOSARIO.get(0).getAudioId());
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer.setLooping(false);
        listView.setAdapter(new ListAdapterManager(this, R.layout.view_glossary, GLOSARIO) {
            @Override
            public void onEntrada(final Object entrada, final View view) {
                if (entrada != null) {
                    v = view;
                    TextView texto_palabra = (TextView) view.findViewById(R.id.palabra);
                    texto_palabra.setText(((Palabra) entrada).getPalabra());
                    TextView texto_definicion = (TextView) view.findViewById(R.id.definicion);
                    texto_definicion.setText(((Palabra) entrada).getSignificado());
                    final ImageView audio = (ImageView) view.findViewById(R.id.imageView_audio);
                    mediaPlayer = new MediaPlayer();
                    audio.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if ((mediaPlayer.isPlaying()) && (indexaudio == ((Palabra) entrada).getAudioId())) {//Si esta sonando se detiene
                                mediaPlayer.pause();
                                audio.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);

                            } else {
                                if (mediaPlayer.isPlaying()) { //pausar el audio anterior
                                    ImageView temp = (ImageView) v.findViewById(R.id.imageView_audio);
                                    temp.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                                    mediaPlayer.pause();
                                }


                                //tomar audio
                                MediaPlayer mp = MediaPlayer.create(ctx, ((Palabra) entrada).getAudioId());
                                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mp.setLooping(false);
                                mediaPlayer = mp;
                                //guardar valores de audio
                                indexaudio = ((Palabra) entrada).getAudioId();
                                v = view;
                                //iniciar
                                audio.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                                mediaPlayer.start();


                                if (mediaPlayer.getCurrentPosition() == mediaPlayer.getDuration()) {
                                    mediaPlayer.seekTo(0);
                                }
                            }
                        }
                    });

                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });
    }


}