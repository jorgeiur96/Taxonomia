package com.unamjorge.practicas.desarrollotaxonomia;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import Taxonomia.atributosTaxonomia;

/**
 * Created by Jorge Urueta on 21/11/2017.
 */

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import Taxonomia.atributosTaxonomia;

/**
 * Created by Jorge Urueta on 21/11/2017.
 */

public class Taxoadaptador extends RecyclerView.Adapter<Taxoadaptador.taxoViewHolder>{
    ArrayList <atributosTaxonomia> atributos;

    public Taxoadaptador(ArrayList<atributosTaxonomia> atributos){
        this.atributos=atributos;
    }




    @Override
    public taxoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_datos,parent,false);
        return new taxoViewHolder(v);
    }
atributosTaxonomia obj=new atributosTaxonomia();
    @Override
    public void onBindViewHolder(final taxoViewHolder taxoViewHolder, int position) {
        final atributosTaxonomia lis=atributos.get(position);
        //taxoViewHolder.IMGfotoCard.set(lis.getFoto());
        taxoViewHolder.tvCardviewnco.setText(lis.getNombrecomun());
        taxoViewHolder.tvCardviewnci.setText(lis.getNombrecientifico());
        taxoViewHolder.tvCardviewnzo.setText(lis.getZonas());
        //taxoViewHolder.urlImagen.setText(lis.getFoto());
        taxoViewHolder.IMGfotoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(taxoViewHolder.IMGfotoCard.getContext(),"codigo"+lis.getID(),Toast.LENGTH_LONG).show();

                Intent intent =new Intent(taxoViewHolder.IMGfotoCard.getContext(),DetallesTaxo.class);
                intent.putExtra("val",lis.getID());
                taxoViewHolder.IMGfotoCard.getContext().startActivity(intent);
            }
        });
        //Toast.makeText(taxoViewHolder.IMGfotoCard.getContext(),lis.getHábitat(),Toast.LENGTH_SHORT).show();

        Glide.with(taxoViewHolder.IMGfotoCard.getContext())
                .load(lis.getFoto())
                .crossFade()
                .placeholder(R.drawable.aves)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(taxoViewHolder.IMGfotoCard);


        // imageView.setImageDrawable(n);

    }

    @Override
    public int getItemCount() {
        return atributos.size();
    }

    public static  class taxoViewHolder extends RecyclerView.ViewHolder{


        private ImageView IMGfotoCard;
        private TextView  tvCardviewnco;
        private  TextView tvCardviewnci;
        private  TextView tvCardviewnzo;
        private TextView urlImagen;
        public taxoViewHolder(View itemView) {
            super(itemView);

            IMGfotoCard=(ImageView)itemView.findViewById(R.id.IMGfotoCard);
            tvCardviewnco=(TextView) itemView.findViewById(R.id.tvCardviewnco);
            tvCardviewnci=(TextView) itemView.findViewById(R.id.tvCardviewnci);
            tvCardviewnzo=(TextView) itemView.findViewById(R.id.tvCardviewnzo);
            urlImagen =itemView.findViewById(R.id.urlImagen);
        }





    }



}