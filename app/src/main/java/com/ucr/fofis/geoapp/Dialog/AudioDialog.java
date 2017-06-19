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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.AfterCameraActivity;
import com.ucr.fofis.geoapp.R;

/**
 * Dialogo que permite escuchar audios de un punto.
 * No debe llamarse si el punto no tiene audios.
 */
public class AudioDialog extends Dialog {
    MediaPlayer mediaPlayer;

    public AudioDialog(@NonNull Context context) {
        super(context);
    }
    Punto punto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_dialog);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.audioRecyclerView);
        punto = (Punto) AfterCameraActivity.getInstance().getIntent().getSerializableExtra("punto");
        recyclerView.setAdapter(new AudioAdapter(punto.getAudios()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        mediaPlayer = MediaPlayer.create(getContext(), punto.getAudios()[0]);//Obtencion de audios
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(false);//Solo se desea escuchar una vez

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }
        });
    }

    protected class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioHolder> {
        int[] audioResouceArray;
        int lastAudio = 0;

        public AudioAdapter(int[] resourceArray) {
            audioResouceArray = resourceArray;
        }

        @Override
        public AudioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_view, parent, false);
            AudioHolder holder = new AudioHolder(itemView);

            return new AudioHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AudioHolder holder, final int position) {
            holder.audioText.setText("Audio " + (position + 1));
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//Si se hace click
                    if (mediaPlayer != null) {
                        if(position != lastAudio){
                            lastAudio=position;
                            mediaPlayer.release();
                            mediaPlayer = MediaPlayer.create(getContext(), punto.getAudios()[position]);
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mediaPlayer.setLooping(false);
                            mediaPlayer.start();
                            holder.icon.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                        }else {
                            if (mediaPlayer.isPlaying()) {//Si esta sonando se detiene
                                mediaPlayer.pause();
                                holder.icon.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                            } else {//Si no se inicia
                                mediaPlayer.start();
                                holder.icon.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                            }
                            if (mediaPlayer.getCurrentPosition() == mediaPlayer.getDuration()) {
                                mediaPlayer.seekTo(0);
                            }
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return audioResouceArray.length;
        }

        public class AudioHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView audioText;
            final ImageView icon;

            public AudioHolder(View view) {
                super(view);
                mView = view;
                audioText = (TextView)view.findViewById(R.id.audioName);
                icon = (ImageView)view.findViewById(R.id.status_audio);
            }
        }
    }
}
