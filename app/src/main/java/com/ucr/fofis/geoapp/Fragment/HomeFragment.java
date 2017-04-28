package com.ucr.fofis.geoapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucr.fofis.geoapp.MapActivity;
import com.ucr.fofis.geoapp.R;

/**
 * Created by omcor on 4/19/2017.
 */

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        FloatingActionButton btn = (FloatingActionButton) rootView.findViewById(R.id.buttonFloat);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MapActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
