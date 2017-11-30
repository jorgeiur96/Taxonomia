package com.unamjorge.practicas.desarrollotaxonomia;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Taxonomia.atributosTaxonomia;

/**
 * Created by Jorge Urueta on 23/11/2017.
 */

public class TipoAdaptador extends RecyclerView.Adapter<TipoAdaptador.ZonaViewHolder> {

    ArrayList<atributosTaxonomia> atributos;

    public TipoAdaptador(ArrayList<atributosTaxonomia> atributos){
        this.atributos=atributos;
    }


    @Override
    public ZonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_zonas,parent,false);
        return new TipoAdaptador.ZonaViewHolder(v);
    }
    atributosTaxonomia obj=new atributosTaxonomia();

    @Override
    public void onBindViewHolder(final ZonaViewHolder zonaViewHolder, int position) {
        final atributosTaxonomia lis=atributos.get(position);

        zonaViewHolder.tvCardviewnzo.setText(lis.getZonas());

        zonaViewHolder.tvCardviewnzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(zonaViewHolder.tvCardviewnzo.getContext(),CatTipo.class);
                intent.putExtra("val",lis.getZonas());

                zonaViewHolder.tvCardviewnzo.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return atributos.size();
    }


    public static  class ZonaViewHolder extends RecyclerView.ViewHolder{



        private  TextView tvCardviewnzo;

        public ZonaViewHolder(View itemView) {
            super(itemView);

            tvCardviewnzo=(TextView) itemView.findViewById(R.id.tvCardviewnzo);

        }





    }
}
