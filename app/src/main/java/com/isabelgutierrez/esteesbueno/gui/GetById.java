package com.isabelgutierrez.esteesbueno.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.isabelgutierrez.esteesbueno.R;
import com.isabelgutierrez.esteesbueno.core.MiscController;
import com.isabelgutierrez.esteesbueno.gui.components.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetById extends AppCompatActivity {

    private OnItemClickListener listener;
    private Context context;

    @BindView(R.id.txtNombre2)
    AppCompatEditText txtNombre;
    @BindView(R.id.txtIngredientes2)
    AppCompatEditText txtIngredientes;
    @BindView(R.id.txtProceso2)
    AppCompatEditText txtProceso;
    @BindView(R.id.imgFoto2)
    AppCompatImageView fotoUrl;


    public MiscController miscController = MiscController.Instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_by_id);

        ButterKnife.bind(this);
        configUI();

    }

    private void configUI() {
        txtNombre.setText(miscController.receta.getNombre());
        txtIngredientes.setText(miscController.receta.getIngredientes());
        txtProceso.setText(miscController.receta.getProceso());
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();
        Glide.with(getApplicationContext())
                .load(miscController.receta.getFotoUrl())
                .apply(options)
                .into(fotoUrl);
        //fotoUrl.setBackground(miscController.receta.getFotoUrl());
        //fotoUrl.setBackground(miscController.receta.getFotoUrl());


    }


    public void onViewClicked() {

    }
}
