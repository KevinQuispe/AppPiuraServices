package com.piuraservices.piuraservices.services.entel;

import com.piuraservices.piuraservices.models.telefonia.entel.InfoReclamosEntelmodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoTramitesEntelmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaTramitesEntelclient {

    @GET("informacion/listaifotramites/{id}")
    Call<List<InfoTramitesEntelmodel>> getInfoTramitesentel(@Path("id") long id);
}
