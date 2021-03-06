package com.ucr.fofis.geoapp.Dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.ucr.fofis.dataaccess.entity.Punto;
import com.ucr.fofis.geoapp.AfterCameraActivity;
import com.ucr.fofis.geoapp.R;

/**
 * Dialogo que permite mostrar imagenes de un punto.
 */

public class GalleryDialog extends DialogFragment {
    public FloatingActionButton fab;
    CarouselView carouselView;
    Punto punto;
    TextView Titulo;
    private GalleryDialog.DialogDismissInterface dialogDismissInterface;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery_dialog, container);
        android.support.v4.app.FragmentManager fm = getChildFragmentManager();
        Titulo = (TextView) view.findViewById(R.id.gallery_title);
                /*tomar punto del lugar que esta cerca*/
        punto = (Punto) AfterCameraActivity.getInstance().getIntent().getSerializableExtra("punto");
        carouselView = (CarouselView) view.findViewById(R.id.carouselGalleryView);
        carouselView.setPageCount(punto.getImagenes().length);
        carouselView.setImageListener(imageListener);
        carouselView.setSlideInterval(Integer.MAX_VALUE);
        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.FILL_HORIZONTAL;
        Titulo.setText(punto.getImagenes()[0].getTitle());
        carouselView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Titulo.setText(punto.getImagenes()[position].getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ;



        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(punto.getImagenes()[position].getId());

        }
    };

    public void setDialogDismissInterface(GalleryDialog.DialogDismissInterface dialogDismissInterface) {
        this.dialogDismissInterface = dialogDismissInterface;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light);
    }

    public interface DialogDismissInterface {
        void onDialogDismiss();
    }
}

