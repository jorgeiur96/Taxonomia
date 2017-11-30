package com.unamjorge.practicas.desarrollotaxonomia;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import Taxonomia.atributosTaxonomia;

public class CatTipo extends AppCompatActivity {

    ArrayList<atributosTaxonomia> atribu;

    SwipeRefreshLayout sfiMirefres;
    private RecyclerView rvTaxonomia;
    Drawable n;
    public   int z=0;
    private ImageView imageView;

    atributosTaxonomia obj =  new atributosTaxonomia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_tipo);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        atribu=new ArrayList<atributosTaxonomia>();
        rvTaxonomia=(RecyclerView)findViewById(R.id.rvTaxonomia);
        LinearLayoutManager lnm=new LinearLayoutManager(this);
        lnm.setOrientation(LinearLayoutManager.VERTICAL);
        rvTaxonomia.setLayoutManager(lnm);

        sfiMirefres=(SwipeRefreshLayout)findViewById(R.id.sfiMirefres);

        sfiMirefres.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Repfrescandocontenido();
            }
        });

        Inicializarlista();
        adaptador();
    }



    private void Repfrescandocontenido() {
        adaptador();
        sfiMirefres.setRefreshing(false);
    }

    public  void  adaptador(){

        Taxoadaptador taxoadaptador=new Taxoadaptador(atribu);
        rvTaxonomia.setAdapter(taxoadaptador);
    }
    public   void Inicializarlista(){

        obtDatos();

    }


    public void obtDatos(){

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String datos = extras.getString("val");
        AsyncHttpClient client =new AsyncHttpClient();
        String url="https://serox.000webhostapp.com/CatTipos.php";
        RequestParams parametros =new RequestParams();
        String jor="monteria";
        parametros.put("tipo","'"+datos+"'");

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    cargarListaJSON(obtDatos(new String(responseBody)));
                    adaptador();
                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }

    public  void cargarListaJSON(ArrayList<String> datos){

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        //listado.setAdapter(adapter);

    }

    public ArrayList<String> obtDatos (String response){
        ArrayList<String> listado=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String NombreComun,
                    Nombrecientifico,
                    zona,
                    Familia,
                    url;
            String  id,cararcteristicas,habitat;

            for (int i=0;i<21;i++){
                NombreComun =jsonArray.getJSONObject(i).getString("NombreComun");
                Nombrecientifico =jsonArray.getJSONObject(i).getString("NombreCientifico");
                zona=jsonArray.getJSONObject(i).getString("zona");
                url =jsonArray.getJSONObject(i).getString("Foto");
                Familia=jsonArray.getJSONObject(i).getString("Familia");
                id=jsonArray.getJSONObject(i).getString("id");
                cararcteristicas=jsonArray.getJSONObject(i).getString("CaracteristicasGenerales");
                habitat=jsonArray.getJSONObject(i).getString("Habitat");
                obj.setFamilia(Familia);
                obj.setID(id);
                obj.setCaracteristicasgenerales(cararcteristicas);
                obj.setHábitat(habitat);
                atribu.add(new atributosTaxonomia(obj.getID(),Nombrecientifico,NombreComun,obj.getFamilia(),obj.getCaracteristicasgenerales(),url,obj.getHábitat(),zona));

                //listado.add(texto);
                //Toast.makeText(MainActivity.this,texto,Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){

            e.printStackTrace();
        }
        return  listado;
    }
}
