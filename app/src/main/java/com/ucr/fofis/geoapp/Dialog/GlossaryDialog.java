package com.ucr.fofis.geoapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ucr.fofis.businesslogic.ResourceManager;
import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.dataaccess.entity.Resource;
import com.ucr.fofis.geoapp.AfterCameraActivity;
import com.ucr.fofis.geoapp.R;

import static java.security.AccessController.getContext;

/**
 * Dialogo que permite ver y escuchar definiciones del glosario
 */
public class GlossaryDialog extends Dialog   {
    MediaPlayer mediaPlayer;
    int index;
    private ImageView play;

    public GlossaryDialog(@NonNull Context context, int position) {
        super(context);
        index = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.glossary_dialog);
        TextView palabra = (TextView)findViewById(R.id.palabra);
        TextView definicion = (TextView)findViewById(R.id.definicion);
        play = (ImageView)findViewById(R.id.status_audio);
        palabra.setText(ResourceManager.getGlossary().get(index).getPalabra());
        definicion.setText(ResourceManager.getGlossary().get(index).getSignificado());
        mediaPlayer = MediaPlayer.create(getContext(), ResourceManager.getGlossary().get(index).getAudioId());
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);
        WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
        wmlp.gravity = Gravity.FILL_HORIZONTAL;
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {//Si se hace click
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {//Si esta sonando se detiene
                        mediaPlayer.pause();
                        play.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    } else {//Si no se inicia
                        mediaPlayer.start();
                        play.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    }
                    if (mediaPlayer.getCurrentPosition() == mediaPlayer.getDuration()) {
                        mediaPlayer.seekTo(0);
                    }


                }
            }
        });
    }


}
