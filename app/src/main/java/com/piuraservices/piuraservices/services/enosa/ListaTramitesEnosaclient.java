package com.piuraservices.piuraservices.services.enosa;

import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaTramitesEnosaclient {
    @GET("informacion/getInfoReclamos")
    Call<List<InfoTramitesEnosamodel>> getInfoReclamosenosa();

}
