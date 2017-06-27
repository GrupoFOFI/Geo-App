package com.ucr.fofis.geoapp.Dialog;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.ucr.fofis.businesslogic.ResourceManager;
import com.ucr.fofis.geoapp.R;

import java.util.ArrayList;

public class GlossaryActivity extends AppCompatActivity {

    private ListView listView;
    private String[] listaGlosario ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.glossary_dialog);
        getSupportActionBar().hide();

        listaGlosario = new String[10];
        for (int i=0; i < 10 ; i++){
            listaGlosario[i] = ResourceManager.getGlossary().get(i).getPalabra();
        }
        this.listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.view_glossary , listaGlosario);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                GlossaryDialog glosDialog = new GlossaryDialog(GlossaryActivity.this , position);
                glosDialog.show();

            }
        });
    }

}
