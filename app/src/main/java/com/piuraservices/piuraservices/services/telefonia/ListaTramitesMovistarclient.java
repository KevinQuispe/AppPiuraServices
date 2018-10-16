package com.piuraservices.piuraservices.services.telefonia;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoTramitesMovistarmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaTramitesMovistarclient {

    @GET("informacion/listainfotramites/{id}")
    Call<List<InfoTramitesMovistarmodel>> getInfoTramitesmovistar(@Path("id") long id);
}
