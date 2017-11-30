package com.unamjorge.practicas.desarrollotaxonomia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityRegistro extends AppCompatActivity {
    TextInputEditText correo, pass, cedula;
    FirebaseAuth.AuthStateListener authStateListener;
    Button  btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        Inicializar();
    }

    private void Inicializar() {
        correo=(TextInputEditText)findViewById(R.id.txtemail);
        pass=(TextInputEditText)findViewById(R.id.txtpassword);
        cedula=(TextInputEditText)findViewById(R.id.txtcedula);
        btn=(Button)findViewById(R.id.btnRegistrar);

        authStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user!=null){

                    Log.i("SESSION","sesion iniciada con mail :"+user.getEmail());

                }else{

                    Log.i("SESSION","sesion cerrada");
                }

            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n1,n2,n3;
                n1=correo.getText()+"";
                n2=pass.getText()+"";
                n3=cedula.getText()+"";


                Pattern pattern = Pattern
                        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                Matcher mather = pattern.matcher(n1);

                if(mather.find() == false) {
                    mensaje(v,"El email ingresado es invalido.");
                }
                else if(n2.equals("")){
                    mensaje(v,"El password ingresado es invalido.");


                }else if(n3.equals("")){

                    mensaje(v,"La cedula ingresada es invalida.");


                }else{

                    Registrar(n1,n2,n3);
                }




            }
        });
    }

    public  void mensaje (View v ,String menajae){
        Snackbar.make(v,menajae, Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(R.color.colorblanco))
                .show();
    }




    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener!=null){

            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
    }






    private  void Registrar(String user, String pass, final String cedula ){
        //

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.i("SESSIO", "USER CREADO");

                    startActivity(new Intent(ActivityRegistro.this,Pimerlayout.class));


                }else{

                    Log.i("SESSION", task.getException().getMessage()+"");
                }
            }
        });


    }



}
