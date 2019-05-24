package com.isabelgutierrez.esteesbueno.gui.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.isabelgutierrez.esteesbueno.R;
import com.isabelgutierrez.esteesbueno.model.Receta;

import java.util.List;

public class RecetasAdapter extends RecyclerView.Adapter<RecetasAdapter.ViewHolder>{

    private List<Receta> recetas;
    private OnItemClickListener listener;
    private Context context;

    public RecetasAdapter(List<Receta> recetas, OnItemClickListener listener) {
        this.recetas = recetas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecetasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_receta, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetasAdapter.ViewHolder viewHolder, int i) {
        Receta receta = recetas.get(i);
        viewHolder.setOnClickListener(receta,listener);
        viewHolder.txtDatos.setText(receta.getNombre());
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();
        Glide.with(context)
                .load(receta.getFotoUrl())
                .apply(options)
                .into(viewHolder.imgFoto);
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        protected AppCompatImageView imgFoto;
        protected AppCompatTextView txtDatos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            txtDatos = itemView.findViewById(R.id.txtDatos);
            this.view = itemView;

        }
        public void setOnClickListener(final Receta receta, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(receta);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongItemClick(receta);
                    return true;
                }
            });
        }
    }
}
