package com.isabelgutierrez.esteesbueno.core;

import android.util.Log;

import com.isabelgutierrez.esteesbueno.api.API;
import com.isabelgutierrez.esteesbueno.api.apiservices.RecetaService;
import com.isabelgutierrez.esteesbueno.gui.MainActivity;
import com.isabelgutierrez.esteesbueno.model.Receta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecetaController {

    private static RecetaController instance = null;

    public RecetaController() {
    }

    public static RecetaController Instance(){
        if(instance == null)
            instance = new RecetaController();
        return instance;
    }

    public void GetAll(){
        API.getApi().create(RecetaService.class).getAll().enqueue(new Callback<List<Receta>>() {
            @Override
            public void onResponse(Call<List<Receta>> call, Response<List<Receta>> response) {
                Log.i("getall",""+response.body());
                ((MainActivity) MainActivity.GLOBALS.get("app")).actualizar(response.body());
            }

            @Override
            public void onFailure(Call<List<Receta>> call, Throwable t) {
                Log.i("getall", t.getMessage());
            }
        });

    }

    public void GetById(int idReceta){
        API.getApi().create(RecetaService.class).getAll().enqueue(new Callback<List<Receta>>() {
            @Override
            public void onResponse(Call<List<Receta>> call, Response<List<Receta>> response) {
                Log.i("getbyid",""+response.body().toString());
            //    ((RecetasFragment) MainActivity.GLOBALS.get("recetasFragment")).actualizar(response.body());
            }

            @Override
            public void onFailure(Call<List<Receta>> call, Throwable t) {
                Log.i("getbyid",t.getMessage());
            }
        });
    }

}
