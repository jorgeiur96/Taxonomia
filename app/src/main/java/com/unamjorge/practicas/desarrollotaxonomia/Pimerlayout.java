package com.unamjorge.practicas.desarrollotaxonomia;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import Taxonomia.atributosTaxonomia;

public class Pimerlayout extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private ImageView photoImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView idTextView;

    private Button btn;

    private GoogleApiClient googleApiClient;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    private ImageView Img;
    private StorageReference nstorage;
    private ProgressDialog MproProgressBar;
    private   static  final int GALLERY_INTEN=1;
    private atributosTaxonomia taxo=new atributosTaxonomia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pimerlayout);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        photoImageView = (ImageView) findViewById(R.id.photoImageView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        nstorage = FirebaseStorage.getInstance().getReference();
        Img =(ImageView)findViewById(R.id.Imgimagenes);

        btn=(Button)findViewById(R.id.Listar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Pimerlayout.this,Escoger.class));
            }
        });


        MproProgressBar=new ProgressDialog(this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUserData(user);
                } else {
                    goLogInScreen();
                }
            }
        };
    }

    private void setUserData(FirebaseUser user) {
        nameTextView.setText(user.getDisplayName());

        if(user.getPhotoUrl()!=null)
        Glide.with(this).load(user.getPhotoUrl()).into(photoImageView);
        else {


        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    public void Btnclick1(View btn){
        Intent intent =new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,GALLERY_INTEN);


    }
    private void goLogInScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logOut(View view) {
        firebaseAuth.signOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.not_close_session, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void revoke(View view) {
        firebaseAuth.signOut();

        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.not_revoke, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path=data.getData();
            Img.setImageURI(path);

        }
        if(requestCode==GALLERY_INTEN && resultCode==RESULT_OK){


            MproProgressBar.setTitle("subiendo... ");
            MproProgressBar.setMessage("subiendo  foto  a servidor");
            MproProgressBar.setCancelable(false);
            MproProgressBar.show();
            Uri uri =data.getData();
            StorageReference filepath=nstorage.child("fotos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    MproProgressBar.dismiss();
                    Uri descrgarfoto=taskSnapshot.getDownloadUrl();
                   // Toast.makeText(Pimerlayout.this,""+descrgarfoto,Toast.LENGTH_LONG).show();
                    Log.i("Seesion",String.valueOf(descrgarfoto));
                    taxo.setFoto(String.valueOf(descrgarfoto));

                    Toast.makeText(Pimerlayout.this,"se subio exitosamente",Toast.LENGTH_LONG).show();


                    try {
                        Thread.sleep(500);

                        Intent intent =new Intent(Pimerlayout.this,RegistrarDatos.class);
                        intent.putExtra("URL",String.valueOf(descrgarfoto));
                        startActivity(intent);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });

        }
    }
    public  void mensaje (View v ,String menajae){
        Snackbar.make(v,menajae, Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(R.color.colorblanco))
                .show();
    }




}
