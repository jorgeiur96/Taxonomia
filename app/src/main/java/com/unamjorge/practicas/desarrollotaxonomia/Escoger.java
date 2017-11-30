package com.unamjorge.practicas.desarrollotaxonomia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Escoger extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public  void Listaaves(View v){

        startActivity(new Intent(Escoger.this,ListaElementos.class));
    }

    public  void  listaZonas(View v){

        startActivity(new Intent(Escoger.this,ZonasActivity.class));
    }

    public  void  listaZonaspr(View v){

      startActivity(new Intent(Escoger.this,TiposTaxo.class));

       // startActivity(new Intent(Escoger.this,DetallesTaxo.class));
    }


}
