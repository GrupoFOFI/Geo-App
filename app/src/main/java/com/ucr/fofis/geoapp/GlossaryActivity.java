package com.ucr.fofis.geoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ucr.fofis.businesslogic.ResourceManager;
import com.ucr.fofis.geoapp.Dialog.GlossaryDialog;

/*
    Actividad que contiene las palabras del glosario para que el usuario escoja.
 */

public class GlossaryActivity extends AppCompatActivity {

    private ListView listView;
    private String[] listaGlosario ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_glossary);
        getSupportActionBar().hide();

        //Se obtienen las palabas del glosario.
        listaGlosario = new String[10];
        for (int i=0; i < 10 ; i++){
            listaGlosario[i] = ResourceManager.getGlossary().get(i).getPalabra();
        }
        this.listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.view_glossary , listaGlosario);
        listView.setAdapter(adapter);
        //Se activa el onClickListener para cada palabra del glosario.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                GlossaryDialog glosDialog = new GlossaryDialog(GlossaryActivity.this , position);
                glosDialog.show();

            }
        });
    }

}
