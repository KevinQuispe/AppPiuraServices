package com.piuraservices.piuraservices.services.enosa;

import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaTramitesEnosaclient {

    @GET("informacion/getInfoTramites/{id}")
    Call<List<InfoTramitesEnosamodel>> getInfoTramitesenosa(@Path("id") long id);
}
