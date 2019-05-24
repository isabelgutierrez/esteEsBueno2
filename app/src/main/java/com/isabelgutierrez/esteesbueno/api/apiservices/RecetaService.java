package com.isabelgutierrez.esteesbueno.api.apiservices;

import com.isabelgutierrez.esteesbueno.model.Receta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecetaService {
    @GET("receta/getall")
    Call<List<Receta>> getAll();

    @GET("receta/getbyid/{idReceta}")
    Call<Receta> getById (@Path("idReceta") int idReceta);

}
