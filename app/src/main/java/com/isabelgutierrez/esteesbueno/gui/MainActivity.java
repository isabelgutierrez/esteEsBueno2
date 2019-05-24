package com.isabelgutierrez.esteesbueno.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.isabelgutierrez.esteesbueno.R;
import com.isabelgutierrez.esteesbueno.core.MiscController;
import com.isabelgutierrez.esteesbueno.core.RecetaController;
import com.isabelgutierrez.esteesbueno.gui.components.OnItemClickListener;
import com.isabelgutierrez.esteesbueno.gui.components.RecetasAdapter;
import com.isabelgutierrez.esteesbueno.model.Receta;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    public static HashMap<String, Object> GLOBALS = new HashMap<>();
    List<Receta> recetas;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recetasRecycler)
    RecyclerView recetasRecycler;


    MiscController miscController = MiscController.Instance();
    RecetaController recetaController = RecetaController.Instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        configToolbar();

        configureRecycler();
        inicializar();
        actualizar();
    }

    private void actualizar() {
        miscController.ShowWait(this, "Consultando recetas...");
        recetaController.GetAll();
    }

    public void actualizar(List<Receta> recetas){
        if(!recetas.isEmpty()){
            //Log.i("act",recetas.get(0).getFotoUrl());
            recetasRecycler.setAdapter(new RecetasAdapter(recetas, this));
            miscController.CloseWait();
        }
    }

    private void configToolbar() {
        setSupportActionBar(toolbar);
    }

    private void inicializar() {
        GLOBALS.put("app", this);

    }

    private void configureRecycler() {
        recetasRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recetasRecycler.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Receta receta) {
        miscController.receta=receta;
        //miscController.indexReceta = recetas.indexOf(receta);
        //recetaController.GetById(miscController.indexReceta);

        Intent intent = new Intent(getApplicationContext(), GetById.class);
        startActivity(intent);


    }

    @Override
    public void onLongItemClick(Receta receta) {

    }
}
