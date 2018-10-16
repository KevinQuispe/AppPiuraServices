package com.piuraservices.piuraservices.services.telefonia;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoReclamosMovistarmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaReclamosMovistarclient {

    @GET("informacion/listainforeclamos/{id}")
    Call<List<InfoReclamosMovistarmodel>> getInfoReclamosmovistar(@Path("id") long id);

}

