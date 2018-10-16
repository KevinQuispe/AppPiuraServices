package com.piuraservices.piuraservices.services.entel;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoReclamosEntelmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaReclamosEntelclient {
    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosentel();

    @GET("informacion/listainforeclamos/{id}")
    Call<List<InfoReclamosEntelmodel>> getInfoReclamosentel(@Path("id") long id);

}
