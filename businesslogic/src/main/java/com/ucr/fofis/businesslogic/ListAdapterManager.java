package com.ucr.fofis.businesslogic;
/**
 * Created by Tony on 27/6/2017.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/** Adaptador de ListView del glosario
 */
public abstract class ListAdapterManager extends BaseAdapter {

    private ArrayList<?> listglosario;
    private int id_Layout;
    private Context context;

    public ListAdapterManager(Context context, int id_Layout, ArrayList<?> listglosario) {
        super();
        this.context = context;
        this.listglosario = listglosario;
        this.id_Layout = id_Layout;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(id_Layout, null);
        }
        onEntrada (listglosario.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return listglosario.size();
    }

    @Override
    public Object getItem(int posicion) {
        return listglosario.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    /*
     * @param entrada La entrada que es asociada  al view.
     * @param view contiene datos del paquete/handler
     */
    public abstract void onEntrada (Object entrada, View view);

}