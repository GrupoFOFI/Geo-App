package com.ucr.fofis.geoapp;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.CirclePageIndicator;
import com.synnapps.carouselview.ImageListener;
import com.ucr.fofis.businesslogic.ResourceManager;
import com.ucr.fofis.dataaccess.database.Datos;
import com.ucr.fofis.dataaccess.database.Ruta;


public class GalleryDialog  extends DialogFragment {
    public FloatingActionButton fab;
    CarouselView carouselView;
    int index;
    int[] sampleImagesPlace;
    private GalleryDialog.DialogDismissInterface dialogDismissInterface;


    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.gallery_dialog, container);
        android.support.v4.app.FragmentManager fm = getChildFragmentManager();
        /*tomar punto del lugar que esta cerca*/
        index=0;
        sampleImagesPlace = Datos.PUNTOS.get(index).getImagenes();
        carouselView = (CarouselView) view.findViewById(R.id.carouselGalleryView);
        carouselView.setPageCount(sampleImagesPlace.length);

        carouselView.setImageListener(imageListener);
        carouselView.setSlideInterval(Integer.MAX_VALUE);


        //Funcionalidad de botón para cerrar dialogFragment
        fab = (FloatingActionButton) view.findViewById(R.id.fab_exitGallery);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(dialogDismissInterface != null) {
                    dialogDismissInterface.onDialogDismiss();
                }
                getDialog().dismiss();
            }
        });

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImagesPlace[position]);
        }
    };

    public void setDialogDismissInterface(GalleryDialog.DialogDismissInterface dialogDismissInterface){
        this.dialogDismissInterface = dialogDismissInterface;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light);
    }

    public interface DialogDismissInterface {
        void onDialogDismiss();
    }
}
