package com.ucr.fofis.geoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucr.fofis.dataaccess.entity.Punto;

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
        punto = (Punto) getOwnerActivity().getIntent().getSerializableExtra("punto");
        recyclerView.setAdapter(new AudioAdapter(punto.getAudios()));

        mediaPlayer = MediaPlayer.create(getContext(), punto.getAudios()[0]);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

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

        public AudioAdapter(int[] resourceArray) {
            audioResouceArray = resourceArray;
        }

        @Override
        public AudioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_view, parent, false);
            AudioHolder holder = new AudioHolder(itemView);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer != null) {
                        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                    mediaPlayer = MediaPlayer.create(getContext(), punto.getAudios()[0]);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.start();
                }
            });

            return new AudioHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AudioHolder holder, int position) {
            holder.audioText.setText("Audio " + (position + 1));
        }

        @Override
        public int getItemCount() {
            return audioResouceArray.length;
        }

        public class AudioHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView audioText;

            public AudioHolder(View view) {
                super(view);
                mView = view;
                audioText = (TextView)view.findViewById(R.id.audioName);
            }
        }
    }
}
