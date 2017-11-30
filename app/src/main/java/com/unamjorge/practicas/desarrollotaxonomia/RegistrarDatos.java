package com.unamjorge.practicas.desarrollotaxonomia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Taxonomia.atributosTaxonomia;
import cz.msebera.android.httpclient.entity.mime.Header;

public class RegistrarDatos extends AppCompatActivity {

    private TextInputEditText txtCodigo,txtNombrecientifico,txtNombreComun,txtFamilia,txtCaracteristicasGenerales,txtHabitat,txtZona;

    private atributosTaxonomia taxo=new atributosTaxonomia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_datos);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        Inicializar();
    }

    private void Inicializar() {
        txtCodigo=(TextInputEditText) findViewById(R.id.txtCodigo);
        txtNombrecientifico=(TextInputEditText)findViewById(R.id.txtNombrecientifico);
        txtNombreComun=(TextInputEditText)findViewById(R.id.txtNombreComun);
        txtFamilia=(TextInputEditText)findViewById(R.id.txtFamilia);
        txtCaracteristicasGenerales=(TextInputEditText)findViewById(R.id.txtCaracteristicasGenerales);
        txtHabitat=(TextInputEditText)findViewById(R.id.txtHabitat);
        txtZona=(TextInputEditText)findViewById(R.id.txtZona);
    }





    public void Btnclick2(View btn){

       // Toast.makeText(RegistrarDatos.this,,Toast.LENGTH_LONG).show();

        llenar(btn);

    }
    private  void  llenar (View btn)
    {

        String codigo=(txtCodigo.getText().toString());
        String nombreCientifico=txtNombrecientifico.getText()+"";
        String nombreComun=txtNombreComun.getText()+"";
        String Familia =txtFamilia.getText()+"";
        String CarateristicaGenerales=txtCaracteristicasGenerales.getText()+"";
        String habitat=txtHabitat.getText()+"";
        String zonas=txtZona.getText()+"";

        if(codigo.equals("")){
            mensaje(btn,"requiero codigo");

        }else if(nombreCientifico.equals("")){

            mensaje(btn,"requiero Nombre Cientifico");

        }else if (nombreComun.equals("")){
            mensaje(btn,"requiero Nombre Comun");

        }else if(Familia.equals("")){
            mensaje(btn,"requiero Familia");


        }else if(CarateristicaGenerales.equals("")){
            mensaje(btn,"requiero Caracteristicas Generales");


        }else if(habitat.equals("")){

            mensaje(btn,"requiero Habitat");


        }else  if(zonas.equals("")){

            mensaje(btn,"requiero Zona");


        }else{

            taxo.setID(codigo);
            taxo.setNombrecientifico(txtNombrecientifico.getText()+"");
            taxo.setNombrecomun(txtNombreComun.getText()+"");
            taxo.setFamilia(txtFamilia.getText()+"");
            taxo.setCaracteristicasgenerales(txtCaracteristicasGenerales.getText()+"");
            taxo.setHábitat(txtHabitat.getText()+"");
            taxo.setZonas(txtZona.getText()+"");
            MandarServer();
            startActivity(new Intent(RegistrarDatos.this,Pimerlayout.class));
        }






    }



    public  void mensaje (View v ,String menajae){
        Snackbar.make(v,menajae, Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(R.color.colorblanco))
                .show();
    }


    public  void  MandarServer(){

        AsyncHttpClient client2 =new AsyncHttpClient();
        String url="https://serox.000webhostapp.com/prueba.php";


        RequestParams parametros =new RequestParams();

        Bundle Elementos =getIntent().getExtras();
        String URL=Elementos.getString("URL");
        if(Elementos != null){

            Toast.makeText(RegistrarDatos.this,"h"+URL,Toast.LENGTH_LONG).show();

        }

        parametros.put("id","'"+taxo.getID()+"'");
        parametros.put("NombreCientifico","'"+taxo.getNombrecientifico()+"'");
        parametros.put("NombreComun","'"+taxo.getNombrecomun()+"'");
        parametros.put("Familia","'"+taxo.getFamilia()+"'");
        parametros.put("CaracteristicasGenerales","'"+taxo.getCaracteristicasgenerales()+"'");
        parametros.put("Foto","'"+URL+"'");
        parametros.put("Habitat","'"+taxo.getHábitat()+"'");
        parametros.put("zona","'"+taxo.getZonas()+"'");

        client2.post(url,parametros,new JsonHttpResponseHandler(){


            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }




            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                JSONObject firstEvent = null;
                JSONObject firstEvent1 = null;
                JSONObject firstEvent2 = null;
                JSONObject firstEvent3 = null;
                JSONObject firstEvent4 = null;
                JSONObject firstEvent5 = null;
                JSONObject firstEvent6 = null;
                JSONObject firstEvent7 = null;
                JSONObject firstEvent8 = null;
                JSONObject firstEvent9 = null;

                try {

                    firstEvent = (JSONObject) timeline.get(0);
                    firstEvent1 = (JSONObject) timeline.get(0);
                    firstEvent2 = (JSONObject) timeline.get(0);
                    firstEvent3 = (JSONObject) timeline.get(0);
                    firstEvent4 = (JSONObject) timeline.get(0);
                    firstEvent5 = (JSONObject) timeline.get(0);
                    firstEvent6 = (JSONObject) timeline.get(0);
                    firstEvent7 = (JSONObject) timeline.get(0);
                    firstEvent8 = (JSONObject) timeline.get(0);
                    firstEvent9 = (JSONObject) timeline.get(0);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String tweetText = null;
                String tweetText1 = null;
                String tweetText2 = null;
                String tweetText3= null;
                String tweetText4 = null;
                String tweetText5 = null;
                String tweetText6 = null;
                String tweetText7= null;
                String tweetText8 = null;
                String tweetText9 = null;





            }
        });


}
}
