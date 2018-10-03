package com.piuraservices.piuraservices.services.claro;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReclamosClaromodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaReclamosClaroclient {

    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosClaro();

    @GET("informacion/getinforeclamos/{id}")
    Call<InfoReclamosClaromodel> getInfoReclamosClaro(@Path("id") long id);
}
