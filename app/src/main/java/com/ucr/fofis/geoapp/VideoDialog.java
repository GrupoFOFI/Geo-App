package com.ucr.fofis.geoapp;

        import android.app.*;
        import android.app.Dialog;
        import android.content.Context;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.VideoView;

/**
 * Created by william on 08/06/17.
 */

public class VideoDialog extends Dialog {


    public VideoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Video");
        setContentView(R.layout.video_dialog);

        final VideoView vid = (VideoView) findViewById(R.id.video_view);
        String uriPath = "andorid.resource://com.ucr.fofis.dataaccess/"+R.raw.video6_3_small;
        Uri uri = Uri.parse(uriPath);
        vid.setVideoURI(uri);
        vid.requestFocus();
        vid.start();

        Button dialogButton = (Button) findViewById(R.id.video_button);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    dismiss();
                }
            }

        );

    }

}