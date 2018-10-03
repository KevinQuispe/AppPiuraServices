package com.piuraservices.piuraservices.services.entel;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaReclamosEntelclient {
    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosentel();

    @GET("informacion/listainforeclamos/{id}")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosentel( @Path("id") long id);

}
