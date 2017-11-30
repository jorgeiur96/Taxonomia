package com.unamjorge.practicas.desarrollotaxonomia;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import Taxonomia.atributosTaxonomia;

public class DetallesTaxo extends AppCompatActivity {

    TextView txtid,txtNombreCientifico,txtNombreComun,txtFamilia,txtCaracteristicasGenerales,txtZona,txtHabitat;

    ImageView  IMGfotoCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_taxo);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        IMGfotoCard=(ImageView)findViewById(R.id.IMGfotoCard);
        Inicializarlista();


    }






    public   void Inicializarlista(){

        txtid =(TextView)findViewById(R.id.txtCodigo);
        txtNombreCientifico =(TextView)findViewById(R.id.txtNombrecientifico);
        txtNombreComun =(TextView)findViewById(R.id.txtNombreComun);
        txtFamilia= (TextView)findViewById(R.id.txtFamilia);
        txtCaracteristicasGenerales =(TextView)findViewById(R.id.txtCaracteristicasGenerales);
        txtZona= (TextView)findViewById(R.id.txtZona);
        txtHabitat =(TextView)findViewById(R.id.txtHabitat);

        obtDatos();

    }


    public void obtDatos(){

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String datos = extras.getString("val");


        AsyncHttpClient client =new AsyncHttpClient();
        String url="https://serox.000webhostapp.com/Detalles.php";
        RequestParams parametros =new RequestParams();
        parametros.put("codigo","'"+datos+"'");

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    cargarListaJSON(obtDatos(new String(responseBody)));


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

                txtNombreCientifico.setText(Nombrecientifico);
                txtNombreComun.setText(NombreComun);
                txtFamilia.setText(Familia);
                txtCaracteristicasGenerales.setText(cararcteristicas);
                txtHabitat.setText(habitat);
                txtZona.setText(zona);
                txtid.setText(id);

                Glide.with(this)
                        .load(url)
                        .crossFade()
                        .placeholder(R.drawable.aves)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(IMGfotoCard);

                //listado.add(texto);
                //Toast.makeText(MainActivity.this,texto,Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){

            e.printStackTrace();
        }
        return  listado;
    }
}
